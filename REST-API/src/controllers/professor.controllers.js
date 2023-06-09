import { pool } from "../schema/connection.js";

export const deleteProfessor = (request) => {
  const idProfessor = request.body.idProfessor;
  return Promise.resolve(
    pool.query(
      "DELETE FROM\n" + "professor\n" + "WHERE\n" + "idProfessor = ?",
      [idProfessor]
    )
  );
};

export const getProfessorById = (request) => {
  const idProfessor = request.body.idProfessor;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "*\n" +
        "FROM\n" +
        "professor\n" +
        "WHERE\n" +
        "idProfessor = ?",
      [idProfessor]
    )
  );
};

export const getProfessors = () => {
  return Promise.resolve(
    pool.query("SELECT\n" + "*\n" + "FROM\n" + "professor")
  );
};

export const getProfessorsByEducationalExperience = (request) => {
  const idEducationalExperience = request.body.idEducationalExperience;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "*\n" +
        "FROM\n" +
        "professor\n" +
        "INNER JOIN\n" +
        "academicoffering\n" +
        "ON\n" +
        "academicOffering.idProfessor = professor.idProfessor\n" +
        "INNER JOIN\n" +
        "syllabus\n" +
        "ON\n" +
        "syllabus.idSyllabus = academicOffering.idSyllabus\n" +
        "WHERE\n" +
        "idEducationalExperience = ?",
      [idEducationalExperience]
    )
  );
};

export const getProfessorsByFaculty = (request) => {
  const idFaculty = request.body.idFaculty;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "professor.*\n" +
        "from\n" +
        "professor\n" +
        "INNER JOIN\n" +
        "directory\n" +
        "ON\n" +
        "professor.idProfessor = directory.idProfessor\n" +
        "INNER JOIN\n" +
        "faculty\n" +
        "ON\n" +
        "directory.idFaculty = faculty.idFaculty\n" +
        "WHERE\n" +
        "faculty.idFaculty = ?",
      [idFaculty]
    )
  );
};

export const patchProfessor = (request) => {
  const { idProfessor, name, lastName } = request.body;
  return Promise.resolve(
    pool.query(
      "UPDATE\n" +
        "professor\n" +
        "SET\n" +
        "name = IFNULL(?, name),\n" +
        "lastName = IFNULL(?, lastName)\n" +
        "WHERE\n" +
        "\nidProfessor = ?",
      [name, lastName, idProfessor]
    )
  );
};

export const postProfessor = (request) => {
  const { name, lastName } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
        "professor\n" +
        "(name, lastName)\n" +
        "VALUES\n" +
        "(?, ?)",
      [name, lastName]
    )
  );
};
