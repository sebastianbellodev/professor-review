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
    /// Interaction logic for LogEducationalProgramWindow.xaml
    /// </summary>
    public partial class LogEducationalProgramWindow : Window
    {

        Faculty Faculty;

        public LogEducationalProgramWindow()
        {
            InitializeComponent();
            GetFaculties();
        }

        private async void GetFaculties()
        {
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Faculty> educationalExperiences = response.Faculties;
                FacultyComboBox.ItemsSource = educationalExperiences;
                FacultyComboBox.DisplayMemberPath = nameof(response.Faculty.Name);
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

        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            var name = NameTextBox.Text;
            var faculty = FacultyComboBox.SelectedItem as Faculty;
            if (!string.IsNullOrWhiteSpace(name) && faculty != null)
            {
                var educationalProgram = new EducationalProgram()
                {
                    Name = name,
                    IdFaculty = faculty.IdFaculty
                };
                ModifyEducationalExperience(educationalProgram);
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private async void ModifyEducationalExperience(EducationalProgram educationalProgram)
        {
            Response response = await EducationalProgramService.Post(educationalProgram);
            switch (response.Code)
            {
                case (int)HttpStatusCode.OK:
                    MessageBox.Show(Properties.Resources.REGISTERED_INFORMATION_LABEL);
                    ClearField();
                    break;
                case (int)HttpStatusCode.BadRequest:
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                            Properties.Resources.EDUCATIONAL_PROGRAM_ALREADY_REGISTERED_LABEL);
                    break;
                case (int)HttpStatusCode.Forbidden:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                            Properties.Resources.EXPIRED_SESSION_LABEL);
                    GoToEducationalProgramAdministrationMenu();
                    break;
                default:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                            Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                    GoToEducationalProgramAdministrationMenu();
                    break;
            }
        }

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            GoToEducationalProgramAdministrationMenu();
        }

        private void GoToEducationalProgramAdministrationMenu()
        {
            var educationalProgramAdministrationMenuWindow = new EducationalProgramAdministrationMenuWindow();
            Close();
            educationalProgramAdministrationMenuWindow.Show();
        }

        private void ClearField()
        {
            NameTextBox.Clear();
            FacultyComboBox.SelectedItem = null;
        }
    }
}
