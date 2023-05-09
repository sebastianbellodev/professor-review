using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class EducationalProgram
    {
        public int IdEducationalProgram { get; set; }
        public string Name { get; set; }
        public Faculty Faculty { get; set; }
        public List<EducationalExperience> EducationalExperiences { get; set; }
        public List<Student> Students { get; set; }
    }
}