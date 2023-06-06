import { pool } from "../schema/connection.js";

export const getSchoolPeriodById = (request) => {
  const idSchoolPeriod = request.body.idSchoolPeriod;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "schoolPeriod\n" +
      "WHERE\n" +
      "idSchoolPeriod = ?",
      [idSchoolPeriod]
    )
  );
};

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