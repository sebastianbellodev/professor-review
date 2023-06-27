import { config } from "dotenv";
import { path } from "../../../../environment.js";

config({ path });

export const TOKEN_KEY = process.env.TOKEN_KEY;