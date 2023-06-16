import Router from "express-promise-router";
import { getAcademicOffering } from "../controllers/academicOffering.controllers.js";
import {
  deleteReview,
  getReview,
  getReviewsByEducationalExperience,
  patchReview,
  postReview,
  getReviewsByProfessor,
} from "../controllers/review.controllers.js";
import { getSyllabusesByEducationalExperienceEducationalProgram } from "../controllers/syllabus.controllers.js";
import { validateToken } from "../utilities/authentication/bearer/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../tools/message.js";

const router = Router();

router.delete("/reviews", validateToken, async (request, response) => {
  try {
    const [row] = await deleteReview(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_DELETE);
    } else {
      message(
        response,
        RESPONSE_CODE.NOT_FOUND,
        RESPONSE_MESSAGE.REVIEW_NOT_FOUND
      );
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.patch("/reviews", validateToken, async (request, response) => {
  try {
    const [row] = await getReview(request);
    if (row.length > 0) {
      message(
        response,
        RESPONSE_CODE.BAD_REQUEST,
        RESPONSE_MESSAGE.REVIEW_ALREADY_REGISTERED
      );
    } else {
      await patchReview(request);
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_PUT);
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post("/reviews", validateToken, async (request, response) => {
  try {
    console.log(request.body);
    const [row3] = await getSyllabusesByEducationalExperienceEducationalProgram(
      request
    );
    if (row3.length > 0) {
      request.body.idSyllabus = row3[0].idSyllabus;
      const [row2] = await getAcademicOffering(request);
      if (row2.length > 0) {
        request.body.idAcademicOffering = row2[0].idAcademicOffering;
        const [row] = await getReview(request);
        if (row.length > 0) {
          message(
            response,
            RESPONSE_CODE.BAD_REQUEST,
            RESPONSE_MESSAGE.REVIEW_ALREADY_REGISTERED
          );
        } else {
          await postReview(request);
          message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_POST);
        }
      } else {
        await postReview(request);
        message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_POST);
      }
    } else {
      await postReview(request);
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_POST);
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post(
  "/reviews/educationalexperience",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getReviewsByEducationalExperience(request);
      message(response, RESPONSE_CODE.OK, null, { reviews: row });
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
      );
    }
  }
);

router.post("/reviews/professor", validateToken, async (request, response) => {
  try {
    const [row] = await getReviewsByProfessor(request);
    const reviews = { reviews: row };
    message(response, RESPONSE_CODE.OK, null, reviews);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

export default router;
