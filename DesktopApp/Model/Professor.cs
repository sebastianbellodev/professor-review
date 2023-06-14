using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class Professor
    {
        [JsonProperty("idProfessor")]
        public int IdProfessor { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("lastName")]
        public string LastName { get; set; }
    }
}