package coursework_question3;

import java.util.HashMap;
import java.util.Map;

public class Auctioneer extends Dealership {
  private Map<Seller, Integer> sales;
  private Seller topSeller;

  public Auctioneer(String name) {
    super(name);
    sales = new HashMap<>();
  }

  private boolean checkExistence(Car car) {
    for (Map.Entry<Advert, Seller> entry : carsForSale.entrySet()) {
      if (entry.getKey().getCar() == car) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String displaySoldCars() {
    StringBuffer sb = new StringBuffer();
    sb.append("SOLD CARS:\n");
    for (Map.Entry<Advert, Buyer> entry : soldCars.entrySet()) {
      sb.append(entry.getKey().getCar().getID() + " - Purchased by " + entry.getValue().toString()
          + " with a successful £"
          + String.format("%.2f", entry.getKey().getHighestOffer().getValue()) + " bid.\n");
    }
    return sb.toString();
  }

  @Override
  public String displayStatistics() {
    return "Statistics";
  }

  @Override
  public String displayUnsoldCars() {
    StringBuffer sb = new StringBuffer();
    sb.append("UNSOLD CARS:\n");
    for (Map.Entry<Advert, Seller> entry : unsoldCars.entrySet()) {
      sb.append(entry.getKey().toString());
    }
    return sb.toString();
  }

  public void endSale(Advert advert) {
    if (advert == null) {
      throw new IllegalArgumentException();
    }

    if (carsForSale.containsKey(advert)) {
      if (advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
        soldCars.put(advert, advert.getHighestOffer().getBuyer());
        carsForSale.remove(advert);
      } else {
        unsoldCars.put(advert, carsForSale.get(advert));
        carsForSale.remove(advert);
      }
    }
  }

  @Override
  public boolean placeOffer(Advert carAdvert, User user, double value) {
    if (carAdvert == null || user == null) {
      throw new IllegalArgumentException();
    }
    if (!(carAdvert.getCar().getType() == SaleType.AUCTION)) {
      return false;
    }
    if (!checkExistence(carAdvert.getCar())) {
      return false;
    }
    carAdvert.placeOffer((Buyer) user, value);
    return true;
  }

  @Override
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body,
      int numberOfSeats) {
    if (carAdvert == null || user == null) {
      throw new IllegalArgumentException();
    }
    carAdvert.getCar().setGearbox(type);
    carAdvert.getCar().setColour(colour);
    carAdvert.getCar().setBody(body);
    carAdvert.getCar().setNumberOfSeats(numberOfSeats);

    if (!checkExistence(carAdvert.getCar())) {
      carsForSale.put(carAdvert, (Seller) user);
    }
  }
}
