import { config } from "dotenv";

config();

export const TOKEN_KEY = process.env.TOKEN_KEY;
export const USERNAME = process.env.USERNAME;
export const PASSWORD = process.env.PASSWORD;