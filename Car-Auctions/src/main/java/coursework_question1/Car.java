package coursework_question1;

public class Car {
  private int id;
  private int numberOfSeats;
  private String name;
  private String colour;
  private double reservedPrice;
  private CarType gearbox;
  private CarBody body;
  private Condition condition;

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  public CarType getGearbox() {
    return gearbox;
  }

  public void setGearbox(CarType gearbox) {
    this.gearbox = gearbox;
  }

  public CarBody getBodyStyle() {
    return body;
  }

  public void setBody(CarBody body) {
    this.body = body;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return reservedPrice;
  }

  public Car(int id, String name, double reservedPrice, Condition condition) {
    if (id <= 0 || name == null || reservedPrice <= 0) {
      throw new IllegalArgumentException();
    }
    this.id = id;
    this.name = name;
    this.reservedPrice = reservedPrice;
    this.condition = condition;
  }

  @Override
  public String toString() {
    return id + " - " + name;
  }

  public String displayCarSpecification() {
    StringBuffer display = new StringBuffer();
    display.append(toString() + " (£" + String.format("%.2f", reservedPrice) + ")" + "\n");
    display.append("\t" + "Type: " + gearbox.toString() + "\n");
    display.append("\t" + "Style: " + body.toString() + "\n");
    display.append("\t" + "Colour: " + colour + "\n");
    display.append("\t" + "No. of Seats: " + numberOfSeats + "\n");
    display.append("\t" + "Condition: " + condition.toString());

    return display.toString();
  }
}
