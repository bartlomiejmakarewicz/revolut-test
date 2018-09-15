package nand.revoluttest.service;

import nand.revoluttest.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class GreetingServiceImplTest {
  @Mock
  private UserService userService;
  private GreetingService greetingService;

  private static final String INPUT_NAME = "John";
  private static final String DEFAULT_RESPONSE_VALUE = String.format("Hello, %s!", INPUT_NAME);
  private static final String BIRTHDAY_IN_5_DAYS_RESPONSE_VALUE = String.format("Hello, %s! Your birthday is in 5 days", INPUT_NAME);
  private static final String BIRTHDAY_TOMORROW_RESPONSE_VALUE = String.format("Hello, %s! Your birthday is tomorrow", INPUT_NAME);
  private static final String BIRTHDAY_TODAY_RESPONSE_VALUE = String.format("Hello, %s! Happy birthday!", INPUT_NAME);

  @Before
  public void setUp() {
    greetingService = new GreetingServiceImpl(userService);
  }

  @Test
  public void whenUserNotPersisted_greetingRequested_defaultGreetingReturned() {
    given(userService.getUserWithName(anyString())).willReturn(null);

    String greetingForName = greetingService.getGreetingForName(INPUT_NAME);

    then(userService).should(times(1)).getUserWithName(INPUT_NAME);
    Assert.assertEquals(DEFAULT_RESPONSE_VALUE, greetingForName);
  }

  @Test
  public void whenUserPersistedWithBirthdayIn5Days_greetingRequested_birthdayIn5DaysGreetingReturned() {
    User user = new User(INPUT_NAME, LocalDate.now().plusDays(5));
    given(userService.getUserWithName(anyString())).willReturn(user);

    String greetingForName = greetingService.getGreetingForName(INPUT_NAME);

    then(userService).should(times(1)).getUserWithName(INPUT_NAME);
    Assert.assertEquals(BIRTHDAY_IN_5_DAYS_RESPONSE_VALUE, greetingForName);
  }

  @Test
  public void whenUserPersistedWithBirthdayTomorrow_greetingRequested_birthdayTomorrowGreetingReturned() {
    User user = new User(INPUT_NAME, LocalDate.now().plusDays(1));
    given(userService.getUserWithName(anyString())).willReturn(user);

    String greetingForName = greetingService.getGreetingForName(INPUT_NAME);

    then(userService).should(times(1)).getUserWithName(INPUT_NAME);
    Assert.assertEquals(BIRTHDAY_TOMORROW_RESPONSE_VALUE, greetingForName);
  }

  @Test
  public void whenUserPersistedWithBirthdayToday_greetingRequested_birthdayTodayGreetingReturned() {
    User user = new User(INPUT_NAME, LocalDate.now());
    given(userService.getUserWithName(anyString())).willReturn(user);

    String greetingForName = greetingService.getGreetingForName(INPUT_NAME);

    then(userService).should(times(1)).getUserWithName(INPUT_NAME);
    Assert.assertEquals(BIRTHDAY_TODAY_RESPONSE_VALUE, greetingForName);
  }

  @Test
  public void whenUserPersistedWithBirthdayYesterday_greetingRequested_defaultGreetingReturned() {
    User user = new User(INPUT_NAME, LocalDate.now().minusDays(1));
    given(userService.getUserWithName(anyString())).willReturn(user);

    String greetingForName = greetingService.getGreetingForName(INPUT_NAME);

    then(userService).should(times(1)).getUserWithName(INPUT_NAME);
    Assert.assertEquals(DEFAULT_RESPONSE_VALUE, greetingForName);
  }

  @Test
  public void whenUserPersistedWithBirthdayInOneYear_greetingRequested_defaultGreetingReturned() {
    User user = new User(INPUT_NAME, LocalDate.now().plusYears(1));
    given(userService.getUserWithName(anyString())).willReturn(user);

    String greetingForName = greetingService.getGreetingForName(INPUT_NAME);

    then(userService).should(times(1)).getUserWithName(INPUT_NAME);
    Assert.assertEquals(DEFAULT_RESPONSE_VALUE, greetingForName);
  }
}