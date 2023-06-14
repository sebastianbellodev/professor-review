﻿using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class EducationalProgramManagementMenuWindow : Window
    {
        public EducationalProgramManagementMenuWindow()
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
        }

        private void ModifyProfessorButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void AssignEducationalExperienceToProfessorButtonClick(object sender, RoutedEventArgs e)
        {
        }

        private void LogEducationalProgramButtonClick(object sender, RoutedEventArgs e)
        {
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