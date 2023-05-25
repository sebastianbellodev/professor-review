import Router from "express-promise-router";
import { validateToken, verifyToken } from "../utilities/authentication/bearer";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message";
import { getReportOfProfessor } from "../controllers/report.controller";

const router = Router();

router.get("/reports/reportinformation", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await getReportOfProfessor(req);
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
