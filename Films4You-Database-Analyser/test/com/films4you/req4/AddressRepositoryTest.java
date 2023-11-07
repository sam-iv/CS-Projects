package com.films4you.req4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Map;
import org.junit.Test;


public class AddressRepositoryTest {

  /**
   * Test the getAllAddressCityIds() method returns the expected values.
   */
  @Test
  public void testGetAllAddressCityIds() throws SQLException {
    AddressRepository addressRepo = new AddressRepository();

    Map<Integer, Integer> addresses = addressRepo.getAllAddressCityIds();

    assertNotNull(addresses);
    assertEquals(603, addresses.size());
    assertTrue(addresses.containsKey(1));
    assertEquals(Integer.valueOf(300), addresses.get(1));
    assertEquals(Integer.valueOf(537), addresses.get(605));
  }
}
