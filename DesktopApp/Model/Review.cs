using Newtonsoft.Json;

namespace ProfessorPerformanceEvaluation.Model
{
    public class Review
    {
        [JsonProperty("idReview")]
        public int IdReview { get; set; }
        [JsonProperty("stars")]
        public int Stars { get; set; }
        [JsonProperty("comment")]
        public string Comment { get; set; }
        [JsonProperty("idSchoolPeriod")]
        public int IdSchoolPeriod { get; set; }
        [JsonProperty("idAcademicOffering")]
        public int IdAcademicOffering { get; set; }
        [JsonProperty("registrationNumber")]
        public string RegistrationNumber { get; set; }
    }
}