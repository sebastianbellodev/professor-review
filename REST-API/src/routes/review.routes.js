import Router from "express-promise-router";
import { getReview, patchReview, postReview } from "../controllers/review.controllers.js";
import { validateToken, verifyToken } from "../utilities/authentication/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.patch("/reviews", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getReview(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.REVIEW_ALREADY_REGISTERED)
        : async () => {
            await patchReview(request);
            message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_PUT);
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

router.post("/reviews", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getReview(request);
      row.length > 0
        ? message(response, RES_CODE.BAD_REQUEST, RES_MESSAGE.REVIEW_ALREADY_REGISTERED)
        : async () => {
            await postReview(request);
            message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.REVIEW_POST);
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