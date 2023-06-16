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

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class AssignEducationalExperienceToProfessor : Window
    {
        private List<Professor> professorsList;
        private List<EducationalExperience> educationalExperienceList;
        private Syllabus selectedIdSyllabus = new Syllabus();

        Dictionary<string, string> FacultyDictionary;

        public AssignEducationalExperienceToProfessor()
        {
            InitializeComponent();
            LoadFaculties();
            professorsList = new List<Professor>();
            educationalExperienceList = new List<EducationalExperience>();
        }

        private async void LoadFaculties()
        {
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Faculty> faculties = response.Faculties;
                cbb_Faculty.ItemsSource = faculties;
                cbb_Faculty.DisplayMemberPath = nameof(response.Faculty.Name);

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

        private void FacultyComboBoxSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            Faculty faculty = cbb_Faculty.SelectedItem as Faculty;
            LoadEducationalPograms(faculty);
            LoadProfessors(faculty);

        }

        private async void LoadEducationalPograms(Faculty faculty)
        {
            Response response = await EducationalProgramService.GetEducationalProgramsByFaculty(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalProgram> educationalPrograms = response.EducationalPrograms;
                cbb_EducationalProgram.ItemsSource = educationalPrograms;

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

        private void EducationalProgramComboBoxSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            EducationalProgram educationalProgram = cbb_EducationalProgram.SelectedItem as EducationalProgram;
            LoadEducationalExperience(educationalProgram);
        }

        private async void LoadEducationalExperience(EducationalProgram educationalProgram)
        {
            Response response = await EducationalExperienceService.GetEducationalExperienceByEducationalProgram(educationalProgram);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalExperience> educationalExperiences = response.EducationalExperiences;
                educationalExperienceList = educationalExperiences;
                DataGriEducationalExperience.ItemsSource = educationalExperiences;

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

        private void EducationalExperienceDataGridSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            EducationalExperience educationalExperience = DataGriEducationalExperience.SelectedItem as EducationalExperience;
            EducationalExperienceNameTextBox.Text = educationalExperience.Name;
        }

        private async void LoadProfessors(Faculty faculty)
        {
            Response response = await ProfessorService.GetProfessorsByFaculty(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Professor> professors = response.Professors;
                professorsList = professors;
                DataGridProfessor.ItemsSource = professors;

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

        private void ProfessorDataGridSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            Professor professor = DataGridProfessor.SelectedItem as Professor;
            ProfessorNameTextBox.Text = professor.Name;
        }

        private void EducationalExperienceSearchTextBoxTextChanged(object sender, TextChangedEventArgs e)
        {
            var searchTextBox = sender as TextBox;
            if (searchTextBox.Text != "")
            {
                var filteredList = educationalExperienceList.Where(x => x.Name.ToLower().Contains(searchTextBox.Text));
                DataGriEducationalExperience.ItemsSource = null;
                DataGriEducationalExperience.ItemsSource = filteredList;
            }
            else
            {
                DataGriEducationalExperience.ItemsSource = educationalExperienceList;
            }
        }

        private void ProfessorSearchTextBoxTextChanged(object sender, TextChangedEventArgs e)
        {
            var searchTextBox = sender as TextBox;
            if (searchTextBox.Text != "")
            {
                var filteredList = professorsList.Where(x => x.Name.ToLower().Contains(searchTextBox.Text));
                DataGridProfessor.ItemsSource = null;
                DataGridProfessor.ItemsSource = filteredList;
            }
            else
            {
                DataGridProfessor.ItemsSource = professorsList;
            }
        }

        private async void RegistrarButtonClick(object sender, RoutedEventArgs e)
        {
            EducationalExperience educationalExperience = DataGriEducationalExperience.SelectedItem as EducationalExperience;
            Professor professor = DataGridProfessor.SelectedItem as Professor;

            Response response = await SyllabusService.GetSyllabusesByEducationalExperience(educationalExperience);
            int idSyllabus = response.Syllabuses.First().IdSyllabus;

            var academicOffering = new AcademicOffering()
            {
                IdProfessor = professor.IdProfessor,
                IdSyllabus = idSyllabus
            };

            PostAcademicOffering(academicOffering);


        }

        private async void PostAcademicOffering(AcademicOffering academicOffering)
        {
            Response response = await AcademicOfferingService.Post(academicOffering);
            if (response.Code != (int)HttpStatusCode.OK)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {          
                MessageBox.Show("Document saved", "Success", MessageBoxButton.OK, MessageBoxImage.Information, MessageBoxResult.Yes);
            }
        }

        private void RegresarButtonClick(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

    }
}
