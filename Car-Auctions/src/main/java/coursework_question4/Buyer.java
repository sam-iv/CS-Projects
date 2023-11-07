package coursework_question4;

public class Buyer extends User {
  private int age;

  public int getAge() {
    return age;
  }

  public Buyer(String fullname, int age) {
    super(fullname);

    if (age < 18) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }

  @Override
  public String toString() {
    char[] charName = getName().toCharArray();
    return charName[0] + "***" + charName[charName.length - 1];
  }

  @Override
  public String getName() {
    return getFullname().substring(0, getFullname().indexOf(' '));
  }
}
