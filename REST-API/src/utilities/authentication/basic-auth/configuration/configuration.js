import { config } from "dotenv";
import { path } from "../../../../environment.js";

config({ path });

export const CREDENTIALS = {
  USERNAME: process.env.NAME,
  PASSWORD: process.env.PASSWORD,
};
