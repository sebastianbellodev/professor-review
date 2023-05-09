using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class Faculty
    {
        public int IdFaculty { get; set; }
        public string Name { get; set; }
        public List<EducationalProgram> EducationalPrograms { get; set; }
        public List<Professor> Professors { get; set; }
    }
}