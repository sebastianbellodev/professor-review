import { Router } from "express";
import { ping } from "../controllers/index.controllers.js";

const router = Router();

router.get("/ping", function (req, res) {
  ping
    .then((row) => res.status(200).send(row[0]))
    .catch((err) => res.status(500).send(err));
});

export default router;
