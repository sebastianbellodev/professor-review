import express from "express";
import studentsRoutes from "./routes/students.routes.js";
import testsRoutes from "./routes/tests.routes.js";
import usersRoutes from "./routes/users.routes.js";
import professorsRoutes from "./routes/professors.routes.js";
import educationalProgramsRoutes from "./routes/educationalProgram.routes.js";

// npm run dev
const app = express();

app.use(express.json());

const api = "/api";

app.use(api, testsRoutes);
app.use(api, studentsRoutes);
app.use(api, usersRoutes);
app.use(api, professorsRoutes);
app.use(api, educationalProgramsRoutes);

app.use((req, res, next) => {
  res.status(404).json({ message: "URL not found." });
});

export default app;
