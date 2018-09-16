package nand.revoluttest.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles({"it"})
public class GreetingControllerIntegrationTest {
  @Autowired
  private MockMvc mvc;

  private static final String USER_NAME = "John";
  private static final String EXPECTED_RESPONSE_VALUE = String.format("Hello, %s!", USER_NAME);

  @Test
  public void givenController_whenCorrectPathRequested_shouldRespondWithOk() throws Exception {
    mvc.perform(get(String.format("/hello/%s", USER_NAME)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message", Matchers.is(EXPECTED_RESPONSE_VALUE)));
  }
}