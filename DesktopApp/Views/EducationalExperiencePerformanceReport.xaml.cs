using Microsoft.Win32;
using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using iTextSharp.text;
using iTextSharp.text.pdf;
using iTextSharp.tool.xml;
using System.IO;
using Sprache;

namespace ProfessorPerformanceEvaluation.Views
{
    /// <summary>
    /// Lógica de interacción para EducationalExperiencePerformanceReport.xaml
    /// </summary>
    public partial class EducationalExperiencePerformanceReport : Window
    {
        private List<Review> listReviews;

        public EducationalExperiencePerformanceReport()
        {
            InitializeComponent();
            listReviews = new List<Review>();
            LoadFaculties();
        }
        private async void LoadFaculties()
        {
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Faculty> faculties = response.Faculties;
                FacultyComboBox.ItemsSource = faculties;

            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }
        }

        private void FacultyComboBoxSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Faculty faculty = FacultyComboBox.SelectedItem as Faculty;
            LoadEducationalPograms(faculty);
        }

        private async void LoadEducationalPograms(Faculty faculty)
        {
            Response response = await EducationalProgramService.GetEducationalProgramsByFaculty(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalProgram> educationalPrograms = response.EducationalPrograms;
                EducationalProgramComboBox.ItemsSource = educationalPrograms;

            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }
        }

        private void EducationalProgramComboBoxSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            EducationalProgram educationalProgram = EducationalProgramComboBox.SelectedItem as EducationalProgram;
            LoadEducationalExperience(educationalProgram);
        }

        private async void LoadEducationalExperience(EducationalProgram educationalProgram)
        {
            Response response = await EducationalExperienceService.GetEducationalExperienceByEducationalProgram(educationalProgram);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalExperience> educationalExperiences = response.EducationalExperiences;
                EducationalExperienceComboBox.ItemsSource = educationalExperiences;

            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }
        }

        private void EducationalExperienceComboBoxSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            EducationalExperience educationalExperience = EducationalExperienceComboBox.SelectedItem as EducationalExperience;
            LoadReviews(educationalExperience);
        }

        private async void LoadReviews(EducationalExperience educationalExperience)
        {
            Response response = await ReviewService.GetReviewsByEducationalExperience(educationalExperience);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Review> reviews = response.Reviews;
                listReviews = reviews;

            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }
        }   

        private void SaveButtonClick(object sender, RoutedEventArgs e)
        {
            VerifyTexbox();
        }
    
        private void VerifyTexbox()
        {
            if (EmptyFields())
            {
                LoadDocument();
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                   Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        

        private void LoadDocument()
        {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.FileName = DocumentNameTextBox.Text;
            saveFileDialog.DefaultExt = "pdf";
            Nullable<bool> result = saveFileDialog.ShowDialog();

            LoadContent(result, saveFileDialog);                       
        }

        private void LoadContent(Nullable<bool> result, SaveFileDialog saveFileDialog)
        {
            Faculty faculty = FacultyComboBox.SelectedItem as Faculty;
            EducationalProgram educationalProgram = EducationalProgramComboBox.SelectedItem as EducationalProgram;
            EducationalExperience educationalExperience = EducationalExperienceComboBox.SelectedItem as EducationalExperience;
            string average = GetAverage();
            string totalReviews = GetTotalReviews();

            string hmtlFormat = Properties.Resources.EducationalExperienceReportFormat.ToString();
            hmtlFormat = hmtlFormat.Replace("@Experiencia", educationalExperience.Name);
            hmtlFormat = hmtlFormat.Replace("@Calificacion", average);
            hmtlFormat = hmtlFormat.Replace("@TotalReviews", totalReviews);
            hmtlFormat = hmtlFormat.Replace("@Facultad", faculty.Name);
            hmtlFormat = hmtlFormat.Replace("@Programa", educationalProgram.Name);

            GenerateDocument(result, hmtlFormat, saveFileDialog);
        }

        private string GetAverage()
        {
            return listReviews.Average(x => x.Stars).ToString();
        }

        private string GetTotalReviews()
        {
            return listReviews.Count.ToString();
        }

        private void GenerateDocument(Nullable<bool> result, string hmtlFormat, SaveFileDialog saveFileDialog)
        {
            if (result == true)
            {
                using (FileStream stream = new FileStream(saveFileDialog.FileName, FileMode.Create))
                {
                    Document document = new Document(PageSize.A4, 25, 25, 25, 25);

                    PdfWriter pdfWriter = PdfWriter.GetInstance(document, stream);

                    document.Open();
                    using (StringReader stringReader = new StringReader(hmtlFormat))
                    {
                        XMLWorkerHelper.GetInstance().ParseXHtml(pdfWriter, document, stringReader);
                    }
                    document.Close();
                    stream.Close();

                }
            }
        }

        private Boolean EmptyFields()
        {
            Boolean result = false;
            if (!string.IsNullOrEmpty(this.DocumentNameTextBox.Text))
            {
                result = true;
            }
            return result;
        }

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
