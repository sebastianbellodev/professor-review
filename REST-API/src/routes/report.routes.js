import Router from "express-promise-router";
import { getReportByProfessor } from "../controllers/report.controller";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/reports/professor", validateToken, async (request, response) => {
  try {
      const [row] = await getReportByProfessor(request);
      message(response, RESPONSE_CODE.OK, null, row);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});