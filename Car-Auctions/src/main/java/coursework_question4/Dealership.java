package coursework_question4;

import java.util.HashMap;
import java.util.Map;

public abstract class Dealership {
  protected String name;
  protected Map<Advert, Seller> carsForSale;
  protected Map<Advert, Seller> unsoldCars;
  protected Map<Advert, Buyer> soldCars;

  public Dealership(String name) {
    this.name = name;

    carsForSale = new HashMap<>();
    soldCars = new HashMap<>();
    unsoldCars = new HashMap<>();
  }

  public abstract String displaySoldCars();

  public abstract String displayStatistics();

  public abstract String displayUnsoldCars();

  public abstract boolean placeOffer(Advert carAdvert, User user, double value);

  public abstract void registerCar(Advert advert, User user, String colour, CarType type,
      CarBody body, int numberOfSeats);
}
