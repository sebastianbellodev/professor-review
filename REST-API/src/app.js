import express from "express";
import educationalProgramRoutes from "./routes/educationalProgram.routes.js";
import professorRoutes from "./routes/professor.routes.js";
import studentRoutes from "./routes/student.routes.js";
import testRoutes from "./routes/test.routes.js";
import userRoutes from "./routes/user.routes.js";
import {
  RESPONSE_CODE,
  RESPONSE_MESSAGE
} from "./tools/message.js";

const app = express();

app.use(express.json());

const api = "/api";

app.use(api, educationalProgramRoutes);
app.use(api, professorRoutes);
app.use(api, studentRoutes);
app.use(api, testRoutes);
app.use(api, userRoutes);

app.use((request, response, next) => {
  request.status(RESPONSE_CODE.NOT_FOUND).json({ message: RESPONSE_MESSAGE.URL_NOT_FOUND });
});

export default app;