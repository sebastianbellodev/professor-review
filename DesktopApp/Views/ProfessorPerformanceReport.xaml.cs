using ProfessorPerformanceEvaluation.BusinessLogic;
using System;
using System.Collections.Generic;
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
    /// Lógica de interacción para ProfessorPerformanceReport.xaml
    /// </summary>
    public partial class ProfessorPerformanceReport : Window
    {
        public ProfessorPerformanceReport()
        {
            InitializeComponent();
            this.ComboBox_Faculties.ItemsSource = this.GetFaculties();
        }
        private List<Professor> GetProfessors(int idFaculty) {
            List<Professor> professors = new List<Professor>();
            Professor p1 = new Professor();
            Professor p2 = new Professor();
            p1.Name = "name test 2";
            p2.Name = "name test 1";
            professors.Add(p1);
            professors.Add(p2);
            return professors;
        } 

        private List<Faculty> GetFaculties() {
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

        private void ComboBox_Faculties_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Faculty selectedFaculty = ComboBox_Faculties.SelectedItem as Faculty;
            if (selectedFaculty != null) {
                this.DataGrid_Professor.ItemsSource = this.GetProfessors(selectedFaculty.IdFaculty);
            }
        }


        private void Button_Back_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
