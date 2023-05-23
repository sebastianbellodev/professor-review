import { pool } from "../schema/connection.js";

export const getFaculties = () => {
    return Promise.resolve(
        pool.query(
            "SELECT * FROM faculty"
        )
    );
};
