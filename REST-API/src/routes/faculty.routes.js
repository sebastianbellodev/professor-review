import Router from "express-promise-router";
import {
  deleteFaculty,
  getFacultyByName,
  getFaculties,
  patchFaculty,
  postFaculty,
} from "../controllers/faculty.controllers.js";
import { validateToken } from "../utilities/authentication/bearer/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../tools/message.js";

const router = Router();

router.delete("/faculties", validateToken, async (request, response) => {
  try {
    const [row] = await deleteFaculty(request);
    row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.FACULTY_DELETE)
      : message(
          response,
          RESPONSE_CODE.NOT_FOUND,
          RESPONSE_MESSAGE.FACULTY_DELETE
        );
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.get("/faculties", validateToken, async (request, response) => {
  try {
    const [row] = await getFaculties();
    const faculties = { faculties: row };
    message(response, RESPONSE_CODE.OK, null, faculties);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post("/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getFacultyByName(request);
    row.length > 0
      ? message(
          response,
          RESPONSE_CODE.BAD_REQUEST,
          RESPONSE_MESSAGE.FACULTY_ALREADY_REGISTERED
        )
      : async () => {
          await postFaculty(request);
          message(
            response,
            RESPONSE_CODE.CREATED,
            RESPONSE_MESSAGE.FACULTY_POST
          );
        };
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.patch("/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getFacultyByName(request);
    row.length > 0
      ? message(
          response,
          RESPONSE_CODE.BAD_REQUEST,
          RESPONSE_MESSAGE.FACULTY_ALREADY_REGISTERED
        )
      : async () => {
          await patchFaculty(request);
          message(
            response,
            RESPONSE_CODE.CREATED,
            RESPONSE_MESSAGE.FACULTY_PUT
          );
        };
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

export default router;
