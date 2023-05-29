export function message(response, code, message, content = null) {
  if (content === null) {
    return response.status(code).json({ message });
  } else if (message === null) {
    return response.status(code).json(content);
  } else {
    return response.status(code).json({ message, content });
  }
}

export const RESPONSE_CODE = {
  OK: 200,
  CREATED: 201,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401,
  FORBIDDEN: 403,
  NOT_FOUND: 404,
  INTERNAL_SERVER_ERROR: 500,
};

export const RESPONSE_MESSAGE = {

  UNAUTHORIZED: "User not authorized.",
  FORBIDDEN: "Forbidden resource.",
  INTERNAL_SERVER_ERROR: "There is no connection to the database.",

  EDUCATIONAL_EXPERIENCE_ALREADY_REGISTERED: "Educational experience already registered in the system.",
  EDUCATIONAL_EXPERIENCE_NOT_FOUND: "Educational experience not found.",
  EDUCATIONAL_EXPERIENCE_POST: "Educational experience posted successfully.",
  EDUCATIONAL_EXPERIENCE_PUT: "Educational experience updated successfully.",
  EDUCATIONAL_EXPERIENCE_DELETE: "Educational experience deleted successfully.",

  EDUCATIONAL_PROGRAM_ALREADY_REGISTERED: "Educational program already registered in the system.",
  EDUCATIONAL_PROGRAM_NOT_FOUND: "Educational program not found.",
  EDUCATIONAL_PROGRAM_POST: "Educational program posted successfully.",
  EDUCATIONAL_PROGRAM_PUT: "Educational program updated successfully.",
  EDUCATIONAL_PROGRAM_DELETE: "Educational program deleted successfully.",

  FACULTY__ALREADY_REGISTERED: "Faculty already registered in the system.",
  FACULTY_NOT_FOUND: "Faculty not found.",
  FACULTY_POST: "Faculty posted successfully.",
  FACULTY_PUT: "Faculty updated successfully.",
  FACULTY_DELETE: "Faculty deleted successfully.",

  SCHOOL_PERIOD_NOT_FOUND: "School period not found.",
  
  REVIEW_ALREADY_REGISTERED: "Review already registered in the system.",
  REVIEW_NOT_FOUND: "Review not found.",
  REVIEW_POST: "Review posted successfully.",
  REVIEW_PUT: "Review updated successfully.",
  REVIEW_DELETE: "Review deleted successfully.",

  STUDENT_ALREADY_REGISTERED: "Student already registered in the system.",
  STUDENT_NOT_FOUND: "Student not found.",
  STUDENT_POST: "Student posted successfully.",
  STUDENT_PUT: "Student updated successfully.",
  STUDENT_DELETE: "Student deleted successfully.",

  SYLLABUS_ALREADY_REGISTERED: "Syllabus already registered in the system.",
  SYLLABUS_NOT_FOUND: "Syllabus not found.",
  SYLLABUS_POST: "Syllabus posted successfully.",
  SYLLABUS_PUT: "Syllabus updated successfully.",
  SYLLABUS_DELETE: "Syllabus deleted successfully.",

  PROFESSOR_ALREADY_REGISTERED: "Professor already registered in the system.",
  PROFESSOR_NOT_FOUND: "Professor not found.",
  PROFESSOR_POST: "Professor posted successfully.",
  PROFESSOR_PUT: "Professor updated successfully.",
  PROFESSOR_DELETE: "Professor deleted successfully.",

  USER_ALREADY_REGISTERED: "User already registered in the system.",
  USER_NOT_FOUND: "User not found.",
  USER_POST: "User posted successfully.",
  USER_PUT: "User updated successfully.",
  USER_DELETE: "User deleted successfully.",
};