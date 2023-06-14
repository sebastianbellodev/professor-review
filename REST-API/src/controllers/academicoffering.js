import { pool } from "../schema/connection.js";

export const postAcademicOffering = (request) => {
    const {  idSyllabus,
        idProfessor } = request.body;
    return Promise.resolve(
      pool.query(
        "INSERT INTO academicoffering \n" +
        "(idSyllabus, idProfessor)\n " +
        "VALUES (?, ?)"
        [
            idSyllabus,
            idProfessor
        ]
      )
    );
  };

export const getAcademicOffering = (request) => {
    const {  idSyllabus,
        idProfessor } = request.body;
    return Promise.resolve(
      pool.query(
        "SELECT * \n" +
        "FROM academicoffering\n" + 
        "WHERE idSyllabus = ? \n" +
        "AND idProfessor = ?"
        [
            idSyllabus,
            idProfessor
        ]
      )
    );
  };