package com.films4you.req2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RequirementTest {
  
  /**
   * Test the getValueAsString() method returns the expected value.
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    String value = r.getValueAsString();
    
    assertNotNull("Requiement value was null", value);
    
    assertEquals("KARL:SEAL:526:221.55,"
        + "ELEANOR:HUNT:148:216.54,"
        + "CLARA:SHAW:144:195.58,"
        + "RHONDA:KENNEDY:137:194.61,"
        + "MARION:SNYDER:178:194.61,"
        + "TOMMY:COLLAZO:459:186.62,"
        + "WESLEY:BULL:469:177.60,"
        + "TIM:CARY:468:175.61,"
        + "MARCIA:DEAN:236:175.58,"
        + "ANA:BRADLEY:181:174.66", value);
  }
  
  /**
   * Test the getHumanReadable() method returns the expected value.
   */
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    String expected = "1. KARL SEAL (526) - £221.55" + "\n"
        + "2. ELEANOR HUNT (148) - £216.54" + "\n"
        + "3. CLARA SHAW (144) - £195.58" + "\n"
        + "4. RHONDA KENNEDY (137) - £194.61" + "\n"
        + "5. MARION SNYDER (178) - £194.61" + "\n"
        + "6. TOMMY COLLAZO (459) - £186.62" + "\n"
        + "7. WESLEY BULL (469) - £177.60" + "\n"
        + "8. TIM CARY (468) - £175.61" + "\n"
        + "9. MARCIA DEAN (236) - £175.58" + "\n"
        + "10. ANA BRADLEY (181) - £174.66";
    
    assertEquals(expected, r.getHumanReadable());
  }
}
