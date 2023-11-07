package com.films4you.req3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {

  /**
   * Test for checking customerId validation during construction.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCustomerConstructorWithInvalidCustomerId() {
    new Customer(-1, "JOHN", "DOE", "johndoe@gmail.com");
  }
  
  /**
   * Test for checking name, (firstName, lastName), validation
   * during construction.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCustomerConstructorWithInvalidName() {
    new Customer(123, " ", " ", "johndoe@gmail.com");
    new Customer(123, "", "", "johndoe@gmail.com");
  }
  
  /**
   * Test for checking email validation during construction.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCustomerConstructorWithInvalidEmail() {
    new Customer(123, "John", "Doe", "");
    new Customer(123, "John", "Doe", "@");
    new Customer(123, "John", "Doe", "johndoeatgmail.com");
  }
  
  /**
   * Test for Customer constructor.
   */
  @Test
  public void testCustomerConstruction() {
    Customer customer = new Customer(123, "JOHN", "DOE", "johndoe@gmail.com");
    
    assertEquals(customer.getCustomerId(), 123);
    assertEquals(customer.getfirstName(), "JOHN");
    assertEquals(customer.getlastName(), "DOE");
    assertEquals(customer.getEmail(), "johndoe@gmail.com");
  }
  
  /**
   * Test the incrementRentCount() method.
   */
  @Test
  public void testIncrementRentCount() {
    Customer customer = new Customer(123, "JOHN", "DOE", "johndoe@gmail.com");
    customer.incrementRentCount();
    
    assertEquals(customer.getRentCount(), 1);
    
    customer.incrementRentCount();
    
    assertEquals(customer.getRentCount(), 2);
    
    customer.incrementRentCount();
    
    assertEquals(customer.getRentCount(), 3);
  }
  
  /*
   * Test Customer's toStirng() method.
   */
  @Test
  public void testToString() {
    Customer customer = new Customer(123, "JOHN", "DOE", "johndoe@gmail.com");
    
    customer.incrementRentCount();
    customer.incrementRentCount();
    customer.incrementRentCount();
    
    assertEquals(customer.toString(), "JOHN:DOE:123:johndoe@gmail.com:3");
  }
}
