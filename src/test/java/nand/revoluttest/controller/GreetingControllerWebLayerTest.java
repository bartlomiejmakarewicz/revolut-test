package nand.revoluttest.controller;

import nand.revoluttest.service.GreetingService;
import nand.revoluttest.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerWebLayerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GreetingService greetingService;

  @MockBean
  private UserService userService;

  private static final String USER_NAME = "John";
  private static final String EXPECTED_RESPONSE_VALUE = String.format("Hello, %s!", USER_NAME);

  @Test
  public  void givenController_whenCorrectPathRequested_shouldRespondWithOk() throws Exception {
    given(greetingService.getGreetingForName(anyString())).willReturn(EXPECTED_RESPONSE_VALUE);
    mockMvc.perform(get(String.format("/hello/%s", USER_NAME)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message", Matchers.is(EXPECTED_RESPONSE_VALUE)));
  }
}
