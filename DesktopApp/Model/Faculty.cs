using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class Faculty
    {
        [JsonProperty("idFaculty")]
        public int IdFaculty { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }
    }
}