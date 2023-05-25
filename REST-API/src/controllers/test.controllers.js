import { pool } from "../schema/connection.js";

export const ping = () => Promise.resolve(pool.query("SELECT 1 + 1 AS result"));
