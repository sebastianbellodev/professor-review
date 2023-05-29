import twilio from "twilio";
import {
  TWILIO_ACCOUNT_SID,
  TWILIO_AUTH_TOKEN,
  TWILIO_PHONE_NUMBER,
} from "./configuration/configuration.js";

const client = twilio(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);

export const sendMessage = (req) => {
  const { phoneNumber, otp } = req.body;
  return client.messages.create({
    to: phoneNumber,
    from: TWILIO_PHONE_NUMBER,
    body: `This your security code: ${otp}`,
  });
};