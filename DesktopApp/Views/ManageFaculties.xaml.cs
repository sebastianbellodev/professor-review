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
    /// Lógica de interacción para ManageFaculties.xaml
    /// </summary>
    public partial class ManageFaculties : Window
    {
        public ManageFaculties()
        {
            InitializeComponent();
            LoadDataGrid();
        }

        private async void LoadDataGrid()
        {
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                List<Faculty> faculties = response.Faculties;
                DataGridFaculties.ItemsSource = faculties;
                //DataGridFaculties.DataContext = faculties; --Ver que funciona mejor
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }

        }

        private void RegisterButtonClick(object sender, RoutedEventArgs e)
        {
            RegisterFaculty registerFaculty = new RegisterFaculty();
            registerFaculty.Show();
        }

        private void UpdateButtonClick(object sender, RoutedEventArgs e)
        {
            UpdateFaculty updateFaculty = new UpdateFaculty();
            Faculty facultyDataGrid = new Faculty();
            if (VerifySelectedFacultyDataGrid())
            {                
                facultyDataGrid = this.DataGridFaculties.SelectedItem as Faculty;
                
                updateFaculty.SetView(facultyDataGrid);
                updateFaculty.Show();
            }

        }
        
        private Boolean VerifySelectedFacultyDataGrid()
        {
            Boolean result = false;
            if (DataGridFaculties.SelectedIndex != -1)
            {
                result = true;
            }
            return result;
        }

        private void DeleteButtonClick(object sender, RoutedEventArgs e)
        {
            Faculty selectedFacultyDataGrid = new Faculty();
            if (VerifySelectedFacultyDataGrid())
            {
                selectedFacultyDataGrid = this.DataGridFaculties.SelectedItem as Faculty;
                DeleteFaculty(selectedFacultyDataGrid);
            }
            else
            {
                MessageBox.Show(Properties.Resources.EMPTY_FIELDS_LABEL);
            }
        }

        private async void DeleteFaculty(Faculty faculty)
        {
            Response response = await FacultyService.Delete(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                MessageBox.Show(Properties.Resources.DELETED_INFORMATION_LABEL);
                this.Close(); ;
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                Close();
            }
        }
        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
