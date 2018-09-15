package nand.revoluttest.service;

import nand.revoluttest.domain.User;

public interface UserService {
  User getUserWithName(String userName);
  void saveOrUpdateUser(User user);
}
