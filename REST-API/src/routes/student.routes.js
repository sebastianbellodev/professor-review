import Router from "express-promise-router";
import {
  deleteStudent,
  getStudentByEmailAddress,
  getStudentByPhoneNumber,
  getStudentByRegistrationNumber,
  getStudents,
  getStudentsByFaculty,
  patchStudent,
  postStudent,
} from "../controllers/student.controllers.js";
import {
  getUserByUsername,
  patchUser,
} from "../controllers/user.controllers.js";
import { validateToken } from "../utilities/authentication/bearer/bearer.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../tools/message.js";

const router = Router();

router.delete("/students", validateToken, async (request, response) => {
  try {
    const [row] = await deleteStudent(request);
    row.affectedRows > 0
      ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_DELETE)
      : message(
          response,
          RESPONSE_CODE.NOT_FOUND,
          RESPONSE_MESSAGE.STUDENT_NOT_FOUND
        );
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get(
  "/students/emailaddress",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getStudentByEmailAddress(request);
      row.length > 0
        ? () => {
            const student = { student: row };
            message(response, RESPONSE_CODE.OK, student);
          }
        : message(
            response,
            RESPONSE_CODE.NOT_FOUND,
            RESPONSE_MESSAGE.STUDENT_NOT_FOUND
          );
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
        exception
      );
    }
  }
);

router.get(
  "/students/phonenumber",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getStudentByPhoneNumber(request);
      const student = { student: row };
      row.length > 0
        ? () => {
            const student = { student: row };
            message(response, RESPONSE_CODE.OK, student);
          }
        : message(
            response,
            RESPONSE_CODE.NOT_FOUND,
            RESPONSE_MESSAGE.STUDENT_NOT_FOUND
          );
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
        exception
      );
    }
  }
);

router.get(
  "/students/registrationnumber",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getStudentByRegistrationNumber(request);
      row.length > 0
        ? () => {
            const student = { student: row };
            message(response, RESPONSE_CODE.OK, student);
          }
        : message(
            response,
            RESPONSE_CODE.NOT_FOUND,
            RESPONSE_MESSAGE.STUDENT_NOT_FOUND
          );
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
        exception
      );
    }
  }
);

router.get("/students", validateToken, async (request, response) => {
  try {
    const [row] = await getStudents();
    const students = { students: row };
    message(response, RESPONSE_CODE.OK, null, students);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.get("/students/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getStudentsByFaculty(request);
    const students = { students: row };
    message(response, RESPONSE_CODE.OK, null, students);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception
    );
  }
});

router.patch("/students", validateToken, async (request, response) => {
  try {
    const [emailAddressRow] = await getUserByUsername(request);
    const [phoneNumberRow] = await getStudentByPhoneNumber(request);
    const [usernameRow] = await getUserByUsername(request);
    if (
      emailAddressRow.affectedRows > 0 ||
      phoneNumberRow.affectedRows > 0 ||
      usernameRow.affectedRows > 0
    ) {
      message(
        response,
        RESPONSE_CODE.BAD_REQUEST,
        `${RESPONSE_MESSAGE.STUDENT_ALREADY_REGISTERED}; ${RESPONSE_MESSAGE.USER_ALREADY_REGISTERED}`
      );
    } else {
      const [studentRow] = await patchStudent(request);
      const [userRow] = await patchUser(request);
      studentRow.affectedRows > 0 || userRow.affectedRows > 0
        ? message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.INFORMATION_PUT)
        : message(
            response,
            RESPONSE_CODE.NOT_FOUND,
            `${RESPONSE_MESSAGE.STUDENT_NOT_FOUND}; ${RESPONSE_MESSAGE.USER_NOT_FOUND}`
          );
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR,
      exception.message
    );
  }
});

router.post("/students", validateToken, async (request, response) => {
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
