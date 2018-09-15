package nand.revoluttest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {
  @Id
  private String name;
  private LocalDate dateOfBirth;

  public User() {
  }

  public User(String name, LocalDate dateOfBirth) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}

