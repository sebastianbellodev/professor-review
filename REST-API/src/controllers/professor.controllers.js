import { pool } from "../schema/connection.js";

export const getProfessor = () =>
  Promise.resolve(pool.query("SELECT * FROM professor"));

export const getProfessorByIdProfessor = (req) => {
  const idProfessor = req.body.idProfessor;
  return Promise.resolve(
    pool.query("SELECT * FROM professor WHERE idProfessor = ?", [idProfessor])
  );
};

export const postProfessor = (req) => {
  const { name, paternalSurname, maternalSurname } = req.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO professor (name, paternalSurname, maternalSurname) VALUES (?, ?, ?)",
      [name, paternalSurname, maternalSurname]
    )
  );
};

export const patchProfessor = (req) => {
  const { idProfessor, name, paternalSurname, maternalSurname } = req.body;
  return Promise.resolve(
    pool.query(
      "UPDATE professor SET name = IFNULL(?, name), paternalSurname = IFNULL(?, paternalSurname), maternalSurname = IFNULL(?, maternalSurname) WHERE idProfessor = ?",
      [name, paternalSurname, maternalSurname, idProfessor]
    )
  );
};

export const deleteProfessor = (req) => {
  const idProfessor = req.body.idProfessor;
  return Promise.resolve(
    pool.query("DELETE FROM professor WHERE idProfessor = ?", [idProfessor])
  );
};

export const getProfessorsByEducationalExperience = (req) => {
  const idEducationalExperience = req.body.idProfessor;
  return Promise.resolve(
    pool.query(
      "SELECT * from professor " +
        "JOIN academicoffering ON academicoffering.idProfessor = professor.idProfessor " +
        "JOIN syllabus ON syllabus.idSyllabus = academicoffering.idSyllabus " +
        "WHERE idEducationalExperience = ?",
      [idEducationalExperience]
    )
  );
};

export const getProfessorByFaculty = (req) => {
  const idFaculty = req.body.idFaculty;
  return Promise.resolve(
    pool.query(
      "SELECT professor.* from professor JOIN directory ON professor.idProfessor = directory.idDirectory JOIN faculty ON directory.idFaculty = faculty.idFaculty WHERE faculty.idFaculty = ?",
      [idFaculty]
    )
  );
};
