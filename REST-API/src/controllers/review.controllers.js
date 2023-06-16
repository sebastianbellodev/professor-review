import { pool } from "../schema/connection.js";

export const deleteReview = (request) => {
  const idReview = request.body.idReview;
  return Promise.resolve(
    pool.query(
      "DELETE FROM\n" +
      "review\n" +
      "WHERE\n" +
      "idReview = ?",
      [idReview]
    )
  );
};

export const getReview = (request) => {
  const { idSchoolPeriod,
    idAcademicOffering,
    registrationNumber } = request.body;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "review.*\n" +
      "FROM\n" +
      "review\n" +
      "WHERE\n" +
      "review.idSchoolPeriod = ?\n" +
      "AND\n" +
      "review.idAcademicOffering = ?\n" +
      "AND\n" +
      "review.registrationNumber = ?",
      [
        idSchoolPeriod,
        idAcademicOffering,
        registrationNumber
      ]
    )
  );
};

export const getReviewsByEducationalExperience = (request) => {
  const idEducationalExperience = request.body.idEducationalExperience;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "review.stars,\n" +
      "review.comment,\n" +
      "CONCAT(schoolPeriod.startDate, \" - \", schoolperiod.endDate) AS schoolPeriod,\n" +
      "educationalexperience.name AS educationalExperience,\n" +
      "CONCAT(professor.name, \" \", professor.lastName) AS professor,\n" +
      "CONCAT(student.name, \" \", student.lastName) AS student\n" +
      "FROM\n" +
      "review\n" +
      "INNER JOIN\n" +
      "schoolPeriod\n" +
      "ON\n" +
      "review.idSchoolPeriod = schoolPeriod.idSchoolPeriod\n" +
      "INNER JOIN\n" +
      "student\n" +
      "ON\n" +
      "review.registrationNumber = student.registrationNumber\n" +
      "INNER JOIN\n" +
      "academicOffering\n" +
      "ON\n" +
      "review.idAcademicOffering = academicOffering.idAcademicOffering\n" +
      "INNER JOIN\n" +
      "syllabus\n" +
      "ON\n" +
      "academicOffering.idSyllabus = syllabus.idSyllabus\n" +
      "INNER JOIN\n" +
      "educationalExperience\n" +
      "ON\n" +
      "educationalExperience.idEducationalExperience = syllabus.idEducationalExperience\n" +
      "INNER JOIN\n" +
      "professor\n" +
      "ON\n" +
      "academicOffering.idProfessor = professor.idProfessor\n" +
      "WHERE\n" +
      "syllabus.idEducationalExperience = ?\n" +
      "ORDER BY\n" +
      "review.comment\n" +
      "ASC",
      [idEducationalExperience]
    )
  );
};

export const getReviewsByProfessor = (request) => {
  const idProfessor = request.body.idProfessor;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "review.idReview, review.stars, review.comment, review.registrationNumber\n" +
      "FROM\n" +
      "review\n" +
      "INNER JOIN\n" +
      "academicoffering\n" +
      "ON\n" +
      "review.idAcademicOffering = academicoffering.idAcademicOffering\n" +
      "INNER JOIN\n" +
      "professor\n" +
      "ON\n" +
      "academicoffering.idProfessor = professor.idProfessor\n" +
      "WHERE\n" +
      "professor.idProfessor = ?",
      [idProfessor]
    )
  );
};

export const patchReview = (request) => {
  const { idReview,
    stars,
    comment,
    idSchoolPeriod } = request.body;
  return Promise.resolve(
    pool.query(
      "",
      [
        stars,
        comment,
        idSchoolPeriod,
        idReview
      ]
    )
  );
};

export const postReview = (request) => {
  const { stars,
    comment,
    idSchoolPeriod,
    idAcademicOffering,
    registrationNumber } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "review\n" +
      "(stars, comment, idSchoolPeriod, idAcademicOffering, registrationNumber)\n" +
      "VALUES\n" +
      "(?, ?, ?, ?, ?)",
      [
        stars,
        comment,
        idSchoolPeriod,
        idAcademicOffering,
        registrationNumber
      ]
    )
  );
};