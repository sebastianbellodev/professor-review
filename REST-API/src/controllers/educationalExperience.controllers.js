import { pool } from "../schema/connection";

export const getEducationalExperienceByFaculty = (request) => {
  const { idFaculty } = request.body;
  return Promise.resolve(
    pool.query(
      "SELECT\n"+
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