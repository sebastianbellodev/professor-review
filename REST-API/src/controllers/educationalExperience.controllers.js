import { pool } from "../schema/connection.js";

export const getEducationalExperienceById = (request) => {
  const idEducationalExperience = request.body.idEducationalExperience;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "educationalExperience\n" +
      "WHERE\n" +
      "educationalExperience.idEducationalExperience = ?",
      [idEducationalExperience]
    )
  );
};

export const getEducationalExperienceByName = (request) => {
  const name = request.body.name;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "educationalExperience\n" +
      "WHERE\n" +
      "name = ?",
      [name]
    )
  );
};

export const getEducationalExperiences = () => {
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "educationalExperience"
    )
  );
};

export const getEducationalExperiencesByEducationalProgram = (request) => {
  const idEducationalProgram = request.body.idEducationalProgram;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "\neducationalexperience.idEducationalExperience, educationalexperience.name\n" +
      "FROM\n" +
      "educationalexperience\n" +
      "INNER JOIN\n" +
      "syllabus\n" +
      "ON\n" +
      "educationalexperience.idEducationalExperience = syllabus.idEducationalExperience\n" +
      "WHERE\n" +
      "syllabus.idEducationalProgram = ?",
      [idEducationalProgram]
    )
  );
};

export const getEducationalExperiencesByFaculty = (request) => {
  const idFaculty = request.body.idFaculty;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "educationalExperience\n" +
      "INNER JOIN\n" +
      "syllabus\n" +
      "ON\n" +
      "educationalExperience.idEducationalExperience = syllabus.idEducationalExperience\n" +
      "INNER JOIN\n" +
      "educationalProgram\n" +
      "ON\n" +
      "syllabus.idEducationalProgram = educationalProgram.idEducationalProgram\n" +
      "INNER JOIN\n" +
      "faculty\n" +
      "ON\n" +
      "faculty.idFaculty = educationalProgram.idFaculty\n" +
      "WHERE\n" +
      "faculty.idFaculty = ?",
      [idFaculty]
    )
  );
};

export const patchEducationalExperience = (request) => {
  const { idEducationalExperience,
    name } = request.body;
  return Promise.resolve(
    pool.query(
      "UPDATE\n" +
      "educationalExperience\n" +
      "SET\n" +
      "name = IFNULL(?, name)\n" +
      "WHERE\n" +
      "idEducationalExperience = ?",
      [
        name,
        idEducationalExperience
      ]
    )
  );
};

export const postEducationalExperience = (request) => {
  const name = request.body.name;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "educationalExperience\n" +
      "(name)\n" +
      "VALUES\n" +
      "(?)",
      [name]
    )
  );
};