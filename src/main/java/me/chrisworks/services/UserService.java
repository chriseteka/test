package me.chrisworks.services;

import me.chrisworks.domain.dtos.LoginDTO;
import me.chrisworks.domain.entities.User;

import java.util.List;

public interface UserService {

  List<User> fetchUsers();
  User fetchUserById(Long userId);
  User addUser(User user);
  User modifyUser(Long userId, User user);
  User authenticate(LoginDTO loginDTO);
}
