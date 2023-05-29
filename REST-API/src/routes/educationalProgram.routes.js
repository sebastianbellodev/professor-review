import Router from "express-promise-router";
import { getEducationalPrograms, getEducationalProgramsByEducationalExperience, postEducationalProgram } from "../controllers/educationalProgram.controllers.js";
import { validateToken, verifyToken } from "../utilities/authentication/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/educationalprograms", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalPrograms();
      message(response, RESPONSE_CODE.OK, null, row);
    });
  } catch (exception) {
    message(
      res,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/educationalprograms/educationalexperience", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalProgramsByEducationalExperience(request);
      message(response, RESPONSE_CODE.OK, null, row);
    });
  } catch (exception) {
    message(
      res,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.post("/educationalprograms", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      await postEducationalProgram(request);
      message(res, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.EDUCATIONAL_PROGRAM_POST);
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