package com.films4you.req3;

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
    
    assertEquals("ELEANOR:HUNT:148:ELEANOR.HUNT@sakilacustomer.org:46", value);
  }
  
  /**
   * Test the getHumanReadable() method returns the expected value.
   */
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    String expected = "Most Frequent Renter:" + "\n" 
        + "\t" + "Name: ELEANOR HUNT" + "\n"
        + "\t" + "ID: 148" + "\n"
        + "\t" + "Email: ELEANOR.HUNT@sakilacustomer.org" + "\n"
        + "\n"
        + "They have rented 46 times.";
    
    assertEquals(expected, r.getHumanReadable());
  }
}
