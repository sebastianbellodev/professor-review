import { pool } from "../schema/connection.js";

export const getEducationalPrograms = () => {
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "educationalProgram"
    )
  );
};

export const getEducationalProgramsByEducationalExperience = (request) => {
  const idEducationalExperience = request.body.idEducationalExperience;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "educationalProgram.*\n" +
      "FROM\n" +
      "educationalProgram\n" +
      "INNER JOIN\n" +
      "syllabus\n" +
      "ON\n" +
      "educationalProgram.idEducationalProgram = syllabus.idEducationalProgram\n" +
      "INNER JOIN\n" +
      "educationalExperience\n" +
      "ON\n" +
      "syllabus.idEducationalExperience = syllabus.idEducationalExperience\n" +
      "WHERE\n" +
      "educationalExperience.idEducationalExperience = ?",
      [idEducationalExperience]
    )
  );
};

export const postEducationalProgram = (request) => {
  const { name, idFaculty } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "educationalprogram\n"+
      "(name, idFaculty)\n" +
      "VALUES(?, ?)",
      [name, idFaculty]
    )
  );
};