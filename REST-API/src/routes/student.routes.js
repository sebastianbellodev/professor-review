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
import { validateToken } from "../utilities/authentication/bearer/bearer.js";
import { generateOneTimePassword } from "../utilities/authentication/token/otp.js"
import { sendMail } from "../utilities/comunication/email/sendgridemail.js";
import { sendMessage } from "../utilities/comunication/messages/twilio.js";
import { message, RESPONSE_CODE, RESPONSE_MESSAGE } from "../tools/message.js";

const router = Router();

router.delete("/students", validateToken, async (request, response) => {
  try {
    const [row] = await deleteStudent(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_DELETE);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.get("/students", validateToken, async (request, response) => {
  try {
    const [row] = await getStudents();
    const students = { students: row };
    message(response, RESPONSE_CODE.OK, null, students);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.patch("/students", validateToken, async (request, response) => {
  try {
    const [row] = await patchStudent(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_PUT);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.patch("/students/activate", validateToken, async (request, response) => {
  try {
    const [row] = await activate(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_PUT);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.patch("/students/status", validateToken, async (request, response) => {
  try {
    const [row] = await patchStatus(request);
    if (row.affectedRows > 0) {
      message(response, RESPONSE_CODE.OK, RESPONSE_MESSAGE.STUDENT_PUT);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/students", validateToken, async (request, response) => {
  try {
    const otp = generateOneTimePassword();
    request.body.otp = otp;
    await postStudent(request);
    message(response, RESPONSE_CODE.CREATED, RESPONSE_MESSAGE.STUDENT_POST);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/students/emailaddress", validateToken, async (request, response) => {
  try {
    const [row] = await getStudentByEmailAddress(request);
    if (row.length > 0) {
      const students = { students: row };
      message(response, RESPONSE_CODE.OK, null, students);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/students/faculty", validateToken, async (request, response) => {
  try {
    const [row] = await getStudentsByFaculty(request);
    const students = { students: row };
    message(response, RESPONSE_CODE.OK, null, students);
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/students/phonenumber", validateToken, async (request, response) => {
  try {
    const [row] = await getStudentByPhoneNumber(request);
    if (row.length > 0) {
      const students = { student: row };
      message(response, RESPONSE_CODE.OK, null, students);
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

router.post("/students/registrationnumber", validateToken, async (request, response) => {
  try {
    const [row] = await getStudentByRegistrationNumber(request);
    if (row.length > 0) {
      const students = { students: row };
      message(response, RESPONSE_CODE.OK, null, students)
    } else {
      message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.STUDENT_NOT_FOUND);
    }
  } catch (exception) {
    message(response, RESPONSE_CODE.INTERNAL_SERVER_ERROR, RESPONSE_MESSAGE.INTERNAL_SERVER_ERROR);
  }
});

export default router;