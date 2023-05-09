using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class Student
    {
        public int RegistrationNumber { get; set; }
        public string Name { get; set; }
        public string PaternalSurname { get; set; }
        public string MaternalSurname { get; set; }
        public string EmailAddress { get; set; }
        public string Biography { get; set; }
        public EducationalProgram EducationalProgram { get; set; }
        public User User { get; set; }
        public List<Review> Reviews { get; set; }
    }
}