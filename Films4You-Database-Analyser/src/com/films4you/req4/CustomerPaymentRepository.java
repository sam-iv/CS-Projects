package com.films4you.req4;

import com.films4you.main.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to act as a repository layer for the CustomerPayment class.

 * @author Samuel Ivuerah
 */
public class CustomerPaymentRepository {

  /**
   * Gets customers and their payments, totalling their payments, and reflecting this in each
   * customer.

   * @return List of CustomerPayments.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  public List<CustomerPayment> findAll() throws SQLException, IllegalStateException {
    Database db = new Database();
    Map<Integer, CustomerPayment> customerPayments = new HashMap<>();

    // Retrieve customers.
    ResultSet rs = db.query("SELECT * FROM customer");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    // Instantiation.
    while (rs.next()) {
      customerPayments.put(rs.getInt(1), new CustomerPayment(rs.getInt(1), rs.getInt(6)));
    }

    // Retrieve payments
    rs = db.query("SELECT * FROM payment");
    if (rs == null) {
      throw new IllegalStateException("Query returned null");
    }

    // Total payments for its respective customer.
    while (rs.next()) {
      int customerId = rs.getInt(2);
      double amount = rs.getDouble(5);
      
      CustomerPayment customerPayment = customerPayments.get(customerId);
      if (customerPayment != null) {
        customerPayment.addAmount(amount);
      }
    }

    db.close(); // Close the database connection.
    return new ArrayList<CustomerPayment>(customerPayments.values());
  }
}
