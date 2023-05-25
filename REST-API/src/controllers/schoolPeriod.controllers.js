import { pool } from "../schema/connection.js";

export const getAllSchoolPeriods = () => {
  return Promise.resolve(pool.query("SELECT * FROM schoolperiod"));
};
