import express from "express";
import academicOfferingRoutes from "./routes/academicOffering.routes.js"
import educationalExperienceRoutes from "./routes/educationalExperience.routes.js";
import educationalProgramRoutes from "./routes/educationalProgram.routes.js";
import facultyRoutes from "./routes/faculty.routes.js";
import professorRoutes from "./routes/professor.routes.js";
import reportRoutes from "./routes/report.routes.js";
import reviewRoutes from "./routes/review.routes.js";
import studentRoutes from "./routes/student.routes.js";
import syllabusesRoutes from "./routes/syllabus.routes.js";
import testRoutes from "./routes/test.routes.js";
import userRoutes from "./routes/user.routes.js";
import academicofferingRoutes from "./routes/academicoffering.routes.js";
import {
  message,
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "./tools/message.js";

const app = express();

app.use(express.json());

const api = "/api";

app.use(api, academicOfferingRoutes);
app.use(api, educationalExperienceRoutes);
app.use(api, educationalProgramRoutes);
app.use(api, facultyRoutes);
app.use(api, professorRoutes);
app.use(api, reportRoutes);
app.use(api, reviewRoutes);
app.use(api, studentRoutes);
app.use(api, syllabusesRoutes);
app.use(api, testRoutes);
app.use(api, userRoutes);
app.use(api, academicofferingRoutes);

app.use((request, response, next) => {
  message(response, RESPONSE_CODE.NOT_FOUND, RESPONSE_MESSAGE.URL_NOT_FOUND);
});

export default app;