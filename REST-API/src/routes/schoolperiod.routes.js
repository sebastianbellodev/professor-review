import Router from "express-promise-router";
import { getSchoolPeriodById, getSchoolPeriods } from "../controllers/schoolPeriod.controllers.js";
import { validateToken, verifyToken,} from "../utilities/authentication/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/schoolperiods/id", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getSchoolPeriodById(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.SCHOOL_PERIOD_NOT_FOUND);
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

router.get("/schoolperiods", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getSchoolPeriods();
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

export default router;