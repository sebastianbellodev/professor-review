using Newtonsoft.Json;
using System.Windows.Controls;

namespace ProfessorPerformanceEvaluation.Model
{
    public class EducationalExperience
    {
        [JsonProperty("idEducationalExperience")]
        public int IdEducationalExperience { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }

        public override string ToString()
        {
            return Name;
        }
    }
}