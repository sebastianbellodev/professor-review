import Router from "express-promise-router";
import {
  deleteUser,
  login,
  getUserByUsername,
  getUsers,
  patchUser,
  postUser,
} from "../controllers/user.controllers.js";
import { validateCredentials } from "../utilities/authentication/basic-auth/basic.js";
import {
  generateToken,
  validateToken,
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.delete("/users", validateToken, async (request, response) => {
  try {
    const [row] = await deleteUser(request);
    row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_DELETE)
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.get("/users/signup", validateCredentials, (request, response) => {
  try {
    const token = { token: generateToken(request) };
    message(response, RESPONSE_CODE.OK, null, token);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception.message
    );
  }
});

router.get("/users", validateToken, async (request, response) => {
  try {
    const [row] = await getUsers();
    const users = { users: row };
    message(response, RESPONSE_CODE.OK, null, users);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.patch("/users", validateToken, async (request, response) => {
  try {
    const [row] = await patchUser(request);
    row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_PUT)
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/users", validateToken, async (request, response) => {
  try {
    await postUser(request);
    message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.USER_POST);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/users/login", validateCredentials, async (request, response) => {
  try {
    const [row] = await login(request);
    if (row.length > 0) {
      const token = { token: generateToken(request) };
      message(response, RESPONSE_CODE.OK, null, token);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});


router.post("/users/username", validateToken, async (request, response) => {
  try {
    const [row] = await getUserByUsername(request);
    row.length > 0
      ? () => {
        const user = { user: row };
        message(response, RESPONSE_CODE.OK, null, user);
      }
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

export default router;