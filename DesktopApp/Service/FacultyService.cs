using Newtonsoft.Json;
using ProfessorPerformanceEvaluation.Model;
using System.Configuration;
using System.Net.Http.Headers;
using System.Net.Http;
using System.Net;
using System.Reflection;
using System.Threading.Tasks;
using System;

namespace ProfessorPerformanceEvaluation.Service
{
    public class FacultyService
    {
        private static readonly string TOKEN = ConfigurationManager.OpenExeConfiguration(Assembly.GetExecutingAssembly().Location).AppSettings.Settings["TOKEN"].Value;
        private static readonly string URL = string.Concat(Properties.Resources.BASE_URL, "faculties/");

        public static async Task<Response> GetFaculties()
        {
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", TOKEN);
                    var httpRequestMessage = new HttpRequestMessage(HttpMethod.Get, new Uri(URL));
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