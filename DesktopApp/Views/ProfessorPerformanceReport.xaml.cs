using ProfessorPerformanceEvaluation.Model;
using ProfessorPerformanceEvaluation.Service;
using System.Collections.Generic;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.IO;
using Microsoft.Win32;
using iTextSharp.text.pdf;
using iTextSharp.text;
using Newtonsoft.Json;

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
            this.GetFaculties();
            //this.ComboBox_Faculties.ItemsSource = (System.Collections.IEnumerable)this.GetFacultiesAsync();
        }
        private async void GetProfessors(Faculty faculty) {
            Response response = await ProfessorService.GetProfessorsByFaculty(faculty);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                this.DataGrid_Professor.ItemsSource = response.Professors;
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

        private async void GetFaculties()
        {
            List<Faculty> faculties = new List<Faculty>();
            Response response = await FacultyService.GetFaculties();
            if (response.Code == (int)HttpStatusCode.OK)
            {
                this.ComboBox_Faculties.ItemsSource = response.Faculties;
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
            //Navigation Service
        }

        private void ComboBox_Faculties_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Faculty selectedFaculty = ComboBox_Faculties.SelectedItem as Faculty;
            if (selectedFaculty != null) {
                this.GetProfessors(selectedFaculty);
            }
        }


        private void Button_Back_Click(object sender, RoutedEventArgs e)
        {
            this.GoBack();
        }

        private void Button_GenerateReport_Click(object sender, RoutedEventArgs e)
        {
            Professor selectedProfessor = (Professor)this.DataGrid_Professor.SelectedItem;
            if (selectedProfessor != null)
            {
                this.GetReportInformation(selectedProfessor); 
            
            }
        }

        private void GeneratePDF(List<Report> reportData) {
            string htmlContent = this.GetHTMLFormat(reportData);
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.Filter = "Archivos PDF|*.pdf";
            saveFileDialog.Title = "Guardar archivo PDF";
            saveFileDialog.FileName = "Report";
            saveFileDialog.CheckFileExists = false;
            saveFileDialog.CheckPathExists = true;
            if (saveFileDialog.ShowDialog() == true)
            {
                string pdfRoute = saveFileDialog.FileName;

                Document pdfReport = new Document();
                PdfWriter writer = PdfWriter.GetInstance(pdfReport, new FileStream(pdfRoute, FileMode.Create));
                pdfReport.Open();
                using (var reader = new StringReader(htmlContent))
                {
                    //var converter = new GroupDocs.Conversion.Converter("sample.html");
                    /*var parser = new HTMLWorker(pdfReport);
                    parser.Parse(reader);*/
                    //ITextSharp.tool.xml.XMLWorkerHelper.GetInstance().ParseXHtml(writer, pdfReport, reader);
                }
                pdfReport.Close();
            }
        }

        private string GetHTMLFormat(List<Report> reports) {
            string contenidoHtml = @"
<!DOCTYPE html>
<html>
<head>
    <script src='https://cdn.jsdelivr.net/npm/chart.js'></script>
    <style>
        .container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }
        .tabla {
            width: 50%;
        }
        .grafica {
            width: 40%;
        }
    </style>
</head>
<body>
    <div class='container'>
        <div class='tabla'>
            <table>
                <thead>
                    <tr>
                        <th>Clave</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>";

            // Agregar las filas de la tabla
            foreach (var report in reports)
            {
                contenidoHtml += $@"
                    <tr>
                        <td>{report.EducationalExperience}</td>
                        <td>{report.Reviews}</td>
                    </tr>";
            }

            contenidoHtml += @"
                </tbody>
            </table>
        </div>
        <div class='grafica'>
            <canvas id='graficaPastel'></canvas>
        </div>
    </div>

    <script>
        // Obtener los datos de la lista
        var datos = " + JsonConvert.SerializeObject(reports) + @";

        // Obtener las claves y los valores de la lista
        var claves = datos.map(report => report.EducationalExperience);
        var valores = datos.map(report => report.Reviews);

        // Configurar la gráfica de pastel
        var ctx = document.getElementById('graficaPastel').getContext('2d');
        var graficaPastel = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: claves,
                datasets: [{
                    data: valores,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.7)',
                        'rgba(54, 162, 235, 0.7)',
                        'rgba(255, 206, 86, 0.7)',
                        'rgba(75, 192, 192, 0.7)',
                        'rgba(153, 102, 255, 0.7)'
                    ]
                }]
            },
            options: {
                responsive: true
            }
        });
    </script>
</body>
</html>";

            return contenidoHtml;
        }

        private async void GetReportInformation(Professor professor)
        {
            List<Report> report = new List<Report>();
            Response response = await ReportService.GetReportByProfessor(professor);
            if (response.Code == (int)HttpStatusCode.OK)
            {
                report = response.Reports;
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
            this.GeneratePDF(report);
        }
    }
}
