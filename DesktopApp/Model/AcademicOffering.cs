using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class AcademicOffering
    {
        [JsonProperty("idAcademicOffering")]
        public int IdAcademicOffering { get; set; }
        [JsonProperty("idSyllabus")]
        public int IdSyllabus { get; set; }
        [JsonProperty("idProfessor")]
        public int IdProfessor { get; set; }
    }
}