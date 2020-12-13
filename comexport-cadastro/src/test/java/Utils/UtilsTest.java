package Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

  @Test
  void validate() {
    assertTrue(Utils.validate("ribertojunior@gmail.com"));
    assertFalse(Utils.validate("ribertojuniorgmail.com"));
    assertFalse(Utils.validate("ribertojunior@gmail"));
    assertFalse(Utils.validate("@gmail.com"));
    assertFalse(Utils.validate(""));
    assertFalse(Utils.validate(null));
  }
}