using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
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
    /// Lógica de interacción para UpdateFaculty.xaml
    /// </summary>
    public partial class UpdateFaculty : Window
    {
        private Faculty newFaculty = new Faculty();

        public UpdateFaculty()
        {
            InitializeComponent();
        }

        public void setView(Faculty faculty)
        {
            this.newFaculty = faculty;
        }

        private void update_Click(object sender, RoutedEventArgs e)
        {
            String facultyName = this.txt_facultyName.Text;
            
        }

        private void back_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
