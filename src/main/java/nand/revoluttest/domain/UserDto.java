package nand.revoluttest.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserDto {
  @NotNull
  private LocalDate dateOfBirth;

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
