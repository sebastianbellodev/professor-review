import { config } from "dotenv";

config();

export const credentials = { 
    username: process.env.NAME, 
    password: process.env.PASSWORD };