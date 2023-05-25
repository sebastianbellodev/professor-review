import Router from "express-promise-router";
import { ping } from "../controllers/test.controllers.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/pings", async (req, res) => {
  try {
    const [row] = await ping();
    message(res, RES_CODE.OK, null, row[0]);
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
