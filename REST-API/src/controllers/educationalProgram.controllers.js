import { pool } from "../schema/connection.js";

export const postEducationalProgram = (req) => {
  const { name, idFaculty } = req.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO educationalprogram (name, idFaculty) VALUES (?, ?)",
      [name, idFaculty]
    )
  );
};

export const getEducationalProgramOfFaculty = (req) => {
  const {idFaculty} = req.body;
  return Promise.resolve(
    pool.query(
      "SELECT educationalprogram.name FROM educationalprogram"+
      "WHERE educationalprogram.idFaculty = ?",
      [idFaculty]
    )
  )
}

