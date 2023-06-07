import { config } from "dotenv";
import { path } from "../../../../environment.js";

config({ path });

export const DB_PORT = process.env.DB_PORT;
export const DB_HOST = process.env.DB_HOST;
export const DB_USER = process.env.DB_USER;
export const DB_PASSWORD = process.env.DB_PASSWORD;
export const DB_NAME = process.env.DB_NAME;

export const TWILIO_SENDGRID_API_KEY = process.env.TWILIO_SENDGRID_API_KEY;
export const TWILIO_EMAIL = process.env.TWILIO_EMAIL;
