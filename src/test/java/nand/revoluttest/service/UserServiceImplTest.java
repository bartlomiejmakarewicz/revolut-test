package nand.revoluttest.service;

import nand.revoluttest.domain.User;
import nand.revoluttest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
  @Mock
  private UserRepository userRepository;
  private UserService userService;

  private static final String INPUT_NAME = "John";

  @Before
  public void setUp() {
    userService = new UserServiceImpl(userRepository);
  }

  @Test
  public void givenUserPersisted_whenUserNameRequested_shouldReturnUser() {
    User user = new User(INPUT_NAME, LocalDate.now());
    when(userRepository.findById(INPUT_NAME)).thenReturn(Optional.of(user));

    User returnedUser = userService.getUserWithName(INPUT_NAME);

    then(userRepository).should(times(1)).findById(INPUT_NAME);
    assertEquals(user, returnedUser);
  }

  @Test
  public void givenUserNotPersisted_whenUserNameRequested_shouldReturnNull() {
    when(userRepository.findById(INPUT_NAME)).thenReturn(Optional.empty());

    User returnedUser = userService.getUserWithName(INPUT_NAME);

    then(userRepository).should(times(1)).findById(INPUT_NAME);
    assertNull(returnedUser);
  }

  @Test
  public void givenUser_whenSaveRequested_shouldSaveInRepository() {
    User user = new User(INPUT_NAME, LocalDate.now());
    userService.saveOrUpdateUser(user);

    then(userRepository).should(times(1)).save(user);
  }
}