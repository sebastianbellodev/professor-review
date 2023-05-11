import Router from "express-promise-router";
import {
  getStudent,
  getStudentByRegistrationNumber,
  postStudent,
  patchStudent,
  deleteStudent,
} from "../controllers/students.controllers.js";
import {
  validateToken,
  verifyToken,
} from "../utilities/security/bearer.authentication.js";

const router = Router();
const student = "Student";

router.get("/students", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    getStudent()
      .then(([row]) => res.status(200).json(row))
      .catch((err) => res.status(500).send(err));
  });
});

router.get("/students/:registrationNumber", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    getStudentByRegistrationNumber(req)
      .then(([row]) =>
        row.length > 0
          ? res.status(200).json(row)
          : res
              .status(404)
              .json({ message: `${student} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

router.post("/students", (req, res) => {
  postStudent(req)
    .then(() => res.sendStatus(204))
    .catch((err) => res.status(500).send(err));
});

router.patch("/students/:registrationNumber", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    patchStudent(req)
      .then(([row]) =>
        row.affectedRows > 0
          ? res.sendStatus(201)
          : res
              .status(404)
              .json({ message: `${student} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

router.delete("/students/:registrationNumber", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    deleteStudent(req)
      .then(([row]) =>
        row.affectedRows > 0
          ? res.sendStatus(204)
          : res
              .status(404)
              .json({ message: `${student} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

export default router;
