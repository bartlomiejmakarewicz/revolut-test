package nand.revoluttest.service;

import nand.revoluttest.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GreetingServiceImpl implements GreetingService {
  private final UserService userService;

  @Value("${app.remind.threshold:5}")
  private int reminderThreshold = 5;

  private static final String MESSAGE_FORMAT = "Hello, %s!%s";
  private static final String BIRTHDAY_MESSAGE = " Happy birthday!";
  private static final String BIRTHDAY_REMINDER_FORMAT = " Your birthday is in %s days";
  private static final String BIRTHDAY_TOMORROW_REMINDER = " Your birthday is tomorrow";

  public GreetingServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String getGreetingForName(String name) {
    String suffix = getMessageSuffixForUserName(name);
    return String.format(MESSAGE_FORMAT, name, suffix);
  }

  private String getBirthdayReminder(int daysRemaining) {
    if (daysRemaining == 1) {
      return BIRTHDAY_TOMORROW_REMINDER;
    }
    return String.format(BIRTHDAY_REMINDER_FORMAT, daysRemaining);
  }

  private String getMessageSuffixForUserName(String name) {
    String suffix = "";
    User user = userService.getUserWithName(name);
    if (user != null) {
      LocalDate currentDay = LocalDate.now();
      LocalDate dayOfBirth = user.getDateOfBirth();
      if (dayOfBirth != null && dayOfBirth.getYear() <= currentDay.getYear()) {
        LocalDate birthday = dayOfBirth.withYear(currentDay.getYear());
        LocalDate birthdayRemindDate = birthday.minusDays(reminderThreshold + 1);
        if (birthday.isEqual(currentDay)) {
          suffix = BIRTHDAY_MESSAGE;
        } else if (currentDay.isAfter(birthdayRemindDate) && currentDay.isBefore(birthday)) {
          int daysToBirthday = currentDay.until(birthday).getDays();
          suffix = getBirthdayReminder(daysToBirthday);
        }
      }
    }
    return suffix;
  }
}
