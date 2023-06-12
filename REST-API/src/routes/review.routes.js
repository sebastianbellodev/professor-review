import Router from "express-promise-router";
import {
  deleteReview,
  getReview,
  getReviewsByEducationalExperience,
  patchReview,
  postReview
} from "../controllers/review.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.delete("/reviews", validateToken, async (request, response) => {
  try {
    const [row] = await deleteReview(request);
    row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_DELETE)
      : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.REVIEW_NOT_FOUND);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.patch("/reviews", validateToken, async (request, response) => {
  try {
    const [row] = await getReview(request);
    row.length > 0
      ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.REVIEW_ALREADY_REGISTERED)
      : async () => {
        await patchReview(request);
        message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_PUT);
      };
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/reviews", validateToken, async (request, response) => {
  try {
    const [row] = await getReview(request);
    row.length > 0
      ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.REVIEW_ALREADY_REGISTERED)
      : async () => {
        await postReview(request);
        message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_POST);
      };
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post("/reviews/educationalexperience", validateToken, async (request, response) => {
  try {
    const [row] = await getReviewsByEducationalExperience(request);
    const reviews = { reviews: row };
    message(response, RESPONSE_CODE.OK, null, reviews);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

export default router;