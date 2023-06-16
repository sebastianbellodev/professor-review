import { pool } from "../schema/connection.js";

export const deleteSyllabus = (request) => {
  const idSyllabus = request.body.idSyllabus;
  return Promise.resolve(
    pool.query(
      "DELETE\n" +
      "FROM\n" +
      "syllabus\n" +
      "WHERE\n" +
      "idSyllabus = ?",
      [idSyllabus]
    )
  );
};

export const getSyllabusesByEducationalExperience = (request) => {
  const idEducationalExperience = request.body.idEducationalExperience;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "syllabus\n" +
      "WHERE\n" +
      "idEducationalExperience = ?",
      [idEducationalExperience]
    )
  );
};

export const getSyllabusesByEducationalExperienceEducationalProgram = (request) => {
  const {idEducationalExperience,idEducationalProgram} = request.body;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "syllabus\n" +
      "WHERE\n" +
      "idEducationalExperience = ? && idEducationalProgram = ?",
      [idEducationalExperience, idEducationalProgram]
    )
  );
};

export const getSyllabusById = (request) => {
  const idSyllabus = request.body.idSyllabus;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "syllabus\n" +
      "WHERE\n" +
      "idSyllabus = ?",
      [idSyllabus]
    )
  );
};

export const postSyllabus = (request) => {
  const { idEducationalProgram,
    idEducationalExperience } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "syllabus\n" +
      "(idEducationalProgram, idEducationalExperience)\n" +
      "VALUES\n" +
      "(?, ?)",
      [
        idEducationalProgram,
        idEducationalExperience
      ]
    )
  );
};