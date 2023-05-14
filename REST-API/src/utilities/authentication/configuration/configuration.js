import { config } from "dotenv";

config();

export const TOKEN_KEY = process.env.TOKEN_KEY;
export const USER = process.env.USER;
export const PASSWORD = process.env.PASSWORD;
