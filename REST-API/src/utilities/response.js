export const checkGet = async (row, res, obj) => {
  if (row.length <= 0) {
    return await res
      .status(404)
      .json({ message: `${obj} not found. Please try again.` });
  } else {
    return await res.status(200).json(row);
  }
};

export const checkDelete = async (row, res, obj) => {
  if (row.affectedRows <= 0) {
    return await res
      .status(404)
      .json({ message: `${obj} not found. Please try again.` });
  } else {
    return await res.sendStatus(204);
  }
};

export const checkPost = async (row, res, obj) => {
  if (row.affectedRows <= 0) {
    return await res
      .status(404)
      .json({ message: `${obj} not found. Please try again.` });
  } else {
    return await res.sendStatus(204);
  }
};

export const checkPatch = async (row, res, obj) => {
  if (row.affectedRows === 0) {
    return await res
      .status(404)
      .json({ message: `${obj} not found. Please try again.` });
  } else {
    return await res.sendStatus(201);
  }
};

export const sendInternalServerError = async (res) => {
  return await res
    .status(500)
    .json({ message: "Something went wrong. Please comeback later." });
};
