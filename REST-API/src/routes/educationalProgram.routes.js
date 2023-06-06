import Router from "express-promise-router";
import { getEducationalProgramOfFaculty, postEducationalProgram } from "../controllers/educationalProgram.controllers.js";
import {
  generateToken,
  validateToken,
  verifyToken,
} from "../utilities/authentication/bearer.js";
import { message, RES_CODE, RES_MESSAGE } from "../utilities/json/message.js";

const router = Router();

router.post("/educationalProgram", validateToken, (req, res) => {
  try {
    verifyToken(req, res, async () => {
      const [row] = await postEducationalProgram(req);
      message(res, RES_CODE.CREATED, RES_MESSAGE.EDUCATIONAL_PROGRAM_POST);
    });
  } catch (err) {
    message(
      res,
      RES_CODE.INTERNAL_SERVER_ERROR,
      RES_MESSAGE.INTERAL_SERVER_ERROR,
      err
    );
  }
});


router.get("/educationalProgram", validateToken, (req, res) => {
  try{
    verifyToken(req, res, async () => {
      await getEducationalProgramOfFaculty(req);
      message(res, RES_CODE.OK, null, row)
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
