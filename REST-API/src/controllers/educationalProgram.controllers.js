import { pool } from "../schema/connection.js";

export const getEducationalProgramByName = (request) => {
  const name = request.body.name;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "educationalProgram\n" +
      "WHERE\n" +
      "name = ?",
      [name]
    )
  );
};


export const getEducationalProgramOfFaculty = (req) => {
  const {idFaculty} = req.body;
  return Promise.resolve(
    pool.query(
      "SELECT educationalprogram.name FROM educationalprogram"+
      "WHERE educationalprogram.idFaculty = ?",
      [idFaculty]
    )
  )
}


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

export const patchEducationalProgram = (request) => {
  const {
    idEducationalProgram,
    name,
    idFaculty } = request.body;
  return Promise.resolve(
    pool.query(
      "UPDATE\n" +
      "educationalProgram\n" +
      "SET\n" +
      "name = IFNULL(?, name),\n" +
      "idFaculty = IFNULL(?, idFaculty)\n" +
      "WHERE\n" +
      "idEducationalProgram = ?",
      [
        name,
        idFaculty,
        idEducationalProgram
      ]
    )
  );
};

export const postEducationalProgram = (request) => {
  const {
    name,
    idFaculty } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "educationalprogram\n" +
      "(name, idFaculty)\n" +
      "VALUES(?, ?)",
      [
        name,
        idFaculty
      ]
    )
  );
};

