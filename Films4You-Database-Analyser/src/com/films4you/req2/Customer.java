package com.films4you.req2;

import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;

/**
 * A class to model a customer entity.

 * @author Samuel Ivuerah
 */
public class Customer {

  /** The ID number of the customer, must be greater or equal to 0. */
  private @IntRange(from = 0) int customerId;
  
  /** The first name of the customer. */
  private @NonNull String firstName;
  
  /** The last name of the customer. */
  private @NonNull String lastName;
  
  /** The total amount paid by the customer. */
  private double amountPaid = 0.00D; 
  
  /**
   * Constructor allowing the ID, first name, and last name
   * to be specified.

   * @param customerId The ID number of the customer, must be >= 0.
   * @param firstName The first name of the customer, cannot be: null,
   *     empty, or only contain whitespace.
   * @param lastName The last name of the customer, cannot be: null,
   *     empty, or only contain whitespace.
   * @throws IllegalArgumentException If the customerId, firstName,
   *     lastName is invalid.
   */
  public Customer(int customerId, String firstName, String lastName) 
      throws IllegalArgumentException {
    if (customerId < 0) {
      throw new IllegalArgumentException("customerId must be greater than or equal to 0");
    }
    if (firstName.isBlank() || lastName.isBlank()) {
      throw new IllegalArgumentException("A Customer must have a first name and last name");
    }
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public int getCustomerId() {
    return customerId;
  }

  public @Nullable String getfirstName() {
    return firstName;
  }

  public @Nullable String getlastName() {
    return lastName;
  }

  public double getAmountPaid() {
    return amountPaid;
  }

  /**
   * Increases the amount paid by a customer by a known amount.

   * @param amount The amount the total amount paid by the
   *     customer should be increased by, must be >= 0.00D.
   * @throws IllegalArgumentException If the amount is invalid.
   */
  public void increaseAmountPaid(double amount) throws IllegalArgumentException {
    if (amount < 0.00D) {
      throw new IllegalArgumentException("Transactions cannot be negative");
    } else {
      amountPaid += amount;
    }
  }
  
  @Override
  public String toString() {
    return String.format("%s:%s:%d:%.2f", Objects.toString(firstName, "Error"), 
        Objects.toString(lastName, "Error"), customerId, amountPaid);
  }
}
