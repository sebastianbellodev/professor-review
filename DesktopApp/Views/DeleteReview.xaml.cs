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
    /// <summary>
    /// Lógica de interacción para DeleteReview.xaml
    /// </summary>
    public partial class DeleteReview : Window
    {
        public DeleteReview()
        {
            InitializeComponent();
            LoadFaculties();
        }

        private async void LoadFaculties()
        {
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Faculty> faculties = response.Faculties;
                cbb_Faculty.ItemsSource = faculties;

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
                cbb_EducationalExperience.ItemsSource = educationalExperiences;

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

        private void EducationalExperienceComboBoxSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            EducationalExperience educationalExperience = cbb_EducationalExperience.SelectedItem as EducationalExperience;
            LoadProfessors(educationalExperience);
        }

        private async void LoadProfessors(EducationalExperience educationalExperience)
        {
            Response response = await ProfessorService.GetProfessorsByEducationalExperience(educationalExperience);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Professor> professors = response.Professors;
                cbb_Professors.ItemsSource = professors;

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
        private void ProfessorComboBoxSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            Professor professor = cbb_Professors.SelectedItem as Professor;
            LoadReviews(professor);
        }

        private async void LoadReviews(Professor professor)
        {
            Response response = await ReviewService.GetReviewByProfessor(professor);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Review> reviews = response.Reviews;
                DataGridReviews.ItemsSource = reviews;

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

        private void DeleteButtonClick(object sender, RoutedEventArgs e)
        {
            Review reviewDataGrid = new Review();
            if (VerifySelectedFacultyDataGrid())
            {
                reviewDataGrid = this.DataGridReviews.SelectedItem as Review;
                DeleteSelectedReview(reviewDataGrid);
            }
            else
            {
                MessageBox.Show(Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private Boolean VerifySelectedFacultyDataGrid()
        {
            Boolean result = false;
            if (DataGridReviews.SelectedIndex != -1)
            {
                result = true;
            }
            return result;
        }

        private async void DeleteSelectedReview(Review review)
        {
            Response response = await ReviewService.Delete(review);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                MessageBox.Show(Properties.Resources.DELETED_INFORMATION_LABEL);
                this.Close(); ;
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

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
