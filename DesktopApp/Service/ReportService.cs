using Newtonsoft.Json;
using ProfessorPerformanceEvaluation.Model;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace ProfessorPerformanceEvaluation.Service
{
    public class ReportService
    {
        private static readonly string TOKEN = ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location).AppSettings.Settings["TOKEN"].Value;
        private static readonly string URL = string.Concat(Properties.Resources.BASE_URL, "reports/");

        public static async Task<Response> GetReportByProfessor(Professor professor)
        {
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", TOKEN);
                    var httpRequestMessage = new HttpRequestMessage()
                    {
                        Content = new StringContent(JsonConvert.SerializeObject(professor), Encoding.UTF8, "application/json"),
                        Method = HttpMethod.Post,
                        RequestUri = new Uri(string.Concat(URL, "professor"))
                    };
                    HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);
                    if (httpResponseMessage != null)
                    {
                        if (httpResponseMessage.IsSuccessStatusCode)
                        {
                            string json = await httpResponseMessage.Content.ReadAsStringAsync();
                            response = JsonConvert.DeserializeObject<Response>(json);
                        }
                        response.Code = (int)httpResponseMessage.StatusCode;
                    }
                }
                catch (Exception exception)
                {
                    Console.WriteLine(exception.Message);
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    Console.WriteLine(exception.Message);
                }
            }
            return response;
        }
    }
}
