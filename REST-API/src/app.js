import express from "express";
import studentsRoutes from "./routes/students.routes.js";
import testsRoutes from "./routes/tests.routes.js";
import usersRoutes from "./routes/users.routes.js";
import professorsRoutes from "./routes/professors.routes.js";

// npm run dev
const app = express();

app.use(express.json());

const api = "/api";

app.use(api, testsRoutes);
app.use(api, studentsRoutes);
app.use(api, usersRoutes);
app.use(api, professorsRoutes);

app.use((req, res, next) => {
  res.status(404).json({ message: "URL not found." });
});

export default app;
