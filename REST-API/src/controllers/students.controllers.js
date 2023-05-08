import { pool } from "../schema/connection.js";
import {
  checkGet,
  checkDelete,
  checkPost,
  checkPatch,
  sendInternalServerError,
} from "../utilities/response.js";

const student = "Student";

export const getStudent = async (req, res) => {
  try {
    const [row] = await pool.query("SELECT * FROM student");
    res.status(200).json(row);
  } catch (error) {
    sendInternalServerError(res);
  }
};

export const getStudentRegistrationNumber = async (req, res) => {
  const registrationNumber = req.params.registrationNumber;
  try {
    const [row] = await pool.query(
      "SELECT * FROM student WHERE registrationNumber = ?",
      [registrationNumber]
    );

    checkGet(row, res, student);
  } catch (error) {
    sendInternalServerError(res);
  }
};

export const logStudent = async (req, res) => {
  const {
    registrationNumber,
    name,
    paternalSurname,
    maternalSurname,
    emailAddress,
    idEducationalProgram,
  } = req.body;
  try {
    const [row] = await pool.query(
      "INSERT INTO student (registrationNumber, name, paternalSurname, maternalSurname, emailAddress, idEducationalProgram) VALUES (?, ?, ?, ?, ?, ?)",
      [
        registrationNumber,
        name,
        paternalSurname,
        maternalSurname,
        emailAddress,
        idEducationalProgram,
      ]
    );

    checkPost(row, res, student);
  } catch (error) {
    sendInternalServerError(res);
  }
};

export const updateStudent = async (req, res) => {
  const registrationNumber = req.params.registrationNumber;
  const { name, paternalSurname, maternalSurname } = req.body;
  try {
    const [row] = await pool.query(
      "UPDATE student SET name = IFNULL(?, name), paternalSurname = IFNULL(?, paternalSurname), maternalSurname = IFNULL(?, maternalSurname) WHERE registrationNumber = ?",
      [name, paternalSurname, maternalSurname, registrationNumber]
    );

    checkPatch(row, res, student);
  } catch (error) {
    sendInternalServerError(res);
  }
};

export const deleteStudent = async (req, res) => {
  const registrationNumber = req.params.registrationNumber;
  try {
    const [row] = await pool.query(
      "DELETE FROM student WHERE registrationNumber = ?",
      [registrationNumber]
    );

    checkDelete(row, res, student);
  } catch (error) {
    sendInternalServerError(res);
  }
};
