using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class EducationalProgramAdministrationMenuWindow : Window
    {
        public EducationalProgramAdministrationMenuWindow()
        {
            InitializeComponent();
        }

        private void LogEducationalExperienceButtonClick(object sender, RoutedEventArgs e)
        {
            var logEducationalExperienceWindow = new LogEducationalExperienceWindow();
            Close();
            logEducationalExperienceWindow.Show();
        }

        private void ModifyEducationalExperienceButtonClick(object sender, RoutedEventArgs e)
        {
            var modifyEducationalExperienceWindow = new ModifyEducationalExperienceWindow();
            Close();
            modifyEducationalExperienceWindow.Show();
        }

        private void LogProfessorButtonClick(object sender, RoutedEventArgs e)
        {
            var logProfessorWindow = new LogProfessorWindow();
            Close();
            logProfessorWindow.Show();
        }

        private void ModifyProfessorButtonClick(object sender, RoutedEventArgs e)
        {
            var modifyProfessorWindow = new ModifyProfessorWindow();
            Close();
            modifyProfessorWindow.Show();
        }

        private void AssignEducationalExperienceToProfessorButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void LogEducationalProgramButtonClick(object sender, RoutedEventArgs e)
        {
            var logEducationalProgramWindow = new LogEducationalProgramWindow();
            Close();
            logEducationalProgramWindow.Show();
        }

        private void ModifyEducationalProgramButtonClick(object sender, RoutedEventArgs e)
        {
            var modifyEducationalProgramWindow = new ModifyEducationalProgramWindow();
            Close();
            modifyEducationalProgramWindow.Show();
        }

        private void ManageFacultiesButtonClick(object sender, RoutedEventArgs e)
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