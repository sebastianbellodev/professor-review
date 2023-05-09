using System;
using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.BusinessLogic
{
    public class SchoolPeriod
    {
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public List<Review> Reviews { get; set; }
    }
}