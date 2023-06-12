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