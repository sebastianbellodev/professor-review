import Router from "express-promise-router";
import { getFaculties, getFacultiesByName, patchFaculty } from "../controllers/faculty.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.get("/faculties", validateToken, async (request, response) => {
try {
    const [row] = await getFaculties();
    const faculties = { faculties: row };
    message(response, RESPONSE_CODE.OK, null, faculties);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR, exception);
  }
});

router.post ("/faculty", validateToken, async  (request, response) => {
    try{
        const [row] = await getFacultiesByName(request);
        row.length > 0
        ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.FACULTY__ALREADY_REGISTERED)
        : async () => {
            await logFaculty(request);
            message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.FACULTY_POST);
        }
    }catch(exception){
        message(
            response,
            RESPONSE_CODE.INTERNAL_SERVER_ERROR,
            RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
            exception
        );
    }
});

router.patch("/faculty", validateToken, async (request, response) => {
    try{
        const [row] = await getFacultiesByName(request);
        row.length < 0
        ? message(response, RESPONSE_CODE.BAD_REQUEST, RESPONSE_MESSAGE.FACULTY__ALREADY_REGISTERED)
        : async () => {
            await patchFaculty(request);
            message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.FACULTY_POST);
        }
    }catch(exception){
        message(
            response,
            RES_CODE.INTERNAL_SERVER_ERROR,
            RES_MESSAGE.INTERAL_SERVER_ERROR,
            exception
        );
    }
});

router.delete("/faculty", validateToken, (request, response) =>{
    try{
        verifyToken(request, reresponses, async() => {
            const [row] = await deleteFaculty(request);
            row.affectedRows > 0
            ? message(response, RES_CODE.OK, RES_MESSAGE.FACULTY_DELETED)
            : message(response, RES_CODE.NOT_FOUND, RES_MESSAGE.FACULTY_NOT_FOUND)
        });
    }catch(exception){
        message(
            response,
            RESPONSE_CODE.INTERNAL_SERVER_ERROR,
            RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
            exception
        );
    }
});

export default router;