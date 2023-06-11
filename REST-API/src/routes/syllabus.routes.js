import Router from "express-promise-router";
import {
  deleteSyllabus,
  getSyllabusById,
  postSyllabus
} from "../controllers/syllabus.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.delete("/syllabus", validateToken, async (request, response) => {
  try {
    const [row] = await deleteSyllabus(request);
    row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.SYLLABUS_DELETE)
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.SYLLABUS_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/syllabus", validateToken, async (request, response) => {
  try {
    await postSyllabus(request);
    message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.SYLLABUS_POST);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/syllabus/id", validateToken, async (request, response) => {
  try {
    const [row] = await getSyllabusById(request);
    row.length > 0
      ? () => {
        const syllabus = { syllabus: row };
        message(response, RESPONSE_CODE.OK, null, syllabus);
      }
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.SYLLABUS_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

export default router;