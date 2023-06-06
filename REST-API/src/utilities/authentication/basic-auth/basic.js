import basicAuth from "basic-auth";
import { credentials } from "./configuration/credentials.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../../../tools/message.js";


export const validateCredentials = (request, response, next) => {
  const authentication = basicAuth(request);
  if (!authentication) {
    response.set("WWW-Authenticate", 'Basic realm ="Secure"');
    message(response, RESPONSE_CODE.UNAUTHORIZED, RESPONSE_MESSAGE.FORBIDDEN);
  } else {
    if (verifyCredentials(authentication.name, authentication.pass)) {
      response.set("WWW-Authenticate", 'Basic realm ="Secure"');
      message(response, RESPONSE_CODE.UNAUTHORIZED, RESPONSE_MESSAGE.UNAUTHORIZED);
    } else {
      next();
    }
  }
};

function verifyCredentials(username, password) {
  return username == credentials.username && password == credentials.password;
}