package com.films4you.req4;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.common.value.qual.IntRange;

/**
 * A class to model a city entity.

 * @author Samuel Ivuerah
 */
public class City {
  
  /** The ID number of the city, must be greater or equal to 0. */
  private @IntRange(from = 0) int cityId;
  
  /** The name of the city. */
  private @NonNull String city;
  
  /** The total amount of revenue the city has generated. */
  private double cityRevenue = 0.00D;
  
  /**
   * Constructor allowing the ID, and name to be specified.

   * @param cityId The ID number of the city, must be >= 0.
   * @param city The name of the city, cannot be: null, empty, or
   *     only contain whitespace.
   * @throws IllegalArgumentException If the cityId or city is invalid.
   */
  public City(int cityId, String city) throws IllegalArgumentException {
    if (cityId < 0) {
      throw new IllegalArgumentException("cityId must be greater than or equal to 0");
    }
    if (city.isBlank()) {
      throw new IllegalArgumentException("A city must have a valid name");
    }
    this.cityId = cityId;
    this.city = city;
  }

  public int getCityId() {
    return cityId;
  }

  public String getCity() {
    return city;
  }
  
  public double getCityRevenue() {
    return cityRevenue;
  }
  
  /**
   * Increase the amount of revenue a city has made by a known amount.

   * @param amount The amount the total revenue made should be increased
   *     by, must be >= 0.00D.
   * @throws IllegalArgumentException If the amount is invalid.
   */
  public void increaseCityRevenue(double amount) {
    if (amount < 0.00D) {
      throw new IllegalArgumentException("Revenue cannot be negative");
    } else {
      cityRevenue += amount;
    }
  }

  @Override
  public String toString() {
    return String.format("%d:%s:%.2f", cityId, city, cityRevenue);
  }
}
