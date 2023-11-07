package com.films4you.req3;

import org.checkerframework.checker.nullness.qual.NonNull;
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
  
  /** The email of the customer. */
  private @NonNull String email;
  
  /** The counter of how many times the customer has rented. */
  private int rentCount = 0; 
  
  /**
   * Constructor allowing the ID, first name, last name and email to
   * be specified.

   * @param customerId The ID number of the customer, must be >= 0.
   * @param firstName The first name of the customer, cannot be: null,
   *     empty, or only contain whitespace.
   * @param lastName The last name of the customer, cannot be: null,
   *     empty, or only contain whitespace.
   * @param email The email of the customer, must follow format:
   *     "foo@bar.com".
   * @throws IllegalArgumentException if the customerId, firstName,
   *     lastName, email is invalid.
   */
  public Customer(int customerId, String firstName, String lastName, String email) 
      throws IllegalArgumentException {
    if (customerId < 0) {
      throw new IllegalArgumentException("customerId must be greater than or equal to 0");
    }
    if (firstName.isBlank() || lastName.isBlank()) {
      throw new IllegalArgumentException("A Customer must have a first name and last name");
    }
    if (!email.matches("[a-zA-Z0-9_.-]*@[a-zA-Z0-9_.-]*")) {
      throw new IllegalArgumentException("An email must follow the format foo@bar.com");
    }
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  
  public int getCustomerId() {
    return customerId;
  }

  public String getfirstName() {
    return firstName;
  }

  public String getlastName() {
    return lastName;
  }
  
  public String getEmail() {
    return email;
  }

  public int getRentCount() {
    return rentCount;
  }

  public void incrementRentCount() {
    rentCount++;
  }
  
  @Override
  public String toString() {
    return String.format("%s:%s:%d:%s:%d", firstName, lastName, customerId, 
        email, rentCount);
  }
}
