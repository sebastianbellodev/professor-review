import { pool } from "../schema/connection.js";

export const getReportOfProfessor = (req) => {
  const { idProfessor } = req.body;
  return Promise.resolve(
    pool.query(
      "SELECT educationalexperience.name AS nombre_materia, " +
        "COUNT(*) AS cantidad_resenas FROM review JOIN academicoffering ON " +
        "review.idAcademicOffering = academicoffering.idAcademicOffering " +
        "JOIN syllabus ON academicoffering.idSyllabus = syllabus.idSyllabus " +
        "JOIN educationalexperience ON syllabus.idEducationalExperience = educationalexperience.idEducationalExperience " +
        "WHERE academicoffering.idProfessor = ? GROUP BY educationalexperience.name",
      [idProfessor]
    )
  );
};
