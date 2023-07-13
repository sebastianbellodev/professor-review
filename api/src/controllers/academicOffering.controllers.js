import { pool } from "../database/connection.js";

export const getAcademicOffering = (request) => {
  const { idSyllabus, idProfessor } = request.body;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "*\n" +
        "FROM\n" +
        "academicoffering\n" +
        "WHERE\n" +
        "idSyllabus = ?\n" +
        "AND\n" +
        "idProfessor = ?",
      [idSyllabus, idProfessor]
    )
  );
};

export const getAcademicOfferingById = (request) => {
  const idAcademicOffering = request.body.idAcademicOffering;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "*\n" +
        "FROM\n" +
        "academicOffering\n" +
        "WHERE\n" +
        "idAcademicOffering = ?",
      [idAcademicOffering]
    )
  );
};

export const postAcademicOffering = (request) => {
  const { idSyllabus, idProfessor } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
        "academicoffering \n" +
        "(idSyllabus, idProfessor)\n " +
        "VALUES (?, ?)",
      [idSyllabus, idProfessor]
    )
  );
};
