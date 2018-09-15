package nand.revoluttest.controller;

import nand.revoluttest.service.GreetingService;
import nand.revoluttest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

  @Test
  public  void givenController_whenCorrectPathRequested_shouldRespondWithOk() throws Exception {
    given(greetingService.getGreetingForName(anyString())).willReturn("Hello, John!");
    mockMvc.perform(get("/hello/John"))
        .andExpect(status().isOk());
  }
}
