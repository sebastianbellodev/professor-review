import Router from "express-promise-router";
import {
  activate,
  deleteStudent,
  getStudentByEmailAddress,
  getStudentByPhoneNumber,
  getStudentByRegistrationNumber,
  getStudents,
  getStudentsByFaculty,
  patchStatus,
  patchStudent,
  postStudent,
} from "../controllers/student.controllers.js";
import {
  getUserByUsername,
  patchUser,
} from "../controllers/user.controllers.js";
import { validateToken } from "../utilities/authentication/bearer/bearer.js";
import { generateOneTimePassword } from "../utilities/authentication/token/otp.js";
import { sendMessage } from "../utilities/comunication/messages/twilio.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../tools/message.js";

const router = Router();

router.delete("/students", validateToken, async (request, response) => {
  try {
    const [row] = await deleteStudent(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_DELETE);
    } else {
      message(
        response,
        RESPONSE_CODE.NOT_FOUND,
        RESPONSE_MESSAGE.STUDENT_NOT_FOUND
      );
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.get("/students", validateToken, async (request, response) => {
  try {
    const [row] = await getStudents();
    message(response, RESPONSE_CODE.OK, null, { students: row });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.patch("/students", validateToken, async (request, response) => {
  try {
    const [emailAddressRow] = await getStudentByEmailAddress(request);
    const [phoneNumberRow] = await getStudentByPhoneNumber(request);
    const [usernameRow] = await getUserByUsername(request);
    if (
      emailAddressRow.length > 0 ||
      phoneNumberRow.length > 0 ||
      usernameRow.length > 0
    ) {
      message(
        response,
        RESPONSE_CODE.BAD_REQUEST,
        RESPONSE_MESSAGE.INFORMATION_ALREADY_REGISTERED
      );
    } else {
      const [studentRow] = await patchStudent(request);
      const [userRow] = await patchUser(request);
      if (studentRow.affectedRows > 0 || userRow.affectedRows > 0) {
        message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.INFORMATION_PUT);
      } else {
        message(
          response,
          RESPONSE_CODE.NOT_FOUND,
          RESPONSE_MESSAGE.INFORMATION_NOT_FOUND
        );
      }
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.patch("/students/activate", validateToken, async (request, response) => {
  try {
    const [row] = await activate(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_PUT);
    } else {
      message(
        response,
        RESPONSE_CODE.NOT_FOUND,
        RESPONSE_MESSAGE.STUDENT_NOT_FOUND
      );
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post("/students/status", validateToken, async (request, response) => {
  try {
    const [row] = await patchStatus(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_PUT);
    } else {
      message(
        response,
        RESPONSE_CODE.NOT_FOUND,
        RESPONSE_MESSAGE.STUDENT_NOT_FOUND
      );
    }
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post("/students", validateToken, async (request, response) => {
  try {
    let oneTimePassword = generateOneTimePassword();
    request.body.oneTimePassword = oneTimePassword;
    sendMessage(request);
    await postStudent(request);
    message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.STUDENT_POST);
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post(
  "/students/emailaddress",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getStudentByEmailAddress(request);
      if (row.length > 0) {
        message(response, RESPONSE_CODE.OK, null, { students: row });
      } else {
        message(
          response,
          RESPONSE_CODE.NOT_FOUND,
          RESPONSE_MESSAGE.STUDENT_NOT_FOUND
        );
      }
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
      );
    }
  }
);

router.post("/students/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getStudentsByFaculty(request);
    message(response, RESPONSE_CODE.OK, null, { students: row });
  } catch (exception) {
    message(
      response,
      RESPONSE_CODE.INTERNAL_SERVER_ERROR,
      RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
    );
  }
});

router.post(
  "/students/phonenumber",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getStudentByPhoneNumber(request);
      if (row.length > 0) {
        message(response, RESPONSE_CODE.OK, null, { students: row });
      } else {
        message(
          response,
          RESPONSE_CODE.NOT_FOUND,
          RESPONSE_MESSAGE.STUDENT_NOT_FOUND
        );
      }
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
      );
    }
  }
);

router.post(
  "/students/registrationnumber",
  validateToken,
  async (request, response) => {
    try {
      const [row] = await getStudentByRegistrationNumber(request);
      if (row.length > 0) {
        message(response, RESPONSE_CODE.OK, null, { students: row });
      } else {
        message(
          response,
          RESPONSE_CODE.NOT_FOUND,
          RESPONSE_MESSAGE.STUDENT_NOT_FOUND
        );
      }
    } catch (exception) {
      message(
        response,
        RESPONSE_CODE.INTERNAL_SERVER_ERROR,
        RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR
      );
    }
  }
);

export default router;
