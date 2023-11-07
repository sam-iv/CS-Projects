package coursework_question4;

public abstract class User {
  private String fullname;

  public String getFullname() {
    return fullname;
  }

  public User(String fullname) {
    if (!fullname.matches("[A-Z]{1}[a-z]* [A-Z]{1}[a-z]*")) {
      throw new IllegalArgumentException();
    }
    this.fullname = fullname;
  }

  public abstract String toString();

  public abstract String getName();
}
