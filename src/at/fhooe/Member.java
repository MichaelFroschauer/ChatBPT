package at.fhooe;

public class Member {
  private String shortName;
  private String userName;
  private String passwordHash;

  public Member(String shortName, String userName, String passwordHash) {
    this.shortName = shortName;
    this.userName = userName;
    this.passwordHash = passwordHash;
  }

  public String getShortName() {
    return shortName;
  }

  public String getUserName() {
    return userName;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(shortName).append(" : ").append(userName);
    return sb.toString();
  }
}
