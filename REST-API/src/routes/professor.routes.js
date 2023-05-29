import Router from "express-promise-router";
import {
  deleteProfessor,
  patchProfessor,
  postProfessor,
  getProfessors,
  getProfessorsByEducationalExperience,
  getProfessorsByFaculty,
  getProfessorById
} from "../controllers/professor.controllers.js";
import {
  validateToken,
  verifyToken
} from "../utilities/authentication/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../utilities/json/message.js";

const router = Router();

router.delete("/professors", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await deleteProfessor(request);
      row.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.PROFESSOR_DELETE)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.PROFESSOR_NOT_FOUND);
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

router.get("/professors/id", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getProfessorById(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.PROFESSOR_NOT_FOUND);
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

router.get("/professors", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getProfessors();
      message(response, RESPONSE_CODE.OK, null, row);
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

router.get("/professors/educationalexperience", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getProfessorsByEducationalExperience(request);
      message(response, RESPONSE_CODE.OK, null, row);
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

router.get("/professors/faculty", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await getProfessorsByFaculty(request);
      message(response, RESPONSE_CODE.OK, null, row);
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

router.patch("/professors", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      const [row] = await patchProfessor(request);
      row.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.PROFESSOR_PUT)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.PROFESSOR_NOT_FOUND);
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

router.post("/professors", validateToken, (request, response) => {
  try {
    verifyToken(request, response, async () => {
      await postProfessor(request);
      message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.PROFESSOR_POST);
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