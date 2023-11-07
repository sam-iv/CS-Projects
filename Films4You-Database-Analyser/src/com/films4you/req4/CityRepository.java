package com.films4you.req4;

import com.films4you.main.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to act as a repository layer for the City class.

 * @author Samuel Ivuerah
 */
public class CityRepository {
  private Map<Integer, City> cities = new HashMap<>();

  /**
   * Gets cities, and instantiates them.

   * @return List of cities.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  public List<City> findAll() throws SQLException, IllegalStateException {
    Database db = new Database();

    // Retrieve cities.
    ResultSet rs = db.query("SELECT * FROM city");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    // Instantiation.
    while (rs.next()) {
      String cityName = rs.getString(2);
      Integer cityId = rs.getInt(1);
      
      if (cityName == null) {
        throw new IllegalArgumentException("A city must have a valid name");
      }
      if (cityId < 0) {
        throw new IllegalArgumentException("cityId must be greater than or equal to 0");
      }
      cities.put(cityId, new City(cityId, cityName));
    }

    db.close(); // Close the database connection.
    return new ArrayList<City>(cities.values());
  }

  /**
   * A method to increase the revenue made in a city, using a known amount and known city ID.

   * @param cityId The ID of the city to be increased.
   * @param amount The amount the revenue should be increased by.
   */
  public void increaseCityRevenue(int cityId, double amount) 
      throws IllegalArgumentException {
    City city = cities.get(cityId);
    
    if (city != null) {
      city.increaseCityRevenue(amount);
    }
  }
}
