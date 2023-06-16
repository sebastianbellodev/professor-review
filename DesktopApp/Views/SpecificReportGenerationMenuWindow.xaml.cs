using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class SpecificReportGenerationMenuWindow : Window
    {
        public SpecificReportGenerationMenuWindow()
        {
            InitializeComponent();
        }

        private void GenerateProfessorPerformanceReportByProfessorButtonClick(object sender, RoutedEventArgs e)
        {
            var profesor = new ProfessorPerformanceReport();
            profesor.Show();
        }

        private void GenerateProfessorPerformanceReportByEducationalExperienceButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void GenerateActivityReportByStudentButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void ReturnButtonClick(object sender, RoutedEventArgs e)
        {
            var mainMenuWindow = new MainMenuWindow();
            Close();
            mainMenuWindow.Show();
        }
    }
}