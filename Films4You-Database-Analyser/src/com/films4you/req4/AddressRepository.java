package com.films4you.req4;

import com.films4you.main.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to act as a repository layer for processing the address to city ID map.

 * @author Samuel Ivuerah
 */
public class AddressRepository {

  /**
   * Gets a mapping between addresses and city IDs.

   * @return Map of addressId to cityId.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  public Map<Integer, Integer> getAllAddressCityIds() throws SQLException, IllegalStateException {
    Database db = new Database();
    Map<Integer, Integer> addresses = new HashMap<>();

    // Retrieve Address and add them to the ID-to-ID map.
    ResultSet rs = db.query("SELECT * FROM address");

    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    while (rs.next()) {
      addresses.put(rs.getInt(1), rs.getInt(5));
    }

    db.close(); // Close the database connection.

    return addresses;
  }
}
