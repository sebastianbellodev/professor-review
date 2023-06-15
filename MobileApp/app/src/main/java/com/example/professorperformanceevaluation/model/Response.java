package com.example.professorperformanceevaluation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    private int code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("educationalExperience")
    @Expose
    private EducationalExperience educationalExperience;
    @SerializedName("educationalExperiences")
    @Expose
    private List<EducationalExperience> educationalExperiences;
    @SerializedName("educationalProgram")
    @Expose
    private EducationalProgram educationalProgram;
    @SerializedName("educationalPrograms")
    @Expose
    private List<EducationalProgram> educationalPrograms;
    @SerializedName("faculty")
    @Expose
    private Faculty faculty;
    @SerializedName("faculties")
    @Expose
    private List<Faculty> faculties;
    @SerializedName("professor")
    @Expose
    private Professor professor;
    @SerializedName("professors")
    @Expose
    private List<Professor> professors;
    @SerializedName("review")
    @Expose
    private Review review;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews;
    @SerializedName("schoolPeriod")
    @Expose
    private SchoolPeriod schoolPeriod;
    @SerializedName("schoolPeriods")
    @Expose
    private List<SchoolPeriod> schoolPeriods;
    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("students")
    @Expose
    private List<Student> students;
    @SerializedName("syllabus")
    @Expose
    private Syllabus syllabus;
    @SerializedName("syllabuses")
    @Expose
    private List<Syllabus> syllabuses;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("users")
    @Expose
    private List<User> users;
    @SerializedName("test")
    @Expose
    private Test test;

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
}