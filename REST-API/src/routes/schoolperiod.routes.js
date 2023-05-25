import Router from "express-promise-router";
import {
  validateToken,
  verifyToken,
} from "../utilities/authentication/bearer.js";
import { getAllSchoolPeriods } from "../controllers/schoolPeriod.controllers.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/schoolperiods", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getAllSchoolPeriods();
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

export default router;
