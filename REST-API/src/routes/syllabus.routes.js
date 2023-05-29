import Router from "express-promise-router";
import { deleteSyllabus } from "../controllers/syllabus.controllers.js";
import { validateToken, verifyToken } from "../utilities/authentication/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.delete("/syllabus", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await deleteSyllabus(request);
      row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.SYLLABUS_DELETE)
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.SYLLABUS_NOT_FOUND);
    });
  } catch (exception) {
    message(
      res,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});