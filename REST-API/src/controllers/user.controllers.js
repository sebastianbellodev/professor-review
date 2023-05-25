import { pool } from "../schema/connection.js";

export const getUser = () =>
  Promise.resolve(pool.query("SELECT username, registrationNumber FROM user"));

export const getUserByUsername = (req) => {
  const username = req.body.username;
  return Promise.resolve(
    pool.query(
      "SELECT username, registrationNumber FROM user WHERE username = ?",
      [username]
    )
  );
};

export const postUser = (req) => {
  const { username, password, registrationNumber } = req.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO user (username, password, registrationNumber) VALUES (?, ?, ?)",
      [username, password, registrationNumber]
    )
  );
};

export const patchUser = (req) => {
  const { user, username, password } = req.body;
  return Promise.resolve(
    pool.query(
      "UPDATE user SET username = IFNULL(?, username), password = IFNULL(?, password) WHERE username = ?",
      [username, password, user]
    )
  );
};

export const deleteUser = (req) => {
  const username = req.body.username;
  return Promise.resolve(
    pool.query("DELETE FROM user WHERE username = ?", [username])
  );
};
