import Router from "express-promise-router";
import {
    getAcademicOfferingById
} from "../controllers/academicOffering.controllers.js";
import {
    validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
    message,
    RESPONSE_CODE,
    RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.post("/academicofferings/id", validateToken, async (request, response) => {
    try {
        const [row] = await getAcademicOfferingById(request);
        if (row.length > 0) {
            message(response, RESPONSE_CODE.OK, null, { academicOfferings: row });
        } else {
            message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.EDUCATIONAL_EXPERIENCE_NOT_FOUND);
        }
    } catch (exception) {
        message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
    }
});

export default router;