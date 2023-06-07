using Newtonsoft.Json;
using ProfessorPerformanceEvaluation.Model;
using System.Net.Http.Headers;
using System.Net.Http;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System;
using ProfessorPerformanceEvaluation.Utilities;

namespace ProfessorPerformanceEvaluation.Service
{
    public class EducationalExperienceService
    {
        private static readonly string URL = string.Concat(Properties.Resources.BASE_URL, "educationalexperiences/");

        public static async Task<Response> GetEducationalExperienceById(EducationalExperience educationalExperience)
        {
            string url = string.Concat(URL, "id");
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", Credentials.BEARER_TOKEN);
                    var httpRequestMessage = new HttpRequestMessage()
                    {
                        Content = new StringContent(JsonConvert.SerializeObject(educationalExperience), Encoding.UTF8, "application/json"),
                        Method = HttpMethod.Get,
                        RequestUri = new Uri(url)
                    };
                    HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);
                    if (httpResponseMessage != null)
                    {
                        string content = await httpResponseMessage.Content.ReadAsStringAsync();
                        response = JsonConvert.DeserializeObject<Response>(content);
                    }
                }
                catch (Exception exception)
                {
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    response.Message = exception.Message;
                }
            }
            return response;
        }

        public static async Task<Response> GetEducationalExperiences()
        {
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", Credentials.BEARER_TOKEN);
                    var httpRequestMessage = new HttpRequestMessage(HttpMethod.Get, new Uri(URL));
                    HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);
                    if (httpResponseMessage != null)
                    {
                        string content = await httpResponseMessage.Content.ReadAsStringAsync();
                        response = JsonConvert.DeserializeObject<Response>(content);
                    }
                }
                catch (Exception exception)
                {
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    response.Message = exception.Message;
                }
            }
            return response;
        }

        public static async Task<Response> GetEducationalExperiencesByFaculty(EducationalExperience educationalExperience)
        {
            string url = string.Concat(URL, "faculty");
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", Credentials.BEARER_TOKEN);
                    var httpRequestMessage = new HttpRequestMessage()
                    {
                        Content = new StringContent(JsonConvert.SerializeObject(educationalExperience), Encoding.UTF8, "application/json"),
                        Method = HttpMethod.Get,
                        RequestUri = new Uri(url)
                    };
                    HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);
                    if (httpResponseMessage != null)
                    {
                        string content = await httpResponseMessage.Content.ReadAsStringAsync();
                        response = JsonConvert.DeserializeObject<Response>(content);
                    }
                }
                catch (Exception exception)
                {
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    response.Message = exception.Message;
                }
            }
            return response;
        }

        public static async Task<Response> Patch(EducationalExperience educationalExperience)
        {
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", Credentials.BEARER_TOKEN);
                    var httpRequestMessage = new HttpRequestMessage()
                    {
                        Content = new StringContent(JsonConvert.SerializeObject(educationalExperience), Encoding.UTF8, "application/json"),
                        Method = new HttpMethod("PATCH"),
                        RequestUri = new Uri(URL)
                    };
                    HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);
                    if (httpResponseMessage != null)
                    {
                        string content = await httpResponseMessage.Content.ReadAsStringAsync();
                        response = JsonConvert.DeserializeObject<Response>(content);
                    }
                }
                catch (Exception exception)
                {
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    response.Message = exception.Message;
                }
            }
            return response;
        }

        public static async Task<Response> Post(EducationalExperience educationalExperience)
        {
            Response response = new Response();
            using (var httpClient = new HttpClient())
            {
                try
                {
                    httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", Credentials.BEARER_TOKEN);
                    var httpRequestMessage = new HttpRequestMessage()
                    {
                        Content = new StringContent(JsonConvert.SerializeObject(educationalExperience), Encoding.UTF8, "application/json"),
                        Method = HttpMethod.Post,
                        RequestUri = new Uri(URL)
                    };
                    HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);
                    if (httpResponseMessage != null)
                    {
                        string content = await httpResponseMessage.Content.ReadAsStringAsync();
                        response = JsonConvert.DeserializeObject<Response>(content);
                    }
                }
                catch (Exception exception)
                {
                    response.Code = (int)HttpStatusCode.InternalServerError;
                    response.Message = exception.Message;
                }
            }
            return response;
        }
    }
}