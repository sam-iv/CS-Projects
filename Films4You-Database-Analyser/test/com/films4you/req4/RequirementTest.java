package com.films4you.req4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RequirementTest {
  
  /**
   * Test the getValueAsString() method returns the expected value.
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    
    String expected1 = "101:Cape Coral:221.55,"
        + "442:Saint-Denis:216.54,"
        + "42:Aurora:198.50,"
        + "340:Molodetno:195.58,"
        + "456:Santa Brbara dOeste:194.61,"
        + "29:Apeldoorn:194.61,"
        + "423:Qomsheh:186.62,"
        + "312:London:180.52,"
        + "388:Ourense (Orense):177.60,"
        + "78:Bijapur:175.61";
    
    String expected2 = "101:Cape Coral:221.55,"
        + "442:Saint-Denis:216.54,"
        + "42:Aurora:198.50,"
        + "340:Molodetno:195.58,"
        + "29:Apeldoorn:194.61,"
        + "456:Santa Brbara dOeste:194.61,"
        + "423:Qomsheh:186.62,"
        + "312:London:180.52,"
        + "388:Ourense (Orense):177.60,"
        + "78:Bijapur:175.61";
    
    String actual = r.getValueAsString();
    
    assertNotNull("Requirement value was null", actual);
    
    assertTrue(actual.equals(expected1) || actual.equals(expected2));
  }
  
  /**
   * Test the getHumanReadable() method returns the expected value.
   */
  @Test
  public void testRequiremntGetHumanReadable() {
    Requirement r = new Requirement();
    
    String expected1 = "1. Cape Coral (101) - £221.55" + "\n"
        + "2. Saint-Denis (442) - £216.54" + "\n"
        + "3. Aurora (42) - £198.50" + "\n"
        + "4. Molodetno (340) - £195.58" + "\n"
        + "5. Santa Brbara dOeste (456) - £194.61" + "\n"
        + "6. Apeldoorn (29) - £194.61" + "\n"
        + "7. Qomsheh (423) - £186.62" + "\n"
        + "8. London (312) - £180.52" + "\n"
        + "9. Ourense (Orense) (388) - £177.60" + "\n"
        + "10. Bijapur (78) - £175.61";
    
    String expected2 = "1. Cape Coral (101) - £221.55" + "\n"
        + "2. Saint-Denis (442) - £216.54" + "\n"
        + "3. Aurora (42) - £198.50" + "\n"
        + "4. Molodetno (340) - £195.58" + "\n"
        + "5. Apeldoorn (29) - £194.61" + "\n"
        + "6. Santa Brbara dOeste (456) - £194.61" + "\n"
        + "7. Qomsheh (423) - £186.62" + "\n"
        + "8. London (312) - £180.52" + "\n"
        + "9. Ourense (Orense) (388) - £177.60" + "\n"
        + "10. Bijapur (78) - £175.61";
    
    String actual = r.getHumanReadable();
    
    assertNotNull("Human-readable value was null", actual);
    
    assertTrue(actual.equals(expected1) || actual.equals(expected2));
  }
}
