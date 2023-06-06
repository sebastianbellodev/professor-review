using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class User
    {
        [JsonProperty("username")]
        public string Username { get; set; }
        [JsonProperty("password")]
        public string Password { get; set; }
        [JsonProperty("registrationNumber")]
        public string RegistrationNumber { get; set; }
    }
}