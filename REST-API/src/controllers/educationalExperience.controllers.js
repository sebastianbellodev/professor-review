import { pool } from "../schema/connection";

export const getEducationalExperienceOfFaculty = (req) => {
  const { idFaculty } = req.body;
  return Promise.resolve(
    pool.query(
      "SELECT * FROM educationalexperience JOIN syllabus ON " +
        "educationalexperience.idEducationalExperience = syllabus.idEducationalExperience " +
        "JOIN educationalprogram ON syllabus.idEducationalProgram = educationalprogram.idEducationalProgram " +
        "JOIN faculty ON faculty.idFaculty = educationalprogram.idFaculty WHERE faculty.idFaculty = ?",
      [idFaculty]
    )
  );
};
