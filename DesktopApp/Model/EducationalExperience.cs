using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class EducationalExperience
    {
        [JsonProperty("idEducationalExperience")]
        public int IdEducationalExperience { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }
    }
}