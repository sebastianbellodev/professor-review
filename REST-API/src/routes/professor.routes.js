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
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.delete("/professors", validateToken, async (request, response) => {
  try {
    const [row] = await deleteProfessor(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.PROFESSOR_DELETE);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.PROFESSOR_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.get("/professors", validateToken, async (request, response) => {
  try {
    const [row] = await getProfessors();
    message(response, RESPONSE_CODE.OK, null, { professors: row });
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.patch("/professors", validateToken, async (request, response) => {
  try {
    const [row] = await patchProfessor(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.PROFESSOR_PUT);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.PROFESSOR_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/professors", validateToken, async (request, response) => {
  try {
    await postProfessor(request);
    message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.PROFESSOR_POST);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/professors/educationalexperience", validateToken, async (request, response) => {
  try {
    const [row] = await getProfessorsByEducationalExperience(request);
    message(response, RESPONSE_CODE.OK, null, { professors: row });
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/professors/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getProfessorsByFaculty(request);
    message(response, RESPONSE_CODE.OK, null, { professors: row });
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/professors/id", validateToken, async (request, response) => {
  try {
    const [row] = await getProfessorById(request);
    if (row.length > 0) {
      message(response, RESPONSE_CODE.OK, null, { professors: row });
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.PROFESSOR_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

export default router;