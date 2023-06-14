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
      "review.*\n" +
      "FROM\n" +
      "review\n" +
      "INNER JOIN\n" +
      "academicOffering\n" +
      "ON\n" +
      "review.idAcademicOffering = academicOffering.idAcademicOffering\n" +
      "INNER JOIN\n" +
      "syllabus\n" +
      "ON\n" +
      "academicOffering.idSyllabus = syllabus.idSyllabus\n" +
      "WHERE\n" +
      "syllabus.idEducationalExperience = ?\n" +
      "ORDER BY\n" +
      "review.comment\n" +
      "ASC"
      [idEducationalExperience]
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