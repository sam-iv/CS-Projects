package com.films4you.req2;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A requirement to list the top ten customers by revenue.

 * @author Samuel Ivuerah
 */
public class Requirement implements RequirementInterface {
  
  /**
   * Gets customers and their payments, totalling their payments, and
   * reflecting this in each customer.

   * @return List of customers.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  private List<Customer> getCustomers() throws SQLException, IllegalStateException {
    Database db = new Database();
    Map<Integer, Customer> customers = new HashMap<>();
    
    // Retrieve customers.
    ResultSet rs = db.query("SELECT * FROM customer");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }
    
    // Instantiation.
    while (rs.next()) {
      Integer customerId = rs.getInt(1);
      String firstName = rs.getString(3);
      String lastName = rs.getString(4);
      
      if (customerId < 0) {
        throw new IllegalArgumentException("customerId must be greater than or equal to 0");
      }
      if (firstName == null || lastName == null) {
        throw new IllegalArgumentException("A Customer must have a first name and last name");
      }
      customers.put(rs.getInt(1), new Customer(customerId, firstName, lastName));
    }
    
    // Retrieve payments
    rs = db.query("SELECT * FROM payment");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }
    
    // Total payments for its respective customer.
    while (rs.next()) {
      int customerId = rs.getInt(2);
      double amountPaid = rs.getDouble(5);
      
      Customer customer = customers.get(customerId);
      if (customer != null) {
        customer.increaseAmountPaid(amountPaid);
      }
    }
    
    db.close(); // Close the database connection.
    return new ArrayList<Customer>(customers.values());
  }
  
  /**
   * Gets the top ten customers by revenue.

   * @return Array of customers, ordered by revenue.
   * @throws SQLException on database error.
   */
  private Customer[] getTopTenCustomers() throws SQLException {
    return getCustomers().stream()
        .sorted(Comparator.comparingDouble(Customer::getAmountPaid).reversed())
        .limit(10)
        .toArray(Customer[]::new);
  }
  
  /**
   * A method to return the top ten customers by revenue, by
   * using {@link com.films4you.req2.Customer#toString()}, to format.

   * @return A string containing the top ten customers.
   *     In the format: "{@linkplain firstName}:{@linkplain lastName}:
   *     {@linkplain customerId}:{@linkplain amountPaid}".
   */
  @Override
  public @Nullable String getValueAsString() {
    try {
      Customer[] topTenCustomers = getTopTenCustomers();
      
      if (topTenCustomers == null) {
        return null;
      }

      StringBuilder output = new StringBuilder();
      String delimiter = ",";
      for (Customer customer : topTenCustomers) {
        output.append(customer.toString()).append(delimiter);
      }

      // Removing the last delimiter
      output.setLength(output.length() - delimiter.length());

      return output.toString();
      
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * A method to acquire and parse the non-human-readable string
   * from {@link com.films4you.req2.Requirement#getValueAsString()}
   * and return it in human-readable format.

   * @return A string formatted for end user containing a display
   *     of all customers, with each customers': rank, first name, 
   *     last name, customer ID, and total amount paid. In the formant:
   *     "[RANK]. [firstName] [lastName] (customerId) - £[amountPaid], etc.".
   */
  @Override
  public @NonNull String getHumanReadable() {
    String value = getValueAsString();
    if (value == null) {
      return "Sorry, no results found or error occurred.";
    }

    String[] valueParts = value.split("\\,");
    StringBuilder result = new StringBuilder();

    int pos = 1;
    for (int i = 0; i < Math.min(valueParts.length, 10); i++) {
      String[] intermediateParts = valueParts[i].split(":");

      if (intermediateParts.length >= 4) {
        result.append(String.format("%s. %s %s (%s) - £%s\n", pos, 
            intermediateParts[0], intermediateParts[1], intermediateParts[2], 
            intermediateParts[3]));
        
        pos++;
      }
    }

    if (result.length() > 0) {
      return result.substring(0, result.length() - 1);
    } else {
      return "Sorry, no results found or error occurred.";
    }
  }
}
