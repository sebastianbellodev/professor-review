import Router from "express-promise-router";
import { getFaculties } from "../controllers/faculty.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/faculties", validateToken, async (request, response) => {
  try {
    const [row] = await getFaculties();
    const faculties = { faculties: row };
    message(response, RESPONSE_CODE.OK, null, faculties);
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