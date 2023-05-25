import express from "express";
import studentRoutes from "./routes/student.routes.js";
import testRoutes from "./routes/test.routes.js";
import userRoutes from "./routes/user.routes.js";
import professorRoutes from "./routes/professor.routes.js";
import educationalProgramRoutes from "./routes/educationalProgram.routes.js";

// npm run dev
const app = express();

app.use(express.json());

const api = "/api";

app.use(api, testRoutes);
app.use(api, studentRoutes);
app.use(api, userRoutes);
app.use(api, professorRoutes);
app.use(api, educationalProgramRoutes);

app.use((req, res, next) => {
  res.status(404).json({ message: "URL not found." });
});

export default app;
