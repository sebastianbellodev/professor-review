import { config } from "dotenv";
import { path } from "../../../../environment.js";

config({ path });

export const credentials = {
  username: process.env.NAME,
  password: process.env.PASSWORD,
};
