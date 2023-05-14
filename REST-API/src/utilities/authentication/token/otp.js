import otpGenerator from "otp-generator";

export const generateOtp = () =>
  otpGenerator.generate(6, {
    digits: true,
    upperCaseAlphabets: true,
    specialChars: false,
  });
