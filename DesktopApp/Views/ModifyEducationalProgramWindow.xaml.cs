using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System.Collections.Generic;
using System.Net;
using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class ModifyEducationalProgramWindow : Window
    {
        private readonly int MAXIMUM_NUMBER_OF_FACULTIES_PER_EDUCATIONAL_PROGRAM = 1;

        public ModifyEducationalProgramWindow()
        {
            InitializeComponent();
            GetEducationalPrograms();
            GetFaculties();
        }

        private async void GetEducationalPrograms()
        {
            Response response = await EducationalProgramService.GetEducationalPrograms();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalProgram> educationalPrograms = response.EducationalPrograms;
                EducationalProgramComboBox.ItemsSource = educationalPrograms;
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                GoToEducationalProgramAdministrationMenu();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                GoToEducationalProgramAdministrationMenu();
            }
        }

        private void GoToEducationalProgramAdministrationMenu()
        {
            var educationalProgramAdministrationMenuWindow = new EducationalProgramAdministrationMenuWindow();
            Close();
            educationalProgramAdministrationMenuWindow.Show();
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
                GoToEducationalProgramAdministrationMenu();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                GoToEducationalProgramAdministrationMenu();
            }
        }

        private void EducationalProgramComboBoxSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            NameTextBox.IsEnabled = true;
            EducationalProgram educationalProgram = EducationalProgramComboBox.SelectedItem as EducationalProgram;
            NameTextBox.Text = educationalProgram.Name;
            ConfigureFacultyByEducationalProgram(educationalProgram);
        }

        private void ConfigureFacultyByEducationalProgram(EducationalProgram educationalProgram)
        {
            int idFaculty = educationalProgram.IdFaculty;
            foreach (var item in FacultiesDataGrid.Items)
            {
                if (item is Faculty faculty)
                {
                    faculty.IsSelected = faculty.IdFaculty == idFaculty;
                }
            }
        }

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            GoToEducationalProgramAdministrationMenu();
        }

        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            string name = NameTextBox.Text;
            if (!string.IsNullOrWhiteSpace(name))
            {
                EducationalProgram educationalProgram = EducationalProgramComboBox.SelectedItem as EducationalProgram;
                int numberOfFaculties = GetNumberOfFacultiesByEducationalProgram();
                if (numberOfFaculties <= MAXIMUM_NUMBER_OF_FACULTIES_PER_EDUCATIONAL_PROGRAM)
                {
                    educationalProgram.Name = (educationalProgram.Name == name) ? null : name;
                    Faculty faculty = GetFacultyByEducationalProgram();
                    educationalProgram.IdFaculty = faculty.IdFaculty;
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

        private int GetNumberOfFacultiesByEducationalProgram()
        {
            int numberOfFaculties = 0;
            foreach (var item in FacultiesDataGrid.Items)
            {
                if (item is Faculty faculty)
                {
                    if (faculty.IsSelected)
                    {
                        numberOfFaculties++;
                    }
                }
            }
            return numberOfFaculties;
        }

        private Faculty GetFacultyByEducationalProgram()
        {
            foreach (var item in FacultiesDataGrid.Items)
            {
                if (item is Faculty faculty)
                {
                    if (faculty.IsSelected)
                    {
                        return faculty;
                    }
                }
            }
            return null;
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
                    break;
                default:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                            Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                    break;
            }
            GoToEducationalProgramAdministrationMenu();
        }
    }
}