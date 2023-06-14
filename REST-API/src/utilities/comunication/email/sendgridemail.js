import sendgridMail from "@sendgrid/mail";
import {
  TWILIO_SENDGRID_API_KEY,
  TWILIO_EMAIL,
} from "./configuration/configuration.js";

sendgridMail.setApiKey(TWILIO_SENDGRID_API_KEY);

export const createEmail = (to, subject, body) => {
  var message = {
    to: to,
    from: FROM_EMAIL,
    subject: subject,
    text: body,
  };
  return message;
};

export const sendEmail = (message) => {
  return Promise.resolve(sendgridMail.send(message));
};

export const sendOTP = (request) => {
  const { email, otp } = request.body;
  return Promise.resolve(
    sendgridMail.send({
      to: email,
      from: TWILIO_EMAIL,
      subject: "OTP verification",
      text: `This is your security code: ${otp}`,
    })
  );
};
