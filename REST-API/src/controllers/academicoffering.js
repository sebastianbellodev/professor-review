import { pool } from "../schema/connection";

export const logAcademicOffering = (request) => {
    const {
        idSyllabus,
        idProfessor
    } = request.body;
    return Promise.resolve(
        pool.query(
            "INSERT INTO review ('stars', 'comment', 'idSchoolPeriod', 'idAcademicOffering', 'registrationNumber') VALUES ('?', '?', '?', '?', '?')",
            [
                idSyllabus,
                idProfessor
            ]
        )
    );
};
