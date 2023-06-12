import { pool } from "../schema/connection.js";

export const getFaculties = () => {
    return Promise.resolve(
        pool.query(
            "SELECT * FROM faculty"
        )
    );
};

export const getFacultiesByName = () => {
    const name = request.body.name;
    return Promise.resolve(
        pool.query(
            "Select * FROM faculty WHERE name = ?",
            [name]
        )
    );
};

export const logFaculty = () => {
    const {
        name
    } = req.body;
    return Promise.resolve(
        pool.query(
            "INSERT INTO faculty ('name') VALUES('?')",
            [
                name
            ]
        )
    )
};

export const deleteFaculty = () => {
    const {
        idFaculty
    } = req.body;
    return Promise.resolve(
        pool.query(
            "DELETE FROM faculty WHERE idFaculty = ?",
            [
                idFaculty
            ]
        )

    )
};

export const patchFaculty = () => {
    const {
        name,
        idFaculty
    } = req.body;
    return Promise.resolve(
        pool.query(
            "UPDATE faculty SET faculty.name = ? WHERE faculty.idFaculty = ?",
            [
                name,
                idFaculty
            ]
        )

    )
};

return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "faculty"
    )
);


