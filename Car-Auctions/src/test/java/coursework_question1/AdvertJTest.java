package coursework_question1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AdvertJTest {
  Advert advert = null;
  Car car = new Car(6969, "Mercedes-Benz EQC", 72335, Condition.NEW);
  User buyer1 = new User("Samuel Ivuerah");
  User buyer2 = new User("Molly Carton");

  @Test
  public void coursework_testAdvertConstruction() {
    advert = new Advert(car);

    assertEquals(car, advert.getCar());
  }

  @Test(expected = IllegalArgumentException.class)
  public void coursework_testInvalidAdvert() {
    advert = new Advert(null);
  }

  @Test
  public void coursework_testHighestOffer() {
    advert = new Advert(car);
    advert.placeOffer(buyer1, 3000);
    advert.placeOffer(buyer2, 4000);

    assertEquals(buyer2.getName(), advert.getHighestOffer().getBuyer().getName());
  }

  @Test
  public void coursework_testPlaceOffer() {
    advert = new Advert(car);

    assertTrue(advert.placeOffer(buyer2, 3000));
  }

  @Test
  public void coursework_testInvalidPlaceOffer() {
    advert = new Advert(car);

    assertFalse(advert.placeOffer(null, -3000));
    assertEquals(0, advert.getOffers().size());
  }

  @Test
  public void coursework_testToString() {
    advert = new Advert(car);
    car.setBody(CarBody.HATCHBACK);
    car.setColour("Black");
    car.setGearbox(CarType.AUTOMATIC);
    car.setNumberOfSeats(5);

    assertEquals("Ad: " + car.displayCarSpecification() + "\n", advert.toString());
  }
}
