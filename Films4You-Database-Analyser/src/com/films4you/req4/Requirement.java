package com.films4you.req4;

import com.films4you.main.RequirementInterface;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A requirement to list the top ten cities by revenue.

 * @author Samuel Ivuerah
 */
public class Requirement implements RequirementInterface {
  
  /** A repository for city processing. */
  private final CityRepository cityRepo = new CityRepository();
  
  /** A repository for customer and payment processing. */
  private final CustomerPaymentRepository customerPaymentRepo = 
      new CustomerPaymentRepository();
  
  /** A repository for address processing. */
  private final AddressRepository addressRepo = new AddressRepository();
  
  /**
   * Gets cities, and instantiates them.

   * @return List of cities.
   * @throws SQLException on database error.
   * @throws IllegalStateException on error, e.g. value null when not allowed.
   */
  private List<City> getCities() throws SQLException, IllegalStateException {
    List<City> cities = cityRepo.findAll();
    List<CustomerPayment> customerPayments = customerPaymentRepo.findAll();
    Map<Integer, Integer> addresses = addressRepo.getAllAddressCityIds();

    // For each customer payment, update the revenue the city has generated.
    for (CustomerPayment customerPayment : customerPayments) {
      Integer cityId = addresses.get(customerPayment.getAddressId());
      if (cityId != null) {
        cityRepo.increaseCityRevenue(cityId, customerPayment.getAmount());
      }
    }

    return cities;
  }
  
  /**
   * Gets the top ten cities by revenue.

   * @return Array of cities, ordered by revenue.
   * @throws SQLException on database error.
   */
  private City[] getTopTenCities() throws SQLException {
    return getCities().stream()
        .sorted(Comparator.comparingDouble(City::getCityRevenue).reversed())
        .limit(10)
        .toArray(City[]::new);
  }
  
  /**
   * A method to return the top ten cities by revenue, by using
   * {@link com.films4you.req4.City#toString()}, to format.

   * @return A string containing the top ten cities.
   *     In the format: "{@linkplain cityId}:{@linkplain city}:{@linkplain cityRevenue}".
   */
  @Override
  public @Nullable String getValueAsString() {
    try {
      City[] topTenCities = getTopTenCities();
      
      if (topTenCities == null) {
        return null;
      }
      
      StringBuilder output = new StringBuilder();
      String delimiter = ",";
      for (City city : topTenCities) {
        output.append(city.toString()).append(delimiter);
      }
      
      // Removing the last delimiter
      output.setLength(output.length() - delimiter.length());

      return output.toString();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * A method to acquire and parse the non-human-readable string
   * from {@link com.films4you.req4.Requirement#getValueAsString()}
   * and return it in human-readable format.

   * @return A string formatted for end user containing a display
   *     of all cities, with each cities': rank, name, id, and revenue
   *     generated. In the format:
   *     "[RANK]. [city] ([cityId]) - £[cityRevenue], etc.".
   */
  @Override
  public @NonNull String getHumanReadable() {
    
    String value = getValueAsString();
    if (value == null) {
      return "Sorry, no results found or error occurred.";
    }

    String[] valueParts = value.split("\\,");
    StringBuilder result = new StringBuilder();

    int pos = 1;
    for (String valuePart : valueParts) {
      String[] intermediateParts = valuePart.split(":");

      if (intermediateParts.length >= 3) {
        result.append(String.format("%s. %s (%s) - £%s\n", pos, intermediateParts[1], 
            intermediateParts[0], intermediateParts[2]));
        pos++;

        if (pos > 10) {
          break;
        }
      }
    }

    if (result.length() > 0) {
      return result.substring(0, result.length() - 1);
    } else {
      return "Sorry, no results found or error occurred.";
    }
  }
}
