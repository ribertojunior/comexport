package org.example.comexport.cadastro.utils;

import org.example.comexport.jpa.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public static boolean validateEmail(String emailStr) {
    if (emailStr == null) return false;
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    return matcher.find();
  }

  public static boolean validaUser(User user) {
    return user != null && !user.getName().trim().isEmpty()
        && validateEmail(user.getEmail()) && user.getBirthday() != null;
  }
}
