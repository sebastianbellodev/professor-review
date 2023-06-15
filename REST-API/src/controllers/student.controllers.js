import { pool } from "../schema/connection.js";
import { isNullish } from "@supercharge/goodies";

export const deleteStudent = (request) => {
  const registrationNumber = request.body.registrationNumber;
  return Promise.resolve(
    pool.query(
      "DELETE\n" +
      "FROM\n" +
      "student\n" +
      "WHERE\n" +
      "registrationNumber = ?",
      [registrationNumber]
    )
  );
};

export const getStudentByEmailAddress = (request) => {
  const emailAddress =
    isNullish(request.body.emailAddress)
      ? request.body.emailAddress
      : "";
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "student\n" +
      "WHERE\n" +
      "emailAddress = ?",
      [emailAddress]
    )
  );
};

export const getStudentByPhoneNumber = (request) => {
  const phoneNumber =
    isNullish(request.body.phoneNumber)
      ? request.body.phoneNumber
      : "";
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "student\n" +
      "WHERE\n" +
      "phoneNumber = ?",
      [phoneNumber]
    )
  );
};

export const getStudentByRegistrationNumber = (request) => {
  const registrationNumber = request.body.registrationNumber;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "*\n" +
      "FROM\n" +
      "student\n" +
      "WHERE\n" +
      "registrationNumber = ?",
      [registrationNumber]
    )
  );
};

export const getStudents = () => {
  return Promise.resolve(
      pool.query(
          "SELECT\n" +
          "*\n" +
          "FROM\n" +
          "student\n" +
          "ORDER BY\n" +
          "name\n" +
          "ASC"
      )
  );
};

export const getStudentsByFaculty = (request) => {
  const idFaculty = request.body.idFaculty;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "student.*\n" +
      "FROM\n" +
      "student\n" +
      "INNER JOIN\n" +
      "educationalProgram\n" +
      "ON\n" +
      "student.idEducationalProgram = educationalProgram.idEducationalProgram\n" +
      "INNER JOIN\n" +
      "faculty\n" +
      "ON\n" +
      "educationalProgram.idFaculty = faculty.idFaculty\n" +
      "WHERE\n" +
      "faculty.idFaculty = ?\n" +
      "ORDER BY\n" +
      "student.name\n" +
      "ASC",
      [idFaculty]
    )
  );
};

export const patchStudent = (request) => {
  const {
    registrationNumber,
    name,
    lastName,
    emailAddress,
    phoneNumber,
    biography,
  } = request.body;
  return Promise.resolve(
    pool.query(
      "UPDATE\n" +
      "student\n" +
      "SET\n" +
      "name = IFNULL(?, name),\n" +
      "lastName = IFNULL(?, lastName),\n" +
      "emailAddress = IFNULL(?, emailAddress),\n" +
      "phoneNumber = IFNULL(?, phoneNumber),\n" +
      "biography = IFNULL(?, biography)\n" +
      "WHERE\n" +
      "registrationNumber = ?",
      [name, lastName, emailAddress, phoneNumber, biography, registrationNumber]
    )
  );
};

export const postStudent = (request) => {
  const {
    registrationNumber,
    name,
    lastName,
    emailAddress,
    idEducationalProgram,
  } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "student\n" +
      "(registrationNumber, name, lastName, emailAddress, idEducationalProgram)\n" +
      "VALUES\n" +
      "(?, ?, ?, ?, ?)",
      [registrationNumber, name, lastName, emailAddress, idEducationalProgram]
    )
  );
};

export const updateStatus = (request) => {
  const {
    ids
  } = request.body;
  return Promise.resolve(
    pool.query(
      "UPDATE student SET active = CASE WHEN active = 1 THEN 0 ELSE 1 END WHERE registrationNumber IN (?)",
      [ids]
    )
  );
};