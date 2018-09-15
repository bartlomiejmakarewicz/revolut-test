package nand.revoluttest.service;

import nand.revoluttest.domain.User;
import nand.revoluttest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUserWithName(String userName) {
    return userRepository.findById(userName).orElse(null);
  }

  @Override
  public void saveOrUpdateUser(User user) {
    userRepository.save(user);
  }
}
