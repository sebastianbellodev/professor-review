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
    /// Lógica de interacción para UpdateFaculty.xaml
    /// </summary>
    public partial class UpdateFaculty : Window
    {
        private Faculty selectedFaculty = new Faculty();

        public UpdateFaculty()
        {
            InitializeComponent();
        }

        public void SetView(Faculty faculty)
        {
            this.selectedFaculty = faculty;
        }

        private void Update_Click(object sender, RoutedEventArgs e)
        {
            if (EmptyFields()) {
                var faculty = new Faculty()
                {
                    Name = this.txt_facultyName.Text,
                    IdFaculty = this.selectedFaculty.IdFaculty
                };
                UpdateFacultyName(faculty);
            }
            else
            {
                MessageBox.Show(Properties.Resources.CHECK_ENTERED_INFORMATION_LABEL,
                   Properties.Resources.EMPTY_FIELDS_LABEL);
            }

        }

        private Boolean EmptyFields()
        {
            Boolean result = false;
            if (!string.IsNullOrEmpty(this.txt_facultyName.Text))
            {
                result = true;
            }
            return result;
        }
        
        private async void UpdateFacultyName(Faculty faculty)
        {
            Response response = await FacultyService.Patch(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                MessageBox.Show(Properties.Resources.REGISTERED_INFORMATION_LABEL);
                this.Close();
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                this.Close();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                this.Close();
            }
        }

        private void Back_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
