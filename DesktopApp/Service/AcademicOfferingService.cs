using Newtonsoft.Json;
using ProfessorPerformanceEvaluation.Model;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net.Http.Headers;
using System.Net.Http;
using System.Net;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace ProfessorPerformanceEvaluation.Service
{
    internal class AcademicOfferingService
    {
        private static readonly string TOKEN = ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location).AppSettings.Settings["TOKEN"].Value;
        private static readonly string URL = string.Concat(Properties.Resources.BASE_URL, "academicofferings/");

        public static async Task<Response> Post(AcademicOffering academicOffering)
        {
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", TOKEN);
                    var httpRequestMessage = new HttpRequestMessage()
                    {
                        Content = new StringContent(JsonConvert.SerializeObject(academicOffering), Encoding.UTF8, "application/json"),
                        Method = HttpMethod.Post,
                        RequestUri = new Uri(URL)
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
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    Console.WriteLine(exception.Message);
                }
            }
            return response;
        }
    }
}
