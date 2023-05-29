import { pool } from "../schema/connection.js";

export const getSchoolPeriods = () => {
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "schoolPeriod"
    )
  );
};