import Router from "express-promise-router";
import {
  deleteUser,
  login,
  getUserByUsername,
  getUsers,
  patchUser,
  postUser
} from "../controllers/user.controllers.js";
import {
  validateCredentials,
  verifyCredentials
} from "../utilities/authentication/basic.js";
import {
  generateToken,
  validateToken,
  verifyToken
} from "../utilities/authentication/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../utilities/json/message.js";

const router = Router();

router.delete("/users", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await deleteUser(request);
      row.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_DELETE)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
    });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/users/login", validateCredentials, async (request, response) => {
  try {
      const [row] = await login(request);
      row.length > 0
        ? () => {
          const token = generateToken(request);
          message(response, RESPONSE_CODE.OK, token);
        }
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/users/signup", validateCredentials, (request, response) => {
  try {
      const token = generateToken(request);
      message(response, RESPONSE_CODE.OK, token);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/users/username", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getUserByUsername(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
    });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/users", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getUsers();
      message(response, RESPONSE_CODE.OK, null, row);
    });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.patch("/users", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await patchUser(request);
      row.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.USER_PUT)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.USER_NOT_FOUND);
    });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.post("/users", async (request, response) => {
  try {
    verifyToken(request, response, async () => {
      await postUser(request);
      message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.USER_POST);
    });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

export default router;