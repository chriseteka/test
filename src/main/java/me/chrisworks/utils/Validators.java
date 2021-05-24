package me.chrisworks.utils;

import me.chrisworks.exceptions.AppException;

import java.time.LocalDate;

//Naive implementations
public class Validators {

  public static boolean isValidEmail(String email) {

    if (email != null && !email.matches("^(.+)@(.+)$"))
      throw new AppException("Invalid email passed, email must be of the form example@ddd.com");

    return true;
  }

  public static boolean isValidBirthDay(LocalDate birthDay) {

    if (birthDay != null && birthDay.isAfter(LocalDate.now()))
      throw new AppException("Invalid birthDate passed, date cannot be in the future");

    return true;
  }
}
