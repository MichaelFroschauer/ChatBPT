package at.fhooe;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Member member = (Member) o;
    return Objects.equals(shortName, member.shortName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shortName);
  }
}
