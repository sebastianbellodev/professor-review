import basicAuth from "basic-auth";
import { credential } from "./credential.js";
import { message, RES_CODE, RES_MESSAGE } from "../json/message.js";

export const validateCredential = (req, res, next) => {
  const authentication = basicAuth(req);

  if (!authentication) {
    res.set("WWW-Authenticate", 'Basic realm ="Secure"');
    message(res, RES_CODE.UNAUTHORIZED, RES_MESSAGE.UNAUTHORIZED);
  } else {
    if (!verifyCredential(authentication.name, authentication.pass)) {
      res.set("WWW-Authenticate", 'Basic realm ="Secure"');
      message(res, RES_CODE.UNAUTHORIZED, RES_MESSAGE.UNAUTHORIZED);
    } else {
      next();
    }
  }
};

function verifyCredential(name, pass) {
  var flag = true;
  credential.user == name && credential.password == pass
    ? (flag = true)
    : (flag = false);
  return flag;
}
