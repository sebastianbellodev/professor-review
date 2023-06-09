import jwt from "jsonwebtoken";
import { TOKEN_KEY } from "./configuration/configuration.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE,
} from "../../../tools/message.js";

export const generateToken = (request) => {
  const { username, password } = request.body;
  return jwt.sign({ username, password }, TOKEN_KEY, { expiresIn: "2h" });
};

export const validateToken = (request, response, next) => {
  const bearer = request.headers["authorization"];
  if (typeof bearer !== "undefined") {
    const token = bearer.split(" ")[1];
    request.token = token;
    if (verifyToken(token)) {
      next();
    } else {
      message(response, RESPONSE_CODE.FORBIDDEN, RESPONSE_MESSAGE.FORBIDDEN);
    }
  } else {
    message(response, RESPONSE_CODE.FORBIDDEN, RESPONSE_MESSAGE.FORBIDDEN);
  }
};

function verifyToken(token) {
  let flag = true;
  jwt.verify(token, TOKEN_KEY, (error) => {
    if (error) {
      flag = false;
    }
  });
  return flag;
}