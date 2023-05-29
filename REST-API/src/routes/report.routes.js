import Router from "express-promise-router";
import { getReportByProfessor } from "../controllers/report.controller";
import { validateToken, verifyToken } from "../utilities/authentication/bearer";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message";

const router = Router();

router.get("/reports/professor", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getReportByProfessor(request);
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