package org.example.comexport.cadastro.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

  @Test
  void validate() {
    assertTrue(Utils.validateEmail("ribertojunior@gmail.com"));
    assertFalse(Utils.validateEmail("ribertojuniorgmail.com"));
    assertFalse(Utils.validateEmail("ribertojunior@gmail"));
    assertFalse(Utils.validateEmail("@gmail.com"));
    assertFalse(Utils.validateEmail(""));
    assertFalse(Utils.validateEmail(null));
  }
}