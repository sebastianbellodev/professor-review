import jwt from "jsonwebtoken";
import { TOKEN_KEY } from "./configuration/configuration.js";

export const generateToken = (req) => {
  const { username, password } = req.body;
  return jwt.sign({ username, password }, TOKEN_KEY, { expiresIn: "2h" });
};

export const verifyToken = (req, res, next) => {
  jwt.verify(req.token, TOKEN_KEY, (err) => {
    if (err) {
      res.sendStatus(403);
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
    res.sendStatus(403);
  }
};
