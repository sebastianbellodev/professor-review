using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class Professor
    {
        public int IdProfessor { get; set; }
        public string Name { get; set; }
        public string PaternalSurname { get; set; }
        public string MaternalSurname { get; set; }
        public List<AcademicOffering> AcademicOfferings { get; set; }
        public List<Faculty> Faculties { get; set; }
    }
}