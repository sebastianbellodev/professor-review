using Newtonsoft.Json;
using System.Collections.Generic;

namespace ProfessorPerformanceEvaluation.Model
{
    public class Response
    {
        [JsonProperty("message")]
        public string Message { get; set; }
        [JsonProperty("token")]
        public string Token { get; set; }
        [JsonProperty("educationalExperience")]
        public EducationalExperience EducationalExperience { get; set; }
        [JsonProperty("educationalExperiences")]
        public List<EducationalExperience> EducationalExperiences { get; set; }
        [JsonProperty("educationalProgram")]
        public EducationalProgram EducationalProgram { get; set; }
        [JsonProperty("educationalPrograms")]
        public List<EducationalProgram> EducationalPrograms { get; set; }
        [JsonProperty("faculty")]
        public Faculty Faculty { get; set; }
        [JsonProperty("faculties")]
        public List<Faculty> Faculties { get; set; }
        [JsonProperty("professor")]
        public Professor Professor { get; set; }
        [JsonProperty("professors")]
        public List<Professor> Professors { get; set; }
        [JsonProperty("review")]
        public Review Review { get; set; }
        [JsonProperty("reviews")]
        public List<Review> Reviews { get; set; }
        [JsonProperty("schoolPeriod")]
        public SchoolPeriod SchoolPeriod { get; set; }
        [JsonProperty("schoolPeriods")]
        public List<SchoolPeriod> SchoolPeriods { get; set; }
        [JsonProperty("student")]
        public Student Student { get; set; }
        [JsonProperty("students")]
        public List<Student> Students { get; set; }
        [JsonProperty("syllabus")]
        public Syllabus Syllabus { get; set; }
        [JsonProperty("syllabuses")]
        public List<Syllabus> Syllabuses { get; set; }
        [JsonProperty("user")]
        public User User { get; set; }
        [JsonProperty("users")]
        public List<User> Users { get; set; }
    }
}