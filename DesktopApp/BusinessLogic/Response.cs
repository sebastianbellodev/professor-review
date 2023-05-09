namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class Response
    {
        public bool Error { get; set; }
        public string Message { get; set; }
        public AcademicOffering AcademicOffering { get; set; }
        public EducationalExperience EducationalExperience { get; set; }
        public EducationalProgram EducationalProgram { get; set; }
        public Faculty Faculty { get; set; }
        public Professor Professor { get; set; }
        public Review Review { get; set; }
        public SchoolPeriod SchoolPeriod { get; set; }
        public Student Student { get; set; }
        public User User { get; set; }
    }
}