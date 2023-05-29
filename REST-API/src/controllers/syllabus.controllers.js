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