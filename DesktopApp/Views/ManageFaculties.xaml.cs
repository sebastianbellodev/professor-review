using ProfessorPerformanceEvaluation.Model;
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
    /// Lógica de interacción para ManageFaculties.xaml
    /// </summary>
    public partial class ManageFaculties : Window
    {
        public ManageFaculties()
        {
            InitializeComponent();
            loadDataGrid();
        }

        private void loadDataGrid()
        {
            DataGridFaculties.DataContext = null;
            //DataGridFaculties.DataContext = 
        }

        private void register_Click(object sender, RoutedEventArgs e)
        {
            RegisterFaculty registerFaculty = new RegisterFaculty();
            registerFaculty.Show();
        }

        private void update_Click(object sender, RoutedEventArgs e)
        {
            if (verifySelectedFacultyDataGrid())
            {
                Faculty facultyDataGrid = new Faculty();
                facultyDataGrid = this.DataGridFaculties.SelectedItem as Faculty;

                UpdateFaculty updateFaculty = new UpdateFaculty();
                updateFaculty.setView(facultyDataGrid);
                updateFaculty.Show();
            }

        }
        
        private Boolean verifySelectedFacultyDataGrid()
        {
            Boolean result = false;
            if (DataGridFaculties.SelectedIndex != -1)
            {
                result = true;
            }
            return result;
        }

    }
}
