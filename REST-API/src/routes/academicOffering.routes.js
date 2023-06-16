import Router from "express-promise-router";
import {
    validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
    message,
    RESPONSE_CODE,
    RESPONSE_MESSAGE
} from "../tools/message.js";
import {
    getAcademicOfferingById,
    postAcademicOffering,
    getAcademicOffering
} from "../controllers/academicOffering.controllers.js";

const router = Router();

router.post("/academicofferings", validateToken, async (request, response) => {
    try {
        const [row] = await getAcademicOffering(request);
        if (row.length > 0) {
            message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.ACADEMIC_OFFERING_ALREADY_REGISTERED)
        } else {
            await postAcademicOffering(request);
            message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.ACADEMIC_OFFERING_POST);
        }
    } catch (exception) {
        message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
    }
});

router.post("/academicofferings/id", validateToken, async (request, response) => {
    try {
        const [row] = await getAcademicOfferingById(request);
        if (row.length > 0) {
            message(response, RESPONSE_CODE.OK, null, { academicOfferings: row });
        } else {
            message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.ACADEMIC_OFFERING_NOT_FOUND);
        }
    } catch (exception) {
        message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
    }
});

export default router;