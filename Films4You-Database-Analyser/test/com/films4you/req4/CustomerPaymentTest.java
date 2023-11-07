package com.films4you.req4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerPaymentTest {

  /**
   * Test for checking customerId validation during construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCustomerPaymentConstructorWithInvalidCustomerId() {
    new CustomerPayment(-1, 123);
  }
  
  /**
   * Test for checking addressId validation during construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCustomerPaymentConstructorWithInvalidAddressId() {
    new CustomerPayment(123, -1);
  }
  
  /**
   * Test for CustomerPayment constructor.
   */
  @Test
  public void testCustomerPaymentConstruction() {
    CustomerPayment customerPayment = new CustomerPayment(123, 456);
    
    assertEquals(customerPayment.getCustomerId(), 123);
    assertEquals(customerPayment.getAddressId(), 456);
  }
  
  /**
   * Test for checking amount validation in addAmount() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddAmountWithInvalidAmount() {
    CustomerPayment customerPayment = new CustomerPayment(123, 456); 
    customerPayment.addAmount(-1);
  }
  
  /**
   * Test the addAmount() method.
   */
  @Test
  public void testAddAmount() {
    CustomerPayment customerPayment = new CustomerPayment(123, 456);
    
    customerPayment.addAmount(12.99);
    
    assertEquals(customerPayment.getAmount(), 12.99, 0.00);
  }
  
  /**
   * Test multiple amount increases for the addAmount() method.
   */
  @Test
  public void testAddAmountWithMultipleUpdates() {
    CustomerPayment customerPayment = new CustomerPayment(123, 456);
    
    customerPayment.addAmount(12.99);
    
    assertEquals(customerPayment.getAmount(), 12.99, 0.00);
    
    customerPayment.addAmount(13.99);
    
    assertEquals(customerPayment.getAmount(), 26.98, 0.00);
    
    customerPayment.addAmount(14.99);
    
    assertEquals(customerPayment.getAmount(), 41.97, 0.00); 
  }
  
  /**
   * Test CustomerPayment's toString() method.
   */
  @Test
  public void testToString() {
    CustomerPayment customerPayment = new CustomerPayment(123, 456);
    customerPayment.addAmount(15.99);
    
    assertEquals(customerPayment.toString(), "123:456:15.99");
  }
}
