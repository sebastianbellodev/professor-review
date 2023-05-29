import Router from "express-promise-router";
import {
  getEducationalExperienceById,
  getEducationalExperienceByName,
  getEducationalExperiences,
  getEducationalExperiencesByFaculty,
  patchEducationalExperience,
  postEducationalExperience
} from "../controllers/educationalExperience.controllers.js";
import {
  validateToken,
  verifyToken
} from "../utilities/authentication/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../utilities/json/message.js";

const router = Router();

router.get("/educationalexperiences/id", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalExperienceById(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_NOT_FOUND);
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

router.get("/educationalexperiences", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalExperiences();
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

router.get("/educationalexperiences/faculty", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalExperiencesByFaculty(request);
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

router.patch("/educationalexperiences", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalExperienceByName(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_ALREADY_REGISTERED)
        : async () => {
          await patchEducationalExperience(request);
          message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_PUT);
        };
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

router.post("/educationalexperiences", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalExperienceByName(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_ALREADY_REGISTERED)
        : async () => {
          await postEducationalExperience(request);
          message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_POST);
        };
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