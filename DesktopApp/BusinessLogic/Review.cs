namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class Review
    {
        public int IdReview { get; set; }
        public int Stars { get; set; }
        public string Comment { get; set; }
        public AcademicOffering AcademicOffering { get; set; }
        public SchoolPeriod SchoolPeriod { get; set; }
        public Student Student { get; set; }
    }
}