using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System;
using System.Collections.Generic;
using System.Globalization;
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
    /// Lógica de interacción para StudentAccessManagement.xaml
    /// </summary>
    /// 

    public class StringToBooleanConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            string stringValue = value as string;
            if ((int)value == 1) {
                return true;
            }
            return false;
        }


        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            bool boolValue = (bool)value;
            return boolValue ? 1 : 0;
        }

    }
    public partial class StudentAccessManagement : Window
    {
        private List<Student> studentsToUpdate = new List<Student>();
        public StudentAccessManagement()
        {
            InitializeComponent();
            this.GetFaculties();
        }


        private async void GetFaculties()
        {
            List<Faculty> faculties = new List<Faculty>();
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                faculties = response.Faculties;
                this.ComboBox_Faculties.ItemsSource = faculties;
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                this.GoBack();
            }
            else {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                this.GoBack();
            }
        }

        private void GoBack() 
        { 
            //Nav Service
        }

        private async void Button_UpdateStatus_Click(object sender, RoutedEventArgs e)
        {
            Response response = await StudentService.UpdateStatus(this.studentsToUpdate);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                MessageBox.Show(Properties.Resources.REGISTERED_INFORMATION_LABEL, "");
                this.studentsToUpdate.Clear();
                Faculty selectedFaculty = ComboBox_Faculties.SelectedItem as Faculty;
                this.GetStudents(selectedFaculty);                
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                this.GoBack();
            }
            else
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                   Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                this.GoBack();
            }
        }

        private void Button_Back_Click(object sender, RoutedEventArgs e)
        {
            this.GoBack();
        }

        private async void GetStudents(Faculty faculty) {
            List<Student> students = new List<Student>();
            Response response = await StudentService.GetStudentsByFaculty(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                students = response.Students;
                this.DataGrid_Student.ItemsSource = students;
            }
            else if (response.Code == (int)HttpStatusCode.Forbidden)
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                    Properties.Resources.EXPIRED_SESSION_LABEL);
                this.GoBack();          
            }
            else 
            {
                MessageBox.Show(Properties.Resources.TRY_AGAIN_LATER_LABEL,
                   Properties.Resources.SERVICE_NOT_AVAILABLE_LABEL);
                this.GoBack();
            }              
        }

        private void ComboBox_Faculties_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Faculty selectedFaculty = ComboBox_Faculties.SelectedItem as Faculty;
            if (selectedFaculty != null)
            {
                this.GetStudents(selectedFaculty);
            }
        }

        private void RadioButton_Checked(object sender, RoutedEventArgs e)
        {
            CheckBox checkBox = sender as CheckBox;
            Student student = checkBox.DataContext as Student;

            if (this.studentsToUpdate.Contains(student) && student.Active == 1)
            {
                this.studentsToUpdate.Remove(student);
            }
            else if (!(student.Active == 1) && !this.studentsToUpdate.Contains(student))
            {
                this.studentsToUpdate.Add(student);            
            }
        }

        private void RadioButton_Unchecked(object sender, RoutedEventArgs e)
        {
            CheckBox checkBox = (CheckBox)sender;
            Student student = (Student)checkBox.DataContext;

            if (!this.studentsToUpdate.Contains(student) && student.Active == 1)
            {
                this.studentsToUpdate.Add(student);
            }
            else if (!(student.Active == 1) && this.studentsToUpdate.Contains(student))
            {
                this.studentsToUpdate.Remove(student);
            }
        }
    }
}
