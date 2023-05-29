import { pool } from "../schema/connection.js";

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