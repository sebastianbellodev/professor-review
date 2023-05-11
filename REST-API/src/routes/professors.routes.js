import Router from "express-promise-router";
import {
  getProfessor,
  getProfessorByIdProfessor,
  postProfessor,
  patchProfessor,
  deleteProfessor,
} from "../controllers/professors.controllers.js";
import {
  validateToken,
  verifyToken,
} from "../utilities/security/bearer.authentication.js";

const router = Router();
const profesor = "Professor";

router.get("/professors", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    getProfessor()
      .then(([row]) => res.status(200).json(row))
      .catch((err) => res.status(500).send(err));
  });
});

router.get("/professors/:idProfessor", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    getProfessorByIdProfessor(req)
      .then(([row]) =>
        row.length > 0
          ? res.status(200).json(row)
          : res
              .status(404)
              .json({ message: `${profesor} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

router.post("/professors", (req, res) => {
  postProfessor(req)
    .then(() => res.sendStatus(204))
    .catch((err) => res.status(500).send(err));
});

router.patch("/professors/:idProfessor", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    patchProfessor(req)
      .then(([row]) =>
        row.affectedRows > 0
          ? res.sendStatus(201)
          : res
              .status(404)
              .json({ message: `${student} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

router.delete("/professors/:idProfessor", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    deleteProfessor(req)
      .then(([row]) =>
        row.affectedRows > 0
          ? res.sendStatus(204)
          : res
              .status(404)
              .json({ message: `${student} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

export default router;
