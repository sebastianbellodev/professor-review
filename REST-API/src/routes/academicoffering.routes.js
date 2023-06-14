import Router from "express-promise-router";
import {
    validateToken
  } from "../utilities/authentication/bearer/bearer.js";
  import {
    message,
    RESPONSE_CODE,
    RESPONSE_MESSAGE
  } from "../tools/message.js";
  import{
    postAcademicOffering,
    getAcademicOffering
  } from "../controllers/academicoffering.js";

  const router = Router();

  router.post("/academicofferings", validateToken, async (request, response) => {
    try {
      const [row] = await getAcademicOffering(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.REVIEW_ALREADY_REGISTERED)
        : async () => {
          await postAcademicOffering(request);
          message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_POST);
        };
    } catch (exception) {
      message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
    }
  });

  export default router;