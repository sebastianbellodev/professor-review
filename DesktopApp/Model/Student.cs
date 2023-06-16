using Newtonsoft.Json;
using System;

namespace ProfessorPerformanceEvaluation.Model
{
    public class Student
    {
        [JsonProperty("registrationNumber")]
        public string RegistrationNumber { get; set; }
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("lastName")]
        public string LastName { get; set; }
        [JsonProperty("emailAddress")]
        public string EmailAddress { get; set; }
        [JsonProperty("phoneNumber")]
        public string PhoneNumber { get; set; }
        [JsonProperty("biography")]
        public string Biography { get; set; }
        [JsonProperty("active")]
        public int Active { get; set; }/*
        [JsonProperty("activationDate")]
        public DateTime ActivationDate { get; set; }`*/
        [JsonProperty("oneTimePassword")]
        public string OneTimePassword { get; set; }
        [JsonProperty("idEducationalProgram")]
        public int IdEducationalProgram { get; set; }
    }
}