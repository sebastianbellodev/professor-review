import Router from "express-promise-router";
import {
  getUser,
  getUserByUsername,
  postUser,
  patchUser,
  deleteUser,
} from "../controllers/users.controllers.js";
import {
  createToken,
  validateToken,
  verifyToken,
} from "../utilities/security/bearer.authentication.js";

const router = Router();
const user = "User";

router.get("/users", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    getUser()
      .then(([row]) => res.status(200).json(row))
      .catch((err) => res.status(500).send(err));
  });
});

router.get("/users/:username", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    getUserByUsername(req)
      .then(([row]) =>
        row.length > 0
          ? res.status(200).json(row)
          : res
              .status(404)
              .json({ message: `${user} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

router.post("/users", (req, res) => {
  postUser(req)
    .then(() => {
      const token = createToken(req);
      res.json({ token });
    })
    .catch((err) => res.status(500).send(err));
});

router.patch("/users/:username", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    patchUser(req)
      .then(([row]) =>
        row.affectedRows > 0
          ? res.sendStatus(201)
          : res
              .status(404)
              .json({ message: `${user} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

router.delete("/users/:username", validateToken, (req, res) => {
  verifyToken(req, res, () => {
    deleteUser(req)
      .then(([row]) =>
        row.affectedRows > 0
          ? res.sendStatus(204)
          : res
              .status(404)
              .json({ message: `${user} not found. Please try again.` })
      )
      .catch((err) => res.status(500).send(err));
  });
});

export default router;
