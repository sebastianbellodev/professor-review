package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    public int code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("educationalExperience")
    @Expose
    public EducationalExperience educationalExperience;
    @SerializedName("educationalExperiences")
    @Expose
    public List<EducationalExperience> educationalExperiences;
    @SerializedName("educationalProgram")
    @Expose
    public EducationalProgram educationalProgram;
    @SerializedName("educationalPrograms")
    @Expose
    public List<EducationalProgram> educationalPrograms;
    @SerializedName("faculty")
    @Expose
    public Faculty faculty;
    @SerializedName("faculties")
    @Expose
    public List<Faculty> faculties;
    @SerializedName("professor")
    @Expose
    public Professor professor;
    @SerializedName("professors")
    @Expose
    public List<Professor> professors;
    @SerializedName("review")
    @Expose
    public Review review;
    @SerializedName("reviews")
    @Expose
    public List<Review> reviews;
    @SerializedName("schoolPeriod")
    @Expose
    public SchoolPeriod schoolPeriod;
    @SerializedName("schoolPeriods")
    @Expose
    public List<SchoolPeriod> schoolPeriods;
    @SerializedName("student")
    @Expose
    public Student student;
    @SerializedName("students")
    @Expose
    public List<Student> students;
    @SerializedName("syllabus")
    @Expose
    public Syllabus syllabus;
    @SerializedName("syllabuses")
    @Expose
    public List<Syllabus> syllabuses;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("users")
    @Expose
    public List<User> users;
    @SerializedName("test")
    @Expose
    public Test test;
    @SerializedName("tests")
    @Expose
    public List<Test> tests;
    @SerializedName("result")
    private Integer result;

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EducationalExperience getEducationalExperience() {
        return educationalExperience;
    }

    public void setEducationalExperience(EducationalExperience educationalExperience) {
        this.educationalExperience = educationalExperience;
    }

    public List<EducationalExperience> getEducationalExperiences() {
        return educationalExperiences;
    }

    public void setEducationalExperiences(List<EducationalExperience> educationalExperiences) {
        this.educationalExperiences = educationalExperiences;
    }

    public EducationalProgram getEducationalProgram() {
        return educationalProgram;
    }

    public void setEducationalProgram(EducationalProgram educationalProgram) {
        this.educationalProgram = educationalProgram;
    }

    public List<EducationalProgram> getEducationalPrograms() {
        return educationalPrograms;
    }

    public void setEducationalPrograms(List<EducationalProgram> educationalPrograms) {
        this.educationalPrograms = educationalPrograms;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public SchoolPeriod getSchoolPeriod() {
        return schoolPeriod;
    }

    public void setSchoolPeriod(SchoolPeriod schoolPeriod) {
        this.schoolPeriod = schoolPeriod;
    }

    public List<SchoolPeriod> getSchoolPeriods() {
        return schoolPeriods;
    }

    public void setSchoolPeriods(List<SchoolPeriod> schoolPeriods) {
        this.schoolPeriods = schoolPeriods;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public List<Syllabus> getSyllabuses() {
        return syllabuses;
    }

    public void setSyllabuses(List<Syllabus> syllabuses) {
        this.syllabuses = syllabuses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}