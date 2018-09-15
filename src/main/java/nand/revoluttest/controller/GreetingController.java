package nand.revoluttest.controller;

import nand.revoluttest.domain.User;
import nand.revoluttest.domain.UserDto;
import nand.revoluttest.service.GreetingService;
import nand.revoluttest.service.UserService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/hello")
public class GreetingController {
  private final UserService userService;
  private final GreetingService greetingService;

  public GreetingController(UserService userService, GreetingService greetingService) {
    this.userService = userService;
    this.greetingService = greetingService;
  }

  @GetMapping(path = "/{name}", produces = {"application/json"})
  public ResponseEntity greetingGet(@PathVariable String name) {
    return ResponseEntity.ok(new JSONObject().put("message", greetingService.getGreetingForName(name)).toString());
  }

  @PutMapping(path = "{name}")
  public ResponseEntity greetingPut(@PathVariable String name, @RequestBody @Valid UserDto userDto) {
    userService.saveOrUpdateUser(new User(name, userDto.getDateOfBirth()));
    return ResponseEntity.noContent().build();
  }
}
