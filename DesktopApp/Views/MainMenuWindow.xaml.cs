using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class MainMenuWindow : Window
    {
        public MainMenuWindow()
        {
            InitializeComponent();
        }

        private void ProfessorPerformanceEvaluationsManagementButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void EducationalProgramAdministrationButtonClick(object sender, RoutedEventArgs e)
        {
            var educationalProgramAdministrationMenuWindow = new EducationalProgramAdministrationMenuWindow();
            Close();
            educationalProgramAdministrationMenuWindow.Show();
        }

        private void StudentManagementButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void SpecificReportGenerationButtonClick(object sender, RoutedEventArgs e)
        {
            var specificReportGenerationMenuWindow = new SpecificReportGenerationMenuWindow();
            Close();
            specificReportGenerationMenuWindow.Show();
        }

        private void LogOutButtonClick(object sender, RoutedEventArgs e)
        {
            var loginWindow = new LoginWindow();
            Close();
            loginWindow.Show();
        }
    }
}