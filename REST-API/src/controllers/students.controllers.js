import { pool } from "../schema/connection.js";

const student = "Student";

export const getStudent = Promise.resolve(pool.query("SELECT * FROM student"));

export const getStudentRegistrationNumber = (registrationNumber) =>
  Promise.resolve(
    pool.query("SELECT * FROM student WHERE registrationNumber = ?", [
      registrationNumber,
    ])
  );

export const postStudent = (values) =>
  Promise.resolve(
    pool.query(
      "INSERT INTO student (registrationNumber, name, paternalSurname, maternalSurname, emailAddress, idEducationalProgram) VALUES (?, ?, ?, ?, ?, ?)",
      [
        values.registrationNumber,
        values.name,
        values.paternalSurname,
        values.maternalSurname,
        values.emailAddress,
        values.idEducationalProgram,
      ]
    )
  );

export const patchStudent = (values, registrationNumber) =>
  Promise.resolve(
    pool.query(
      "UPDATE student SET name = IFNULL(?, name), paternalSurname = IFNULL(?, paternalSurname), maternalSurname = IFNULL(?, maternalSurname) WHERE registrationNumber = ?",
      [
        values.name,
        values.paternalSurname,
        values.maternalSurname,
        registrationNumber,
      ]
    )
  );

export const deleteStudent = (registrationNumber) =>
  Promise.resolve(
    pool.query("DELETE FROM student WHERE registrationNumber = ?", [
      registrationNumber,
    ])
  );
