package com.films4you.req1;

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
    
    assertNotNull("Requirement value was null", value);
    
    assertEquals("599:584:15", value);
  }
  
  /**
   * Test the getHumanReadable() method returns the expected value.
   */
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    assertEquals("The total number of customers is 599, (584 Active, 15 Inactive).",
        r.getHumanReadable());
  }
}
