using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System;
using System.Collections.Generic;
using System.Net;
using System.Windows;

namespace ProfessorPerformanceEvaluation.Views
{
    public partial class ModifyEducationalExperienceWindow : Window
    {
        private List<Syllabus> syllabuses;

        public ModifyEducationalExperienceWindow()
        {
            InitializeComponent();
            GetEducationalExperiences();
            GetEducationalPrograms();
        }

        private async void GetEducationalExperiences()
        {
            Response response = await EducationalExperienceService.GetEducationalExperiences();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<EducationalExperience> educationalExperiences = response.EducationalExperiences;
                EducationalExperienceComboBox.ItemsSource = educationalExperiences;
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
            var educationalProgramAdministrationMenuWindow = new EducationalProgramManagementMenuWindow();
            Close();
            educationalProgramAdministrationMenuWindow.Show();
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

        private void EducationalExperienceComboBoxSelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            EducationalExperience educationalExperience = EducationalExperienceComboBox.SelectedItem as EducationalExperience;
            NameTextBox.Text = educationalExperience.Name;
            NameTextBox.IsEnabled = true;
            GetSyllabusesByEducationalExperience(educationalExperience);
        }

        private async void GetSyllabusesByEducationalExperience(EducationalExperience educationalExperience)
        {
            Response response = await SyllabusService.GetSyllabusesByEducationalExperience(educationalExperience);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                syllabuses = response.Syllabuses;
                ConfigureEducationalProgramsByEducationalExperience();
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

        private void ConfigureEducationalProgramsByEducationalExperience()
        {
            foreach (var item in EducationalProgramsDataGrid.Items)
            {
                if (item is EducationalProgram educationalProgram)
                {
                    educationalProgram.IsSelected = false;
                    foreach (Syllabus syllabus in syllabuses)
                    {
                        if (educationalProgram.IdEducationalProgram == syllabus.IdEducationalProgram)
                        {
                            educationalProgram.IsSelected = true;
                            break;
                        }
                    }
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
                EducationalExperience educationalExperience = EducationalExperienceComboBox.SelectedItem as EducationalExperience;
                educationalExperience.Name = (educationalExperience.Name == name) ? null : name;
                ModifyEducationalExperience(educationalExperience);
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                    Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private async void ModifyEducationalExperience(EducationalExperience educationalExperience)
        {
            Response response = await EducationalExperienceService.Patch(educationalExperience);
            switch (response.Code)
            {
                case (int)HttpStatusCode.OK:
                    MessageBox.Show(Properties.Resources.MODIFIED_INFORMATION_LABEL);
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

        private void LogSyllabuses(EducationalExperience educationalExperience)
        {
            foreach (var item in EducationalProgramsDataGrid.Items)
            {
                if (item is EducationalProgram educationalProgram)
                {
                    bool isSelected = educationalProgram.IsSelected;
                    bool isRegistered = false;
                    foreach (Syllabus syllabus in syllabuses)
                    {
                        if (educationalProgram.IdEducationalProgram == syllabus.IdEducationalProgram)
                        {
                            isRegistered = true;
                            break;
                        }
                    }
                    if (isSelected && !isRegistered)
                    {
                        int idEducationalProgram = educationalProgram.IdEducationalProgram;
                        int idEducationalExperience = educationalExperience.IdEducationalExperience;
                        var syllabus = new Syllabus()
                        {
                            IdEducationalProgram = idEducationalProgram,
                            IdEducationalExperience = idEducationalExperience
                        };
                        LogSyllabus(syllabus);
                    }
                    else if (!isSelected && isRegistered)
                    {
                        Syllabus syllabus = GetSyllabusByEducationalProgram(educationalProgram);
                        DeleteSyllabus(syllabus);
                    }
                }
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

        private Syllabus GetSyllabusByEducationalProgram(EducationalProgram educationalProgram)
        {
            int idEducationalProgram = educationalProgram.IdEducationalProgram;
            foreach (Syllabus syllabus in syllabuses)
            {
                if (syllabus.IdEducationalProgram == idEducationalProgram)
                {
                    return syllabus;
                }
            }
            return null;
        }

        private async void DeleteSyllabus(Syllabus syllabus)
        {
            Response response = await SyllabusService.Delete(syllabus);
            if (response.Code == (int)HttpStatusCode.OK)
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