import Router from "express-promise-router";
import {
  getEducationalExperienceById,
  getEducationalExperienceByName,
  getEducationalExperiences,
  getEducationalExperiencesByFaculty,
  getEducationalExperiencesByEducationalProgram,
  patchEducationalExperience,
  postEducationalExperience
} from "../controllers/educationalExperience.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/educationalexperiences", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalExperiences();
    const educationalExperiences = { educationalExperiences: row };
    message(response, RESPONSE_CODE.OK, null, educationalExperiences);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.patch("/educationalexperiences", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalExperienceByName(request);
    row.length > 0
      ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_ALREADY_REGISTERED)
      : async () => {
        await patchEducationalExperience(request);
        message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_PUT);
      };
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/educationalexperiences", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalExperienceByName(request);
    row.length > 0
      ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_ALREADY_REGISTERED)
      : async () => {
        await postEducationalExperience(request);
        message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_POST);
      };
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/educationalexperiences/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalExperiencesByFaculty(request);
    const educationalExperiences = { educationalExperiences: row };
    message(response, RESPONSE_CODE.OK, null, educationalExperiences);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/educationalexperiences/id", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalExperienceById(request);
    row.length > 0
      ? () => {
        const educationalExperience = { educationalExperience, row };
        message(response, RESPONSE_CODE.OK, null, educationalExperience)
      }
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/educationalexperiences/name", validateToken, async (request, response) => {
  try {
    const [row] = await getEducationalExperienceByName(request);
    row.length > 0
      ? () => {
        const educationalExperience = { educationalExperience, row };
        message(response, RESPONSE_CODE.OK, null, educationalExperience)
      }
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

export default router;