import Router from "express-promise-router";
import { ping } from "../controllers/test.controllers.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.post("/pings", async (request, response) => {
  try {
    const [row] = await ping();
    var o = {}
    const test = "test"
    o[test] = []
    o[test].push(row[0])
    message(response, RESPONSE_CODE.OK, null, o);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

export default router;