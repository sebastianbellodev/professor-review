import Router from "express-promise-router";
import {
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


router.delete("/review/deletes", validateToken,(request, response) => {
    try{
        verifyToken(request, response, async() => {
            const [row] = await deleteReview(request);
            row.affectedRows > 0
            ? message(response, RES_CODE.OK, RES_MESSAGE.REVIEW_DELETE)
            : message(RES_CODE.NOT_FOUND, RES_MESSAGE.REVIEW_NOT_FOUND);
        });
    }catch(err){        
        message(
          response,RES_CODE.INTERNAL_SERVER_ERROR,
            RES_MESSAGE.INTERAL_SERVER_ERROR,
            err
        );
    }
});

export default router;