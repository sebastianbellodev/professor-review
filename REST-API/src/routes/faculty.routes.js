import Router from "express-promise-router";
import { getFaculties } from "../controllers/faculty.controllers.js";
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

router.post ("/logFaculty", validateToken, (req, res) => {
    try{
        verifyToken(req, res, async() => {
            const [row] = await logFaculty();
            message(res, RES_CODE.CREATED, RES_MESSAGE.FACULTY_POST);
        });
    }catch(err){
        message(
            res,
            RES_CODE.INTERNAL_SERVER_ERROR,
            RES_MESSAGE.INTERAL_SERVER_ERROR,
            err
        );
    }
});

router.patch("/updateFaculty", validateToken, (req, res) => {
    try{
        verifyToken(req, res, async() => {
            const [row] = await patchFaculty (req);
            row.affectedRows > 0
            ? message(res, RES_CODE.OK, RES_MESSAGE.FACULTY_PUT)
            : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.FACULTY_NOT_FOUND)
        });
    }catch(err){
        message(
            res,
            RES_CODE.INTERNAL_SERVER_ERROR,
            RES_MESSAGE.INTERAL_SERVER_ERROR,
            err
        );
    }
});

router.delete("/deleteFaculty", validateToken, (req, res) =>{
    try{
        verifyToken(req, res, async() => {
            const [row] = await deleteFaculty(req);
            row.affectedRows > 0
            ? message(res, RES_CODE.OK, RES_MESSAGE.FACULTY_DELETED)
            : message(res, RES_CODE.NOT_FOUND, RES_MESSAGE.FACULTY_NOT_FOUND)
        });
    }catch(err){
        message(
            res,
            RES_CODE.INTERNAL_SERVER_ERROR,
            RES_MESSAGE.INTERAL_SERVER_ERROR,
            err
        );
    }
});

export default router;