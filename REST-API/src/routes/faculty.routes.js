import Router from "express-promise-router";
import { getFaculties } from "../controllers/faculty.controllers.js";
import { validateToken, verifyToken } from "../utilities/authentication/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/faculties", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getFaculties();
      message(response, RESPONSE_CODE.OK, null, row);
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