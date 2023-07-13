import { pool } from "../database/connection.js";

export const getReportByProfessor = (request) => {
  const idProfessor = request.body.idProfessor;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "educationalExperience.name educationalExperience,\n" +
        "COUNT(review.idReview) reviews\n" +
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
        "INNER JOIN\n" +
        "educationalExperience\n" +
        "ON\n" +
        "syllabus.idEducationalExperience = educationalExperience.idEducationalExperience\n" +
        "WHERE\n" +
        "academicOffering.idProfessor = ?\n" +
        "GROUP BY\n" +
        "educationalExperience.name",
      [idProfessor]
    )
  );
};
