using Newtonsoft.Json;
using System;

namespace ProfessorPerformanceEvaluation.Model
{
    public class SchoolPeriod
    {
        [JsonProperty("idSchoolPeriod")]
        public int IdSchoolPeriod { get; set; }
        [JsonProperty("startDate")]
        public DateTime StartDate { get; set; }
        [JsonProperty("endDate")]
        public DateTime EndDate { get; set; }
    }
}