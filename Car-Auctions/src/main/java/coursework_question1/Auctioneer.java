package coursework_question1;

import java.util.HashMap;
import java.util.Map;

/**
 * Auctioneer class.
 *
 * @author samiv
 *
 */
public class Auctioneer {

  /** The name of the auctioneer. */
  protected String name;

  /**
   * Mapping to store cars for sale. CarsForSale is a {@link Map} collection that contains
   * {@link Advert} as a key and {@link User} as a value.
   */
  protected Map<Advert, User> carsForSale;

  /**
   * Mapping to store cars sold. SoldCars is a {@link Map} collection that contains {@link Advert}
   * as a key and {@link User} as a value.
   */
  protected Map<Advert, User> soldCars;
  
  /**
   * Mapping to store unsold cars. UnsoldCars is a {@link Map} collection that contains
   * {@link Advert} as a key and {@link User} as a value.
   */
  protected Map<Advert, User> unsoldCars;

  /**
   * Constructor allowing the name of the auctioneer to be specified.
   *
   * @param name The name of the auctioneer.
   */
  public Auctioneer(String name) {
    this.name = name;

    carsForSale = new HashMap<>();
    soldCars = new HashMap<>();
    unsoldCars = new HashMap<>();
  }

  /**
   * Method to check if a car exists that is for sale.
   *
   * @param car The car to be investigated.
   * @return True if the car exists, otherwise false.
   */
  private boolean checkExistence(Car car) {
    for (Map.Entry<Advert, User> entry : carsForSale.entrySet()) {
      if (entry.getKey().getCar() == car) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method makes use of a StringBuffer object to display all sold cars,their new owners, and the
   * successful bid price in a pretty format.
   *
   * @return The sold cars' ID, name of the buyer and the bid in a pretty format.
   */
  public String displaySoldCars() {
    StringBuffer sb = new StringBuffer();
    sb.append("SOLD CARS:\n");
    for (Map.Entry<Advert, User> entry : soldCars.entrySet()) {
      sb.append(entry.getKey().getCar().getId() + " - Purchased by " + name + " with a successful £"
          + String.format("%.2f", entry.getKey().getHighestOffer().getValue()) + " bid.\n");
    }
    return sb.toString();
  }

  /**
   * Method to return {@value}@"Statistics".
   *
   * @return The value {@value}@"Statistics".
   */
  public String displayStatistics() {
    return "Statistics";
  }

  /**
   * Method makes use of a StringBuffer object to display all unsold cars' specifications in a
   * pretty format.
   *
   * @return The unsold cars' specifications in a pretty format.
   */
  public String displayUnsoldCars() {
    StringBuffer sb = new StringBuffer();
    sb.append("UNSOLD CARS:\n");
    for (Map.Entry<Advert, User> entry : unsoldCars.entrySet()) {
      sb.append(entry.getKey().toString());
    }
    return sb.toString();
  }

  /**
   * Method to end the sale of an advertisements.
   *
   * @param advert The advertisement to be ended.
   */
  public void endSale(Advert advert) {
    if (advert == null) {
      throw new IllegalArgumentException();
    }
    
    if (carsForSale.containsKey(advert)) {
      if (advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
        soldCars.put(advert, carsForSale.get(advert));
        carsForSale.remove(advert);
      } else {
        unsoldCars.put(advert, carsForSale.get(advert));
        carsForSale.remove(advert);
      }
    }
  }

  /**
   * Method to place an offer on an advertisement.
   *
   * @param carAdvert The advertisement to place an offer for.
   * @param user The buyer who is placing the offer.
   * @param value The amount of money offered.
   * @return True if the offer has been placed successfully, otherwise false.
   */
  public boolean placeOffer(Advert carAdvert, User user, double value) {
    if (carAdvert == null || user == null) {
      throw new IllegalArgumentException();
    }
    if (!checkExistence(carAdvert.getCar())) {
      return false;
    } else {
      carAdvert.placeOffer(user, value);
      return true;
    }
  }

  /**
   * Method to register a car for the auctioneer.
   *
   * @param carAdvert The advertisement for the car.
   * @param user The seller who is registering the car.
   * @param colour The colour of the car.
   * @param type The transmission of the car.
   * @param body The body type of the car.
   * @param noOfSeats The number of seats in the car.
   */
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body,
      int noOfSeats) {
    if (carAdvert == null || user == null) {
      throw new IllegalArgumentException();
    }
    carAdvert.getCar().setGearbox(type);
    carAdvert.getCar().setColour(colour);
    carAdvert.getCar().setBody(body);
    carAdvert.getCar().setNumberOfSeats(noOfSeats);

    if (!checkExistence(carAdvert.getCar())) {
      carsForSale.put(carAdvert, user);
    }
  }
}
