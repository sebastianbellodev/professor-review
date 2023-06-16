import Router from "express-promise-router";
import {
  getEducationalProgramByName,
  getEducationalPrograms,
  getEducationalProgramsByFaculty,
  patchEducationalProgram,
  postEducationalProgram
} from "../controllers/educationalProgram.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/educationalprograms", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalPrograms();;
    message(response, RESPONSE_CODE.OK, null, { educationalPrograms: row });
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.patch("/educationalprograms", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalProgramByName(request);
    if (row.length > 0) {
      message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.EDUCATIONAL_PROGRAM_ALREADY_REGISTERED);
    } else {
      await patchEducationalProgram(request);
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.EDUCATIONAL_PROGRAM_PUT);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/educationalprograms", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalProgramByName(request);
    if (row.length > 0) {
      message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.EDUCATIONAL_PROGRAM_ALREADY_REGISTERED);
    } else {
      await postEducationalProgram(request);
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.EDUCATIONAL_PROGRAM_POST);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/educationalprograms/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalProgramsByFaculty(request);
    message(response, RESPONSE_CODE.OK, null, { educationalPrograms: row });
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

export default router;