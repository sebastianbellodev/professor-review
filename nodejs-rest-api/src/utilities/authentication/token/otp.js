import otpGenerator from "otp-generator";

export const generateOneTimePassword = () =>
  otpGenerator.generate(6,{
    digits: true,
    upperCaseAlphabets: true,
    specialChars: false,
  });