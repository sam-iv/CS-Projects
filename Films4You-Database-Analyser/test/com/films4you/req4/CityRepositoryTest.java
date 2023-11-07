package com.films4you.req4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

public class CityRepositoryTest {

  /**
   * Test the findAll() method returns the expected values.
   */
  @Test
  public void testFindAll() throws SQLException {
    CityRepository cityRepository = new CityRepository();

    List<City> cities = cityRepository.findAll();

    assertNotNull(cities);
    assertEquals(600, cities.size());
    assertEquals("A Corua (La Corua)", cities.get(0).getCity());
    assertEquals("Ziguinchor", cities.get(599).getCity());
  }
}
