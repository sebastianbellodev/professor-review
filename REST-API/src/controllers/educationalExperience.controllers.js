import { pool } from "../schema/connection";

export const getEducationalExperienceOfFaculty = (req) => {
    const {idFaculty} = req.body;
    return Promise.resolve(
        pool.query("select * from educationalexperience JOIN syllabus ON "+
        "educationalexperience.idEducationalExperience = syllabus.idEducationalExperience "+
        "JOIN educationalprogram ON syllabus.idEducationalProgram = educationalprogram.idEducationalProgram "+
        "JOIN faculty ON faculty.idFaculty = educationalprogram.idFaculty WHERE faculty.idFaculty = ?",
        [idFaculty]
        )
    );
};


export const getEducationalExperienceOfEducationalProgram = (req) =>{
    const {idEducationalProgram} = req.body;
    return Promise.resolve(
        pool.query("SELECT educationalexperience.idEducationalExperience,  educationalexperience.name FROM syllabus"+
        "INNER JOIN educationalexperience ON syllabus.idEducationalExperience = educationalexperience.idEducationalExperience"+
        "WHERE idEducationalProgram = ? ",
        [idEducationalProgram]
        )
    )
}