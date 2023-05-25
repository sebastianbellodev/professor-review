import { pool } from "../schema/connection.js";

export const getStudent = () =>
  Promise.resolve(pool.query("SELECT * FROM student"));

export const getStudentByRegistrationNumber = (req) => {
  const registrationNumber = req.body.registrationNumber;
  return Promise.resolve(
    pool.query("SELECT * FROM student WHERE registrationNumber = ?", [
      registrationNumber,
    ])
  );
};

export const getStudentByFaculty = (req) => {
  const { idFaculty } = req.body;
  return Promise.resolve(
    pool.query(
      "SELECT student.* FROM student JOIN educationalprogram ON student.idEducationalProgram = educationalprogram.idEducationalProgram JOIN faculty ON educationalprogram.idFaculty = faculty.idFaculty WHERE faculty.idFaculty = ?",
      [idFaculty]
    )
  );
};

export const postStudent = (req) => {
  const {
    registrationNumber,
    name,
    paternalSurname,
    maternalSurname,
    emailAddress,
    idEducationalProgram,
  } = req.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO student (registrationNumber, name, paternalSurname, maternalSurname, emailAddress, idEducationalProgram) VALUES (?, ?, ?, ?, ?, ?)",
      [
        registrationNumber,
        name,
        paternalSurname,
        maternalSurname,
        emailAddress,
        idEducationalProgram,
      ]
    )
  );
};

export const patchStudent = (req) => {
  const { registrationNumber, name, paternalSurname, maternalSurname } =
    req.body;
  return Promise.resolve(
    pool.query(
      "UPDATE student SET name = IFNULL(?, name), paternalSurname = IFNULL(?, paternalSurname), maternalSurname = IFNULL(?, maternalSurname) WHERE registrationNumber = ?",
      [name, paternalSurname, maternalSurname, registrationNumber]
    )
  );
};

export const deleteStudent = (req) => {
  const registrationNumber = req.body.registrationNumber;
  return Promise.resolve(
    pool.query("DELETE FROM student WHERE registrationNumber = ?", [
      registrationNumber,
    ])
  );
};

export const statusUpdate = (req) => {
  const ids = req.body.ids;
  return Promise.resolve(
    pool.query(
      "UPDATE users SET active = CASE WHEN active = 1 THEN 0 ELSE 1 END WHERE registrationNumber IN (?)",
      [ids]
    )
  );
};
