using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net;
using System.Reflection;
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
    /// Interaction logic for LogProfessorWindow.xaml
    /// </summary>
    public partial class LogProfessorWindow : Window
    {
        public LogProfessorWindow()
        {
            InitializeComponent();
        }

        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            string name = NameTextBox.Text;
            string lastName = LastNameTextBox.Text;
            if (!string.IsNullOrWhiteSpace(name) && !string.IsNullOrWhiteSpace(lastName))
            {
                var professor = new Professor()
                {
                    Name = name,
                    LastName = lastName
                };
                Post(professor);
            } else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private async void Post(Professor professor)
        {
            Response response = await ProfessorService.Post(professor);
            switch (response.Code)
            {
                case (int)HttpStatusCode.Created:
                    MessageBox.Show(Properties.Resources.REGISTERED_INFORMATION_LABEL);
                    ClearField();
                    break;
                case (int)HttpStatusCode.Forbidden:
                    MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                        Properties.Resources.EXPIRED_SESSION_LABEL);
                    ClearField();
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
            LastNameTextBox.Clear();
        }
    }
}
