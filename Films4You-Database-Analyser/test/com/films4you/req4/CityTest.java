package com.films4you.req4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityTest {

  /**
   * Test for checking cityId validation during construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCityConstructorWithInvalidCityId() {
    new City(-1, "John City");
  }
  
  /**
   * Test for checking city, (name), validation during
   * construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCityConstructorWithInvalidName() {
    new City(123, "");
    new City(123, " ");
  }
  
  /**
   * Test for Customer constructor.
   */
  @Test
  public void testCityConstruction() {
    City city = new City(123, "John City");
    
    assertEquals(city.getCityId(), 123);
    assertEquals(city.getCity(), "John City");
  }
  
  /**
   * Test for checking amount validation in
   * increaseCityRevenue() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIncreaseCityRevenueWithInvalidAmount() {
    City city = new City(123, "John City");
    city.increaseCityRevenue(-1);
  }
  
  /**
   * Test the increaseCityRevenue() method.
   */
  @Test
  public void testIncreaseCityRevenue() {
    City city = new City(123, "John City");
    
    city.increaseCityRevenue(12.99);
    
    assertEquals(city.getCityRevenue(), 12.99, 0.00);
  }
  
  /**
   * Test multiple revenue updates for the increaseCityRevenue() method.
   */
  @Test
  public void testIncreaseCityRevenueMultipleTimes() {
    City city = new City(123, "John City");
    
    city.increaseCityRevenue(12.99);
    
    assertEquals(city.getCityRevenue(), 12.99, 0.00);
    
    city.increaseCityRevenue(13.99);
    
    assertEquals(city.getCityRevenue(), 26.98, 0.00);
    
    city.increaseCityRevenue(14.99);
    
    assertEquals(city.getCityRevenue(), 41.97, 0.00);
  }
  
  /**
   * Test City's toString() method.
   */
  @Test
  public void testToString() {
    City city = new City(123, "John City");
    city.increaseCityRevenue(15.99);
    
    assertEquals(city.toString(), "123:John City:15.99");
  }
}
