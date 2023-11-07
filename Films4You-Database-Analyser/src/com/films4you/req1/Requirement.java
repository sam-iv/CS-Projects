package com.films4you.req1;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A requirement to find the total number of customers.
 * User Story 1: As Jon Stephens, the Account Executive, I want to know the total
 * number of customers, as well as the number of active and inactive customers,
 * so that I can evaluate customer growth and identify
 * areas where we can improve our services.
 * User Story 2: As Mike Hillyer, the Marketing Manager, I need to find the total
 * number of customers so that I can tailor our marketing campaigns effectively
 * and allocate resources efficiently.

 * @author Samuel Ivuerah
 */
public class Requirement implements RequirementInterface {
  
  /**
   * Gets the number of customers, along with the number of
   * inactive and active customers exclusively, by counting.

   * @return A string containing: the number of customers,
   *     the number of active customers, and the number of inactive
   *     customers. In the format: "[COUNT]:[COUNT]:[COUNT]"
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  private String getNumberOfCustomers() throws SQLException, IllegalStateException {
    Database db = new Database();
    int customerCount = 0;
    int activeCount = 0;
    int inactiveCount = 0;
    
    // Retrieve customers, increment respective counters by analysing record fields.
    ResultSet rs = db.query("SELECT * FROM customer");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }
    
    while (rs.next()) {
      if (rs.getInt(1) < 0) {
        throw new IllegalArgumentException("customerID must be greater than or equal to 0");
      }
      
      String activeType = rs.getString(7);
      if (activeType == null || activeType.isBlank()) {
        throw new IllegalArgumentException("active type cannot be null, empty, "
            + "or have only whitespace");
      }
      
      char value = activeType.charAt(0);
      if (value == '1') {
        activeCount++;
      }
      if (value == '0') {
        inactiveCount++;
      }
      customerCount++;
    }
    
    db.close(); // Close the database connection.
    
    return String.format("%d:%d:%d", customerCount, activeCount, 
        inactiveCount);
  }
  
  /**
   * A method to return the total number of customers along with
   * the amount of inactive and active customers.

   * @return A string containing the number of customers.
   *     In the format: "[COUNT]:[COUNT]:[COUNT]".
   */
  @Override
  public @Nullable String getValueAsString() {
    String value = "";
    try {
      value += getNumberOfCustomers();
      return value;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * A method to acquire and parse the non-human-readable string
   * from {@link com.films4you.req1.Requirement#getValueAsString()}
   * and return it in human-readable format.

   * @return A string formatted for the end user containing the
   *     number of customers and the amount of inactive and active customers.
   *     In the format: "The total number of customers is [COUNT], ([COUNT] Active,
   *     [COUNT] Inactive).".
   */
  @Override
  public @NonNull String getHumanReadable() {
    String value = getValueAsString();
    if (value == null) {
      return "No results found or an error has occurred.";
    }
    
    String[] parts = value.split(":");
    int totalCustomers = Integer.parseInt(parts[0]);
    int activeCustomers = Integer.parseInt(parts[1]);
    int inactiveCustomers = Integer.parseInt(parts[2]);

    String result = "The total number of customers is " + totalCustomers + ", ("
            + activeCustomers + " Active, " + inactiveCustomers + " Inactive).";
    return result;

  }
}
