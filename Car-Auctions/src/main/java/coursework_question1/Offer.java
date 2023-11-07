package coursework_question1;

public class Offer {
  private double value;
  private User buyer;

  public User getBuyer() {
    return buyer;
  }

  public double getValue() {
    return this.value;
  }

  public Offer(User buyer, double value) {
    if (buyer == null || value <= 0) {
      throw new IllegalArgumentException();
    }
    this.buyer = buyer;
    this.value = value;
  }

  @Override
  public String toString() {
    return buyer.toString() + " offered £" + String.format("%.2f", value);
  }
}
