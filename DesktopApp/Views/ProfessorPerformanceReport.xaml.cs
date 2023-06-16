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
using iTextSharp.tool.xml;
using System.IO;
using Newtonsoft.Json;
using System.Linq;
using System.Windows.Forms.DataVisualization.Charting;

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

        private void GeneratePDF(List<Report> reportData, Professor professor) {
            //string htmlContent = this.GetHTMLFormat(reportData, professor);
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.Filter = "Archivos PDF|*.pdf";
            saveFileDialog.Title = "Guardar archivo PDF";
            saveFileDialog.FileName = "Report";
            saveFileDialog.CheckFileExists = false;
            saveFileDialog.CheckPathExists = true;


            if (saveFileDialog.ShowDialog() == true)
            {
                string pdfRoute = saveFileDialog.FileName;
                Document document = new Document();

                // Crear el escritor del documento
                PdfWriter writer = PdfWriter.GetInstance(document, new FileStream(pdfRoute, FileMode.Create));

                // Abrir el documento
                document.Open();

                // Agregar el título
                Paragraph title = new Paragraph("Reporte de Profesor", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
                title.Alignment = Element.ALIGN_CENTER;
                document.Add(title);

                // Agregar el nombre y apellido del profesor
                Paragraph professorName = new Paragraph($"{professor.Name} {professor.LastName}", new Font(Font.FontFamily.HELVETICA, 14));
                professorName.Alignment = Element.ALIGN_CENTER;
                document.Add(professorName);

                // Crear la tabla
                PdfPTable table = new PdfPTable(2);
                table.WidthPercentage = 100;
                table.SpacingBefore = 20f;
                table.SpacingAfter = 20f;

                // Agregar el encabezado de la tabla
                table.AddCell(new PdfPCell(new Phrase("Experiencia Educativa", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
                table.AddCell(new PdfPCell(new Phrase("Cantidad de Reportes", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));

                // Agregar las filas de la tabla
                foreach (var report in reportData)
                {
                    table.AddCell(new PdfPCell(new Phrase(report.EducationalExperience)));
                    table.AddCell(new PdfPCell(new Phrase(report.Reviews.ToString())));
                }

                // Agregar la tabla al documento
                document.Add(table);

                // Crear la gráfica de pastel
                var claves = reportData.Select(report => report.EducationalExperience).ToArray();
                var valores = reportData.Select(report => report.Reviews).ToArray();

                //Crear la gráfica
                // Crear la gráfica de pastel
                Chart chart = new Chart();
                chart.ChartAreas.Add(new ChartArea());
                chart.Series.Add(new Series());
                chart.Series[0].ChartType = SeriesChartType.Pie;

                // Agregar los puntos de datos a la gráfica
                foreach (var report in reportData)
                {
                    chart.Series[0].Points.AddXY(report.EducationalExperience, report.Reviews);
                }

                // Guardar la gráfica en un archivo de imagen
                string chartImagePath = "ruta_de_la_imagen.png";
                chart.SaveImage(chartImagePath, ChartImageFormat.Png);

                // Agregar la imagen de la gráfica al documento PDF
                iTextSharp.text.Image chartImage = iTextSharp.text.Image.GetInstance(chartImagePath);
                chartImage.Alignment = Element.ALIGN_CENTER;
                document.Add(chartImage);

                // Cerrar el documento
                document.Close();
            }            
        }

        private string GetHTMLFormat(List<Report> reports,Professor professor) {
            string contenidoHtml = $@"
<!DOCTYPE html>
<html>
<head>
    <style>
        .tabla {{
            width: 100%;
            border-collapse: collapse;
        }}

        .tabla th {{
            background-color: lightgray;
            font-weight: bold;
            border: 1px solid black;
            padding: 5px;
        }}

        .tabla td {{
            border: 1px solid black;
            padding: 5px;
        }}

        .titulo {{
            text-align: center;
            font-weight: bold;
        }}
    </style>
</head>
<body>
    <h2 class='titulo'>Reporte de Profesor</h2>
    <h3>{professor.Name} {professor.LastName}</h3>

    <table class='tabla'>
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
            this.GeneratePDF(report, professor);
        }
    }
}
