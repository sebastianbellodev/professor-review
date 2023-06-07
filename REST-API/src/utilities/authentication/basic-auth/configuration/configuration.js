import { config } from "dotenv";

config({
  path: "../.env",
});

export const credentials = {
  username: process.env.NAME,
  password: process.env.PASSWORD,
};
