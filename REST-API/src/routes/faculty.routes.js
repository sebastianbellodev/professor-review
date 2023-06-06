import Router from "express-promise-router";
import {
    validateToken,
    verifyToken,
} from "../utilities/authentication/bearer.js";
import { getFaculties, logFaculty } from "../controllers/faculty.controllers.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.get("/faculties",validateToken, (req,res) => {
    try{
        verifyToken(req, res, async () => {
            const [row] = await getFaculties();
            message(res, RES_CODE.OK, null, row);
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