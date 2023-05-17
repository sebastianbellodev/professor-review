import  sendgridMail from "@sendgrid/mail";
import {SENDGRID_API_KEY, FROM_EMAIL} from "./configuration/configuration.js";

sendgridMail.setApiKey(SENDGRID_API_KEY);

export const createEmail = (to,subject,body) => {
    var message = {
        to:to,
        from: FROM_EMAIL,
        subject: subject,
        text: body,
    };
    return message;
};

export const sendEmail = (message) => {
    return Promise.resolve(
        sendgridMail.send(message)
)};

export const sendOTP = (req) => {
    const {email, otp} = req.body;
    return Promise.resolve(
        sendgridMail.send({
            to:email,
            from: FROM_EMAIL,
            subject: 'OTP verification',
            text: `This is your security code: ${otp}`,
        })
    )
};