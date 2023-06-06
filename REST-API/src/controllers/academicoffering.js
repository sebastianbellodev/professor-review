import { pool } from "../schema/connection";

export const logAcademicOffering = (req) => {
    const {
        idSyllabus,
        idProfessor
    } = req.body;
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
