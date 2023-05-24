import { pool } from "../schema/connection";

export const checkReview = (req) => {
    const {
        idSchoolPeriod,
        idAcademicOffering,
        registrationNumber
    } = req.body;
    return Promise.resolve(
        pool.query(
            "Select * from review WHERE review.idSchoolPeriod = ? and review.idAcademicOffering = ? and review.registrationNumber = ?",
            [
                idSchoolPeriod,
                idAcademicOffering,
                registrationNumber
            ]
        )
    );
};

export const logReview = (req) => {
    const {
        stars,
        comment,
        idSchoolPeriod,
        idAcademicOffering,
        registrationNumber
    } = req.body;
    return Promise.resolve(
        pool.query(
            "INSERT INTO review (`stars`, `comment`, `idSchoolPeriod`, `idAcademicOffering`, `registrationNumber`) VALUES ('?', '?', '?', '?', '?')",
            [
                stars,
                comment,
                idSchoolPeriod,
                idAcademicOffering,
                registrationNumber
            ]
        )
    );
};

export const updateReview = (req) => {
    const {
        idReview,
        stars,
        comment,
        idSchoolPeriod
    } = req.body;
    return Promise.resolve(
        pool.query(
            "", // TODO hacer la consulta
            [
                stars,
                comment,
                idSchoolPeriod,
                idReview
            ]
        )
    );
};