package nand.revoluttest.controller;

import nand.revoluttest.domain.User;
import nand.revoluttest.domain.UserDto;
import nand.revoluttest.service.GreetingService;
import nand.revoluttest.service.UserService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class GreetingControllerTest {
  @Mock
  private UserService userService;
  @Mock
  private GreetingService greetingService;
  private GreetingController greetingController;

  private static final String INPUT_NAME = "John";
  private static final String EXPECTED_RESPONSE_VALUE = String.format("Hello, %s!", INPUT_NAME);
  private static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 1, 1);

  @Before
  public void setUp() {
    greetingController = new GreetingController(userService, greetingService);
  }

  @Test
  public void givenController_whenGreetingRequested_shouldCallServiceMethodAndReturnResponse() {
    given(greetingService.getGreetingForName(anyString())).willReturn(EXPECTED_RESPONSE_VALUE);

    ResponseEntity responseEntity = greetingController.greetingGet(INPUT_NAME);

    then(greetingService).should(times(1)).getGreetingForName(INPUT_NAME);
    JSONObject jsonObjectFromResponse = new JSONObject(responseEntity.getBody().toString());
    assertEquals(EXPECTED_RESPONSE_VALUE, jsonObjectFromResponse.get("message"));
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void givenController_whenUserUpdated_shouldCallServiceMethodAndReturnNoContent() {
    UserDto userDto = new UserDto();
    userDto.setDateOfBirth(DATE_OF_BIRTH);

    ResponseEntity responseEntity = greetingController.greetingPut(INPUT_NAME, userDto);

    then(userService).should(times(1)).saveOrUpdateUser(any(User.class));
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    assertNull(responseEntity.getBody());
  }
}
