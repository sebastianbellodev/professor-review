import { pool } from "../schema/connection.js";
import { isNullish } from "@supercharge/goodies";

export const deleteUser = (request) => {
  const username = request.body.username;
  return Promise.resolve(
    pool.query("DELETE\n" + "FROM\n" + "user\n" + "WHERE\n" + "username = ?", [
      username,
    ])
  );
};

export const getUserByUsername = (request) => {
  const username = isNullish(request.body.username)
    ? request.body.username
    : "";
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "username, registrationNumber\n" +
        "FROM\n" +
        "user\n" +
        "WHERE\n" +
        "username = ?",
      [username]
    )
  );
};

export const getUsers = () => {
  return Promise.resolve(
    pool.query(
      "SELECT\n" + "username, registrationNumber\n" + "FROM\n" + "user"
    )
  );
};

export const login = (request) => {
  const { username, password } = request.body;
  return Promise.resolve(
    pool.query(
      "SELECT\n" +
        "*\n" +
        "FROM\n" +
        "user\n" +
        "WHERE\n" +
        "username = ?\n" +
        "AND\n" +
        "password = ?",
      [username, password]
    )
  );
};

export const patchUser = (request) => {
  const { username, password } = request.body;
  return Promise.resolve(
    pool.query(
      "UPDATE\n" +
        "user\n" +
        "SET\n" +
        "password = IFNULL(?, password)\n" +
        "WHERE\n" +
        "username = ?",
      [password, username]
    )
  );
};

export const postUser = (request) => {
  const { username, password, registrationNumber } = request.body;
  return Promise.resolve(
    pool.query(
      "INSERT INTO\n" +
        "user\n" +
        "(username, password, registrationNumber)\n" +
        "VALUES (?, ?, ?)",
      [username, password, registrationNumber]
    )
  );
};
