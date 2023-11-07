package com.films4you.req3;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A requirement to find the customer who is the most frequent renter.

 * @author Samuel Ivuerah
 */
public class Requirement implements RequirementInterface {
  
  /**
   * Gets renters (Customers), and counts each rental for each renter, and
   * stores them respectively.

   * @return List of customers.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  private List<Customer> getRenters() throws SQLException, IllegalStateException {
    Database db = new Database();
    Map<Integer, Customer> customers = new HashMap<>();
    
    // Retrieve customers.
    ResultSet rs = db.query("SELECT * FROM customer");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }
    
    // Instantiation.
    while (rs.next()) {
      int customerId = rs.getInt(1);
      String firstName = rs.getString(3);
      String lastName = rs.getString(4);
      String email = rs.getString(5);
      
      if (customerId < 0) {
        throw new IllegalArgumentException("customerId must be greater than or equal to 0");
      }
      if (firstName == null || lastName == null || email == null) {
        throw new IllegalArgumentException("A Customer must have a non-null first name, "
            + "last name, and email");
      }
      customers.put(rs.getInt(1), new Customer(customerId, firstName, lastName, email));
    }
    
    // Retrieve rentals.
    rs = db.query("SELECT * FROM rental");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }
    
    // Increment rentals count for its respective customer.
    while (rs.next()) {
      int customerId = rs.getInt(4);
      Customer customer = customers.get(customerId);
      if (customer != null) {
        customer.incrementRentCount();
      }
    }
    
    db.close(); // Close the database connection.
    return new ArrayList<Customer>(customers.values());
  }
  
  /**
   * Gets the most frequent renter.

   * @return Customer object, who has rented the most.
   * @throws SQLException on database error.
   */
  private Customer getMostFrequentRenter() throws SQLException {
    Comparator<Customer> compareByRentCount = Comparator.comparingInt(Customer::getRentCount)
        .reversed();
    List<Customer> sortedRenters = new ArrayList<>();
    
    sortedRenters = getRenters();
    Collections.sort(sortedRenters, compareByRentCount);
    
    return sortedRenters.get(0);
  }
  
  /**
   *  A method to return the most frequent renter, by using
   *  {@link com.films4you.req3.Customer#toString()}, to format.

   *  @return A string containing the most frequent renter.
   *     In the format: "{@linkplain firstName}:{@linkplain lastName}
   *     :{@linkplain customerId}:{@linkplain email}:{@linkplain rentCount}".
   */
  @Override
  public @Nullable String getValueAsString() {
    try {
      Customer mostFrequentRenter = getMostFrequentRenter();
      String output = mostFrequentRenter.toString();
      
      return output;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * A method to acquire and parse the non-human-readable string
   * from {@link com.films4you.req3.Requirement#getValueAsString()}
   * and return it in human-readable format.

   * @return A string formatted for end user containing a display
   *     of the most frequent renter and their details such as their:
   *     first name, last name, customer ID, email, and total number
   *     of rentals. In the format:
   *     "Most Frequent Renter:
   *        Name: [firstName] [lastName]
   *        ID: [customerId]
   *        Email: [email]
   *        
   *     They have rented [rentCount] times."
   */
  @Override
  public @NonNull String getHumanReadable() {
    String value = getValueAsString();
    if (value == null) {
      return "Sorry, no results found or error occurred.";
    }
    
    String[] parts = value.split(":");
    String output = "Most Frequent Renter:" + "\n";
    output += "\t" + "Name: " + parts[0] + " " + parts[1] + "\n";
    output += "\t" + "ID: " + parts[2] + "\n";
    output += "\t" + "Email: " + parts[3] + "\n" + "\n";
    output += "They have rented " + parts[4] + " times.";
    
    return output;
  }

}
