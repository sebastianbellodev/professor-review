import Router from "express-promise-router";
import {
  getSchoolPeriodById,
  getSchoolPeriods
} from "../controllers/schoolPeriod.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/schoolperiods", validateToken, async (request, response) => {
  try {
    const [row] = await getSchoolPeriods();
    const schoolPeriods = { schoolPeriods: row };
    message(response, RESPONSE_CODE.OK, null, schoolPeriods);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/schoolperiods/id", validateToken, async (request, response) => {
  try {
    const [row] = await getSchoolPeriodById(request);
    row.length > 0
      ? () => {
        const schoolPeriod = { schoolPeriod: row };
        message(response, RESPONSE_CODE.OK, null, schoolPeriod);
      }
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.SCHOOL_PERIOD_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

export default router;