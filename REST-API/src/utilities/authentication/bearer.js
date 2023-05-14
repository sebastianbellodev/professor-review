import jwt from "jsonwebtoken";
import { TOKEN_KEY } from "./configuration/configuration.js";
import { message, RES_CODE, RES_MESSAGE } from "../json/message.js";

export const generateToken = (req) => {
  const { username, password } = req.body;
  return jwt.sign({ username, password }, TOKEN_KEY, { expiresIn: "2h" });
};

export const verifyToken = (req, res, next) => {
  jwt.verify(req.token, TOKEN_KEY, (err) => {
    if (err) {
      message(res, RES_CODE.FORBIDDEN, RES_MESSAGE.FORBIDDEN);
    } else {
      next();
    }
  });
};

export const validateToken = (req, res, next) => {
  const bearer = req.headers["authorization"];

  if (typeof bearer !== "undefined") {
    const token = bearer.split(" ")[1];
    req.token = token;
    next();
  } else {
    message(res, RES_CODE.FORBIDDEN, RES_MESSAGE.FORBIDDEN);
  }
};
