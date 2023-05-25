import Router from "express-promise-router";
import {
  getUser,
  getUserByUsername,
  postUser,
  patchUser,
  deleteUser,
} from "../controllers/user.controllers.js";
import {
  generateToken,
  validateToken,
  verifyToken,
} from "../utilities/authentication/bearer.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/users", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getUser();
      message(res, RES_CODE.OK, null, row);
    });
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

router.get("/users/username", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getUserByUsername(req);
      row.length > 0
        ? message(res, RES_CODE.OK, null, row)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.USER_NOT_FOUND);
    });
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

router.post("/users", async (req, res) => {
  try {
    await postUser(req);
    const token = generateToken(req);
    message(res, RES_CODE.CREATED, RES_MESSAGE.USER_POST, token);
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

router.patch("/users", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await patchUser(req);
      row.affectedRows > 0
        ? message(res, RES_CODE.OK, RES_MESSAGE.USER_PUT)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.USER_NOT_FOUND);
    });
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

router.delete("/users", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await deleteUser(req);
      row.affectedRows > 0
        ? message(res, RES_CODE.OK, RES_MESSAGE.USER_DELETE)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.USER_NOT_FOUND);
    });
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

export default router;
