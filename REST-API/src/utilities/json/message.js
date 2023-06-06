export function message(res, code, message, content = null) {
  if (content === null) {
    return res.status(code).json({ message });
  } else if (message === null) {
    return res.status(code).json(content);
  } else {
    return res.status(code).json({ message, content });
  }
}

export const RES_CODE = {
  OK: 200,
  CREATED: 201,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403,
  NOT_FOUND: 404,
  DATA_ALREDY: 417,
  INTERNAL_SERVER_ERROR: 500,
};

export const RES_MESSAGE = {
  DATA_POST: "The data was posted succesfully.",

  USER_NOT_FOUND_OR_AUTHENTICATED: "User not found or authenticated.",
  USER_NOT_FOUND: "User not found.",
  USER_DUPLICATED: "User already signed up.",
  USER_POST: "User posted successfully.",
  USER_PUT: "User updated successfully.",
  USER_DELETE: "User deleted successfully.",
  USER_LOGED_IN: "Welcome back!",

  STUDENT_NOT_FOUND: "Student not found.",
  STUDENT_POST: "Student posted successfully.",
  STUDENT_PUT: "Student updated successfully.",
  STUDENT_DELETE: "Student deleted successfully.",

  PROFESSOR_NOT_FOUND: "Professor not found.",
  PROFESSOR_POST: "Professor posted successfully.",
  PROFESSOR_PUT: "Professor updated successfully.",
  PROFESSOR_DELETE: "Professor deleted successfully.",

  EDUCATIONAL_PROGRAM_NOT_FOUND: "Educational program not found.",
  EDUCATIONAL_PROGRAM_POST: "Educational program posted successfully.",
  EDUCATIONAL_PROGRAM_PUT: "Educational program updated successfully.",
  EDUCATIONAL_PROGRAM_DELETE: "Educational program deleted successfully.",

  REVIEW_NOT_FOUND: "Review not found.",
  REVIEW_DELETE: "Review deleted successfully.",

  FACULTY_POST: "Faculty posted successfully",
  FACULTY_PUT: "Faculty updated successfully",
  FACULTY_DELETED: "Faculty deleted successfuly",
  FACULTY_NOT_FOUND: "Faculty not found",

  DATA_ALREDY_LOG: "The data alredy in the sistem.",

  UNAUTHORIZED: "User not authorized.",
  FORBIDDEN: "Forbidden resource.",
  INTERAL_SERVER_ERROR: "Something went wrong. Try again later.",
};
