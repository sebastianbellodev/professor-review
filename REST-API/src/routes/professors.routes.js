import Router from "express-promise-router";
import {
  getProfessor,
  getProfessorByIdProfessor,
  postProfessor,
  patchProfessor,
  deleteProfessor,
} from "../controllers/professors.controllers.js";
import {
  validateToken,
  verifyToken,
} from "../utilities/authentication/bearer.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();
const profesor = "Professor";

router.get("/professors", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getProfessor();
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

router.get("/professors/:idProfessor", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getProfessorByIdProfessor(req);
      row.length > 0
        ? message(res, RES_CODE.OK, null, row)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.PROFESSOR_NOT_FOUND);
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

router.post("/professors", async (req, res) => {
  try {
    await postProfessor(req);
    message(res, RES_CODE.CREATED, RES_MESSAGE.PROFESSOR_POST);
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

router.patch("/professors/:idProfessor", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await patchProfessor(req);
      row.affectedRows > 0
        ? message(res, RES_CODE.OK, RES_MESSAGE.PROFESSOR_PUT)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.PROFESSOR_NOT_FOUND);
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

router.delete("/professors/:idProfessor", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await deleteProfessor(req);
      row.affectedRows > 0
        ? message(res, RES_CODE.OK, RES_MESSAGE.PROFESSOR_DELETE)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.PROFESSOR_NOT_FOUND);
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
