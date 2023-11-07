package coursework_question1;

import java.util.ArrayList;
import java.util.List;

public class Advert {
  private Car car;
  private List<Offer> offers;

  // For J Unit tests
  public Car getCar() {
    return car;
  }
  
  //For J Unit tests
  public List<Offer> getOffers() {
    return offers;
  }

  public Advert(Car car) {
    if (car == null) {
      throw new IllegalArgumentException();
    }

    this.car = car;
    offers = new ArrayList<>();
  }

  public Offer getHighestOffer() {
    Offer highestOffer = offers.get(0);
    for (Offer offer : offers) {
      if (offer.getValue() > highestOffer.getValue()) {
        highestOffer = offer;
      }
    }

    return highestOffer;
  }

  public boolean placeOffer(User buyer, double value) {
    try {
      Offer newOffer = new Offer(buyer, value);
      offers.add(newOffer);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String toString() {
    return "Ad: " + car.displayCarSpecification() + "\n";
  }
}
