package com.films4you.req2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CustomerTest {

  /**
   * Test for checking customerId validation during construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCustomerConstructorWithInvalidCustomerId() {
    new Customer(-1, "JOHN", "DOE");
  }

  /**
   * Test for checking name, (firstName, lastName), validation
   * during construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCustomerConstructorWithInvalidName() {
    new Customer(123, " ", " ");
    new Customer(123, "", "");
  }

  /**
   * Test for Customer constructor.
   */
  @Test
  public void testCustomerConstruction() {
    Customer customer = new Customer(123, "JOHN", "DOE");

    assertEquals(customer.getCustomerId(), 123);
    assertEquals(customer.getfirstName(), "JOHN");
    assertEquals(customer.getlastName(), "DOE");
  }

  /**
   * Test for checking amount validation in
   * IncreaseAmountPaid() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIncreaseAmountPaidWithInvalidAmount() {
    Customer customer = new Customer(123, "JOHN", "DOE");
    customer.increaseAmountPaid(-1);
  }

  /**
   * Test the increaseAmountPaid() method.
   */
  @Test
  public void testIncreaseAmountPaid() {
    Customer customer = new Customer(123, "JOHN", "DOE");

    customer.increaseAmountPaid(12.99);

    assertEquals(customer.getAmountPaid(), 12.99, 0.00);
  }
  
  /**
   * Test multiple payments for the increaseAmountPaid() method.
   */
  @Test
  public void testIncreaseAmountPaidMultipleTimes() {
    Customer customer = new Customer(123, "JOHN", "DOE");

    customer.increaseAmountPaid(12.99);
    
    assertEquals(customer.getAmountPaid(), 12.99, 0.00);
    
    customer.increaseAmountPaid(13.99);
    
    assertEquals(customer.getAmountPaid(), 26.98, 0.00);
    
    customer.increaseAmountPaid(14.99);

    assertEquals(customer.getAmountPaid(), 41.97, 0.00);
  }

  /**
   * Test Customer's toStirng() method.
   */
  @Test
  public void testToString() {
    Customer customer = new Customer(123, "JOHN", "DOE");
    customer.increaseAmountPaid(15.99);

    assertEquals(customer.toString(), "JOHN:DOE:123:15.99");
  }
}
