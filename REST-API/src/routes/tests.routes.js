import { Router } from "express";
import { ping } from "../controllers/tests.controllers.js";
import {
  verifyToken,
  validateToken,
} from "../utilities/security/bearer.authentication.js";

const router = Router();

router.get("/ping", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    ping()
      .then((row) => res.status(200).send(row[0]))
      .catch(() => res.status(500));
  });
});

export default router;
