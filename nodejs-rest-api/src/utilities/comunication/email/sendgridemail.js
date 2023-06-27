import sendgridMail from "@sendgrid/mail";
import {
  TWILIO_SENDGRID_API_KEY,
  TWILIO_EMAIL,
} from "./configuration/configuration.js";

sendgridMail.setApiKey(TWILIO_SENDGRID_API_KEY);

export const sendMail = (request) => {
  const { emailAddress, otp } = request.body;
  return Promise.resolve(
    sendgridMail.send({
      to: emailAddress,
      from: TWILIO_EMAIL,
      subject: "OTP verification",
      text: `This is your security code: ${otp}`,
    })
  );
};