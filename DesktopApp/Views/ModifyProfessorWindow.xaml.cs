using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Linq;
using System.Linq;
using System.Net;
using System.Reflection;
using System.Runtime.InteropServices;
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
    /// Interaction logic for ModifyProfessorWindow.xaml
    /// </summary>
    public partial class ModifyProfessorWindow : Window
    {
        List<Professor> UpdatedProfessor;
        public ModifyProfessorWindow()
        {
            InitializeComponent();
            LoadProfessorInformation();
            UpdatedProfessor = new List<Professor>();
        }

        public async void LoadProfessorInformation()
        {
            Response response = await ProfessorService.GetProfessors();
            List<Professor> professors = response.Professors;
            Console.WriteLine(response.Code);
            switch (response.Code)
            {
                case (int)HttpStatusCode.OK:
                    
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
            DataContext = new ViewModel { Professors = professors };
        }

        private void TextBoxSearch_TextChanged(object sender, TextChangedEventArgs e)
        {
            
        }

        private void DataGrid_CellEditEnding(object sender, DataGridCellEditEndingEventArgs cell)
        {
            if (cell.Row.Item is Professor professor)
            {
                var Name = ((TextBox)cell.EditingElement).Text;
                var NameBindingPath = ((Binding)((DataGridTextColumn)cell.Column).Binding).Path.Path;
                var LastName = ((TextBox)cell.EditingElement).Text;
                var LastNameBindingPath = ((Binding)((DataGridTextColumn)cell.Column).Binding).Path.Path;

                var NameProperty = professor.GetType().GetProperty(NameBindingPath);
                NameProperty.SetValue(professor, Name);
                var LastNameProperty = professor.GetType().GetProperty(LastNameBindingPath);
                LastNameProperty.SetValue(professor, LastName);

                UpdatedProfessor.Add(professor);
            }
        }

        private void AcceptButtonClick(object sender, RoutedEventArgs e)
        {
            Patch();
        }

        private async void Patch()
        {
            if (UpdatedProfessor.Count >= 0)
            {
                foreach (Professor professor in UpdatedProfessor)
                {
                    Response response = await ProfessorService.Patch(professor);
                    switch (response.Code)
                    {
                        case (int)HttpStatusCode.OK:
                            break;
                        case (int)HttpStatusCode.NotFound:
                            MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                                Properties.Resources.INVALID_DATA_LABEL);
                            GoToEducationalProgramAdministrationMenu();
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

                MessageBox.Show(Properties.Resources.MODIFIED_INFORMATION_LABEL);
                GoToEducationalProgramAdministrationMenu();
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
    }

    public class ViewModel
    {
        public List<Professor> Professors { get; set; }
    }
}
