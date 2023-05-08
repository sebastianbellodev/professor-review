import express from "express";
import studentsRoutes from "./routes/students.routes.js";
import indexRoutes from "./routes/index.routes.js";

// npm run dev
const app = express();

app.use(express.json());

const api = "/api";

app.use(api, indexRoutes);
app.use(api, studentsRoutes);

app.use((req, res, next) => {
  res.status(404).json({ message: "URL not found." });
});

export default app;
