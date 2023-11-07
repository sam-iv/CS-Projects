package coursework_question1;

public class User {
  private String fullname;

  public String getName() {
    return fullname.substring(0, fullname.indexOf(' '));
  }

  public User(String fullname) {
    if (!fullname.matches("[A-Z]{1}[a-z]* [A-Z]{1}[a-z]*")) {
      throw new IllegalArgumentException();
    }
    this.fullname = fullname;
  }

  @Override
  public String toString() {
    return fullname;
  }
}
