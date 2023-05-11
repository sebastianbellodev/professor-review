import { pool } from "../schema/connection.js";

export const getStudent = () =>
  Promise.resolve(pool.query("SELECT * FROM student"));

export const getStudentByRegistrationNumber = (req) => {
  const registrationNumber = req.params.registrationNumber;
  return Promise.resolve(
    pool.query("SELECT * FROM student WHERE registrationNumber = ?", [
      registrationNumber,
    ])
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
  const { name, paternalSurname, maternalSurname } = req.body;
  const registrationNumber = req.params.registrationNumber;
  return Promise.resolve(
    pool.query(
      "UPDATE student SET name = IFNULL(?, name), paternalSurname = IFNULL(?, paternalSurname), maternalSurname = IFNULL(?, maternalSurname) WHERE registrationNumber = ?",
      [name, paternalSurname, maternalSurname, registrationNumber]
    )
  );
};

export const deleteStudent = (req) => {
  const registrationNumber = req.params.registrationNumber;
  return Promise.resolve(
    pool.query("DELETE FROM student WHERE registrationNumber = ?", [
      registrationNumber,
    ])
  );
};
