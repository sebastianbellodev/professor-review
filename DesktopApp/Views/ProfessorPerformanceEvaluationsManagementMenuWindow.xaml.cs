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
    /// Lógica de interacción para ProfessorPerformanceEvaluationsManagementMenuWindow.xaml
    /// </summary>
    public partial class ProfessorPerformanceEvaluationsManagementMenuWindow : Window
    {
        public ProfessorPerformanceEvaluationsManagementMenuWindow()
        {
            InitializeComponent();
        }

        private void DeleteProfessorPerformanceEvaluationButtonClick(object sender, RoutedEventArgs e)
        {
            var deleteReview = new DeleteReview();
            this.Close();
            deleteReview.Show();
        }

        private void ReturnButtonClick(object sender, RoutedEventArgs e)
        {
            var mainMenuWindow = new MainMenuWindow();
            Close();
            mainMenuWindow.Show();
        }
    }
}
