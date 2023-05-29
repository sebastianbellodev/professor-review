import { pool } from "../schema/connection.js";

export const deleteSyllabus = (request) => {
  return Promise.resolve(
    pool.query(
      "DELETE\n" +
      "*\n" +
      "FROM\n" +
      "syllabus"
    )
  );
};