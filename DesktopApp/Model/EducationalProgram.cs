using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class EducationalProgram
    {
        [JsonProperty("idEducationalProgram")]
        public int IdEducationalProgram { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("idFaculty")]
        public int IdFaculty { get; set; }
    }
}