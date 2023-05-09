using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class AcademicOffering
    {
        public EducationalExperience EducationalExperience { get; set; }
        public Professor Professor { get; set; }
        public List<Review> Reviews { get; set; }
    }
}