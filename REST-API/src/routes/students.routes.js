import { Router } from "express";
import {
  getStudent,
  getStudentRegistrationNumber,
  logStudent,
  updateStudent,
  deleteStudent,
} from "../controllers/students.controllers.js";
const router = Router();

router.get("/students", getStudent);

router.get("/students/:registrationNumber", getStudentRegistrationNumber);

router.post("/students", logStudent);

router.patch("/students/:registrationNumber", updateStudent);

router.delete("/students/:registrationNumber", deleteStudent);

export default router;
