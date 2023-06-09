import basicAuth from "basic-auth";
import { CREDENTIALS } from "./configuration/configuration.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE,
} from "../../../tools/message.js";

export const validateCredentials = (request, response, next) => {
  const authentication = basicAuth(request);
  if (!authentication) {
    response.set("WWW-Authenticate", 'Basic realm ="Secure"');
    message(response, RESPONSE_CODE.UNAUTHORIZED, RESPONSE_MESSAGE.FORBIDDEN);
  } else {
    if (verifyCredentials(authentication.name, authentication.pass)) {
      response.set("WWW-Authenticate", 'Basic realm ="Secure"')
    } else {
      message(response, RESPONSE_CODE.UNAUTHORIZED, RESPONSE_MESSAGE.UNAUTHORIZED);
    }
  }
};

function verifyCredentials(username, password) {
  return CREDENTIALS.USERNAME == username && CREDENTIALS.PASSWORD == password;
}