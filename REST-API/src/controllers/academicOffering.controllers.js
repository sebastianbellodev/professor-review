import { pool } from "../schema/connection.js";

export const getAcademicOfferingById = (request) => {
    const idAcademicOffering = request.body.idAcademicOffering;
    return Promise.resolve(
        pool.query(
            "SELECT\n" +
            "*\n" +
            "FROM\n" +
            "academicOffering\n" +
            "WHERE\n" +
            "idAcademicOffering = ?",
            [idAcademicOffering]
        )
    );
};