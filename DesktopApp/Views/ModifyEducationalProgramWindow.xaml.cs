using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System.Collections.Generic;
using System.Net;
using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class ModifyEducationalProgramWindow : Window
    {
        public ModifyEducationalProgramWindow()
        {
            InitializeComponent();
            GetFaculties();
        }

        private async void GetFaculties()
        {
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Faculty> faculties = response.Faculties;
                FacultiesDataGrid.ItemsSource = faculties;
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
            EducationalProgram educationalProgram = EducationalProgramComboBox.SelectedItem as EducationalProgram;
            NameTextBox.Text = educationalProgram.Name;
            NameTextBox.IsEnabled = true;
            ConfigureFacultyByEducationalProgram(educationalProgram);
        }

        private void ConfigureFacultyByEducationalProgram(EducationalProgram educationalProgram)
        {
            int idFaculty = educationalProgram.IdFaculty;
            foreach (Faculty faculty in FacultiesDataGrid.Items)
            {
                if (faculty.IdFaculty == idFaculty)
                {
                    faculty.IsSelected = true;
                }
            }
        }

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            string name = NameTextBox.Text;
            if (!string.IsNullOrWhiteSpace(name))
            {
                EducationalProgram educationalProgram = EducationalProgramComboBox.SelectedItem as EducationalProgram;
                int numberOfFaculties = GetNumberOfFacultiesByEducationalProgram(educationalProgram);
                if (numberOfFaculties <= 1)
                {
                    educationalProgram.Name = name;
                    ModifyEducationalProgram(educationalProgram);
                }
                else
                {
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                        Properties.Resources.FACULTY_BY_EDUCATIONAL_PROGRAM_LABEL);
                }
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private int GetNumberOfFacultiesByEducationalProgram(EducationalProgram educationalProgram)
        {
            int numberOfFaculties = 0;
            foreach (Faculty faculty in FacultiesDataGrid.Items)
            {
                if (faculty.IsSelected)
                {
                    numberOfFaculties++;
                }
            }
            return numberOfFaculties;
        }

        private async void ModifyEducationalProgram(EducationalProgram educationalProgram)
        {
            Response response = await EducationalProgramService.Patch(educationalProgram);
            switch (response.Code)
            {
                case (int)HttpStatusCode.OK:
                    MessageBox.Show(Properties.Resources.MODIFIED_INFORMATION_LABEL);
                    break;
                case (int)HttpStatusCode.BadRequest:
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                            Properties.Resources.EDUCATIONAL_PROGRAM_ALREADY_REGISTERED_LABEL);
                    break;
                case (int)HttpStatusCode.Forbidden:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                            Properties.Resources.EXPIRED_SESSION_LABEL);
                    Close();
                    break;
                default:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                            Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                    Close();
                    break;
            }
        }
    }
}