import { pool } from "../schema/connection";

export const getReview = (request) => {
  const { idSchoolPeriod, idAcademicOffering, registrationNumber } = request.body;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
      "FROM\n" +
      "review\n" +
      "WHERE\n" +
      "review.idSchoolPeriod = ?\n" +
      "AND\n" +
      "review.idAcademicOffering = ?\n" +
      "AND\n" +
      "review.registrationNumber = ?",
      [idSchoolPeriod, idAcademicOffering, registrationNumber]
    )
  );
};


export const patchReview = (request) => {
  const { idReview,
    stars,
    comment,
    idSchoolPeriod,} = request.body;
  return Promise.resolve(
    pool.query(
      "",
      [stars, comment, idSchoolPeriod, idReview]
    )
  );
};

export const postReview = (request) => {
  const {
    stars,
    comment,
    idSchoolPeriod,
    idAcademicOffering,
    registrationNumber,
  } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
      "review\n" +
      "(stars, comment, idSchoolPeriod, idAcademicOffering, registrationNumber)\n" +
      "VALUES\n" +
      "(?, ?, ?, ?, ?)",
      [stars, comment, idSchoolPeriod, idAcademicOffering, registrationNumber]
    )
  );
};