using ProfessorPerformanceEvaluation.BusinessLogic;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
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
            if (int.Parse(stringValue) == 1) {
                return true;
            }
            return false;
        }


        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            bool boolValue = (bool)value;
            return boolValue ? "1" : "0";
        }

    }
    public partial class StudentAccessManagement : Window
    {
        List<Student> studentsToUpdate = new List<Student>();
        public StudentAccessManagement()
        {
            InitializeComponent();
            this.ComboBox_Faculties.ItemsSource = this.GetFaculties();
        }


        private List<Faculty> GetFaculties()
        {
            List<Faculty> faculties = new List<Faculty>();
            //Generar la petición y parsear los datos
            Faculty fac = new Faculty();
            fac.Name = "Test1";
            Faculty fac2 = new Faculty();
            fac2.Name = "Test2";
            faculties.Add(fac);
            faculties.Add(fac2);

            return faculties;
        }

        private void Button_GenerateReport_Click(object sender, RoutedEventArgs e)
        {

        }

        private void Button_Back_Click(object sender, RoutedEventArgs e)
        {

        }

        private List<Student> GetStudent(int idFaculty) {
            List<Student> students = new List<Student>();
            Student s1 = new Student();
            s1.Name = "s1 name";
            s1.Active = "1";
            students.Add(s1);
            Student s2 = new Student();
            s2.Name = "s2 name";
            s2.Active = "0";
            students.Add(s2);
            return students;
        
        }



        private void ComboBox_Faculties_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Faculty selectedFaculty = ComboBox_Faculties.SelectedItem as Faculty;
            if (selectedFaculty != null)
            {
                this.DataGrid_Student.ItemsSource = this.GetStudent(selectedFaculty.IdFaculty);
            }
        }

        private void RadioButton_Checked(object sender, RoutedEventArgs e)
        {
            CheckBox checkBox = sender as CheckBox;
            Student student = checkBox.DataContext as Student;

            if (!this.studentsToUpdate.Contains(student))
            {
                this.studentsToUpdate.Add(student);
            }
            else if (int.Parse(student.Active) == 0)
            {
                this.studentsToUpdate.Remove(student);
            }
        }

        private void RadioButton_Unchecked(object sender, RoutedEventArgs e)
        {
            CheckBox checkBox = sender as CheckBox;
            Student student = checkBox.DataContext as Student;

            if (int.Parse(student.Active) == 1)
            {
                this.studentsToUpdate.Remove(student);
            }
            else if (!this.studentsToUpdate.Contains(student))
            {
                this.studentsToUpdate.Add(student);
            }
        }
    }
}
