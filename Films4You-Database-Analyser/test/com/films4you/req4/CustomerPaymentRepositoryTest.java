package com.films4you.req4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;

public class CustomerPaymentRepositoryTest {

  /**
   * Test the findAll() method returns the expected values.
   */
  @Test
  public void testFindAll() throws SQLException {
    CustomerPaymentRepository customerPaymentRepository = new CustomerPaymentRepository();

    List<CustomerPayment> customerPayments = customerPaymentRepository.findAll();

    assertNotNull(customerPayments);
    assertEquals(599, customerPayments.size());
    assertEquals(221.55, customerPayments.get(525).getAmount(), 0.001);
    assertEquals(174.66, customerPayments.get(180).getAmount(), 0.001);
  }
}
