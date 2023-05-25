import Router from "express-promise-router";
import {
  getStudent,
  getStudentByRegistrationNumber,
  postStudent,
  patchStudent,
  deleteStudent,
  getStudentByFaculty,
  statusUpdate,
} from "../controllers/student.controllers.js";
import {
  validateToken,
  verifyToken,
} from "../utilities/authentication/bearer.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/students", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getStudent();
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

router.get("/students", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getStudentByRegistrationNumber(req);
      row.length > 0
        ? message(res, RES_CODE.OK, null, row)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.STUDENT_NOT_FOUND);
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

router.post("/students", async (req, res) => {
  try {
    await postStudent(req);
    message(res, RES_CODE.CREATED, RES_MESSAGE.STUDENT_POST);
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});

router.patch("/students", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await patchStudent(req);
      row.affectedRows > 0
        ? message(res, RES_CODE.OK, RES_MESSAGE.STUDENT_PUT)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.STUDENT_NOT_FOUND);
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

router.delete("/students", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await deleteStudent(req);
      row.affectedRows > 0
        ? message(res, RES_CODE.OK, RES_MESSAGE.STUDENT_DELETE)
        : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.STUDENT_NOT_FOUND);
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

router.patch("/students/faculty", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getStudentByFaculty(req);
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

router.patch("/student/status", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await statusUpdate(req);
      message(res, RES_CODE.OK, RES_MESSAGE.DATA_POST);
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
