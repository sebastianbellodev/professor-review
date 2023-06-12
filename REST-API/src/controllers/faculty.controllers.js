import { pool } from "../schema/connection.js";

export const deleteFaculty = (request) => {
    const idFaculty = request.body.idFaculty;
    return Promise.resolve(
        pool.query(
            "DELETE FROM\n" +
            "faculty\n" +
            "WHERE\n" +
            "idFaculty = ?",
            [idFaculty]
        )

    )
};

export const getFacultyByName = (request) => {
    const name = request.body.name;
    return Promise.resolve(
        pool.query(
            "SELECT\n"
            + "*\n" +
            "FROM\n" +
            "faculty\n" +
            "WHERE\n" +
            "name = ?",
            [name]
        )
    );
};

export const getFaculties = () => {
    return Promise.resolve(
        pool.query(
            "SELECT\n" +
            "*\n" +
            "FROM\n" +
            "faculty"
        )
    );
};

export const patchFaculty = (request) => {
    const { name,
        idFaculty } = request.body;
    return Promise.resolve(
        pool.query(
            "UPDATE\n" +
            "faculty\n" +
            "SET\n" +
            "name = IFNULL(?, name)\n" +
            "WHERE\n" +
            "idFaculty = ? ",
            [
                name,
                idFaculty
            ]
        )
    )
};

export const postFaculty = (request) => {
    const name = request.body.name;
    return Promise.resolve(
        pool.query(
            "INSERT INTO\n" +
            "faculty\n" +
            "('name')" +
            "VALUES('?')",
            [name]
        )
    )
};