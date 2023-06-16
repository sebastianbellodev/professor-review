using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class LogEducationalExperienceWindow : Window
    {
        public LogEducationalExperienceWindow()
        {
            InitializeComponent();
            GetEducationalPrograms();
        }

        private async void GetEducationalPrograms()
        {
            Response response = await EducationalProgramService.GetEducationalPrograms();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalProgram> educationalPrograms = response.EducationalPrograms;
                EducationalProgramsDataGrid.ItemsSource = educationalPrograms;
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

        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            GoToEducationalProgramAdministrationMenu();
        }


        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            string name = NameTextBox.Text;
            if (!string.IsNullOrWhiteSpace(name))
            {
                var educationalExperience = new EducationalExperience()
                {
                    Name = name
                };
                LogEducationalExperience(educationalExperience);
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private async void LogEducationalExperience(EducationalExperience educationalExperience)
        {
            Response response = await EducationalExperienceService.Post(educationalExperience);
            switch (response.Code)
            {
                case (int)HttpStatusCode.Created:
                    MessageBox.Show(Properties.Resources.REGISTERED_INFORMATION_LABEL);
                    LogSyllabuses(educationalExperience);
                    GoToEducationalProgramAdministrationMenu();
                    break;
                case (int)HttpStatusCode.BadRequest:
                    MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                        Properties.Resources.EDUCATIONAL_EXPERIENCE_ALREADY_REGISTERED_LABEL);
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

        private async void LogSyllabuses(EducationalExperience educationalExperience)
        {
            Response response = await EducationalExperienceService.GetEducationalExperienceByName(educationalExperience);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                int idEducationalExperience = response.EducationalExperiences.First().IdEducationalExperience;
                foreach (var item in EducationalProgramsDataGrid.Items)
                {
                    if (item is EducationalProgram educationalProgram)
                    {
                        bool isSelected = educationalProgram.IsSelected;
                        if (isSelected)
                        {
                            int idEducationalProgram = educationalProgram.IdEducationalProgram;
                            var syllabus = new Syllabus()
                            {
                                IdEducationalProgram = idEducationalProgram,
                                IdEducationalExperience = idEducationalExperience
                            };
                            LogSyllabus(syllabus);
                        }
                    }
                }
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
            }
        }

        private async void LogSyllabus(Syllabus syllabus)
        {
            Response response = await SyllabusService.Post(syllabus);
            if (response.Code == (int)HttpStatusCode.Created)
            {
                Console.WriteLine(Properties.Resources.REGISTERED_INFORMATION_LABEL); 
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
            }
        }
    }
}