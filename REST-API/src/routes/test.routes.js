import Router from "express-promise-router";
import { ping } from "../controllers/test.controllers.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/pings", async (response) => {
  try {
    const [row] = await ping();
    message(response, RESPONSE_CODE.OK, null, row[0]);
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