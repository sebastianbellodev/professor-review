import Router from "express-promise-router";
import { getEducationalExperienceByFaculty } from "../controllers/educationalExperience.controllers.js";
import { validateToken, verifyToken } from "../utilities/authentication/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/educationalexperiences/faculty", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getEducationalExperienceByFaculty(request);
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

export default router;