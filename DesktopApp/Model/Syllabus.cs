using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class Syllabus
    {
        [JsonProperty("idSyllabus")]
        public int IdSyllabus { get; set; }
        [JsonProperty("idEducationalProgram")]
        public int IdEducationalProgram { get; set; }
        [JsonProperty("idEducationalExperience")]
        public int IdEducationalExperience { get; set; }
    }
}