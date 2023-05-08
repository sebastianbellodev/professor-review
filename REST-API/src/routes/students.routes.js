import Router from "express-promise-router";
import {
  getStudent,
  getStudentRegistrationNumber,
  postStudent,
  patchStudent,
  deleteStudent,
} from "../controllers/students.controllers.js";

const router = Router();
const student = "Student";

router.get("/students", function (req, res) {
  getStudent
    .then(([row]) => res.status(200).json(row))
    .catch((err) => res.status(500).send(err));
});

router.get("/students/:registrationNumber", function (req, res) {
  const registrationNumber = req.params.registrationNumber;

  getStudentRegistrationNumber(registrationNumber)
    .then(([row]) =>
      row.length > 0
        ? res.status(200).json(row)
        : res
            .status(404)
            .json({ message: `${student} not found. Please try again.` })
    )
    .catch((err) => res.status(500).send(err));
});

router.post("/students", function (req, res) {
  const values = {
    registrationNumber: req.body.registrationNumber,
    name: req.body.name,
    paternalSurname: req.body.paternalSurname,
    maternalSurname: req.body.maternalSurname,
    emailAddress: req.body.emailAddress,
    idEducationalProgram: req.body.idEducationalProgram,
  };

  postStudent(values)
    .then(([row]) => res.sendStatus(204))
    .catch((err) => res.status(500).send(err));
});

router.patch("/students/:registrationNumber", function (req, res) {
  const registrationNumber = req.params.registrationNumber;
  const values = {
    name: req.body.name,
    paternalSurname: req.body.paternalSurname,
    maternalSurname: req.body.maternalSurname,
  };

  patchStudent(values, registrationNumber)
    .then(([row]) =>
      row.affectedRows > 0
        ? res.sendStatus(201)
        : res
            .status(404)
            .json({ message: `${student} not found. Please try again.` })
    )
    .catch((err) => res.status(500).send(err));
});

router.delete("/students/:registrationNumber", function (req, res) {
  const registrationNumber = req.params.registrationNumber;

  deleteStudent(registrationNumber)
    .then(([row]) =>
      row.affectedRows > 0
        ? res.sendStatus(204)
        : res
            .status(404)
            .json({ message: `${student} not found. Please try again.` })
    )
    .catch((err) => res.status(500).send(err));
});

export default router;
