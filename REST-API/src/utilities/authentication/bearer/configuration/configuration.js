import { config } from "dotenv";

config({
  path: "../.env",
});

export const TOKEN_KEY = process.env.TOKEN_KEY;
