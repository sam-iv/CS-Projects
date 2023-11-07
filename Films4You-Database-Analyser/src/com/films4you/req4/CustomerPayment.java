package com.films4you.req4;

import org.checkerframework.common.value.qual.IntRange;

/**
 * A class to model both a customer and payment entity.

 * @author Samuel Ivuerah
 */
public class CustomerPayment {
  
  /** The ID number of the customer, must be greater or equal to 0. */
  private @IntRange(from = 0) int customerId;
  
  /** The ID number of the customer's address, must be greater or equal to 0. */
  private @IntRange(from = 0) int addressId;
  
  /** The total amount, (revenue), of all payments for this customer. */
  private double amount = 0.00D;
  
  /**
   * Constructor allowing the IDs to be specified.

   * @param customerId The ID number of the customer, must be >= 0.
   * @param addressId The ID number of the customer's address, must be >= 0.
   * @throws IllegalArgumentException If the IDs are invalid.
   */
  public CustomerPayment(int customerId, int addressId) throws IllegalArgumentException {
    if (customerId < 0 || addressId < 0) {
      throw new IllegalArgumentException("IDs must be greater than or equal to 0");
    }
    this.customerId = customerId;
    this.addressId = addressId;
  }
  
  public int getCustomerId() {
    return customerId;
  }
  
  public int getAddressId() {
    return addressId;
  }
  
  public double getAmount() {
    return amount;
  }
  
  /**
   * Increase the amount of revenue this customer has given by a known amount.

   * @param amount The amount the revenue made should be increased by, must be
   *     >= 0.00D.
   * @throws IllegalArgumentException If the amount is invalid.
   */
  public void addAmount(double amount) throws IllegalArgumentException {
    if (amount < 0.00D) {
      throw new IllegalArgumentException("Transactions cannot be negative");
    } else {
      this.amount += amount;
    }
  }

  @Override
  public String toString() {
    return String.format("%d:%d:%.2f", customerId, addressId, amount);
  }
}
