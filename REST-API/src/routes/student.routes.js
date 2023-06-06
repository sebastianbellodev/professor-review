import Router from "express-promise-router";
import {
  deleteStudent,
  getStudentByEmailAddress,
  getStudentByPhoneNumber,
  getStudentByRegistrationNumber,
  getStudents,
  getStudentsByFaculty,
  patchStudent,
  postStudent
} from "../controllers/student.controllers.js";
import {
  validateToken
} from "../utilities/authentication/bearer/bearer.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "../tools/message.js";

const router = Router();

router.delete("/students", validateToken, async(request, response) => {
  try {
      const [row] = await deleteStudent(request);
      row.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_DELETE)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/students/emailaddress", validateToken, async (request, response) => {
  try {
      const [row] = await getStudentByEmailAddress(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/students/phonenumber", validateToken, async (request, response) => {
  try {
      const [row] = await getStudentByPhoneNumber(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/students/registrationnumber", validateToken, async (request, response) => {
  try {
      const [row] = await getStudentByRegistrationNumber(request);
      row.length > 0
        ? message(response, RESPONSE_CODE.OK, null, row)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/students", validateToken, async (request, response) => {
  try {
      const [row] = await getStudents();
      message(response, RESPONSE_CODE.OK, null, row);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/students/faculty", validateToken, async(request, response) => {
  try {
      const [row] = await getStudentsByFaculty(request);
      message(response, RESPONSE_CODE.OK, null, row);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.patch("/students", validateToken, async(request, response) => {
  try {
      const [row] = await patchStudent(request);
      row.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_PUT)
        : message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.post("/students", async (request, response) => {
  try {
    await postStudent(request);
    message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.STUDENT_POST);
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