using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class EducationalExperience
    {
        public int IdEducationalExperience { get; set; }
        public string Name { get; set; }
        public List<AcademicOffering> AcademicOfferings { get; set; }
        public List<EducationalProgram> EducationalPrograms { get; set; }
    }
}