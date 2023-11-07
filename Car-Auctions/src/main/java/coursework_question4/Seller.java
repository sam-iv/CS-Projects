package coursework_question4;

public class Seller extends User {
  private int sales;

  public int getSales() {
    return sales;
  }

  public void setSales(int sales) {
    this.sales = sales;
  }

  public Seller(String fullname) {
    super(fullname);
  }

  @Override
  public String toString() {
    return getFullname().substring(0, getFullname().indexOf(' ') + 2) + ". (Sales: " + sales
        + ", Rating: " + identifyRating() + ")";
  }

  @Override
  public String getName() {
    return getFullname().substring(0, getFullname().indexOf(' '));
  }

  public String identifyRating() {
    if (sales > 10) {
      return "Level 3";
    }
    if (sales <= 10 && sales >= 6) {
      return "Level 2";
    }
    if (sales <= 5 && sales >= 1) {
      return "Level 1";
    } else {
      return "Level 0";
    }
  }
}
