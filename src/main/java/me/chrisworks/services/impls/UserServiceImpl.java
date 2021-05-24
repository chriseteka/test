package me.chrisworks.services.impls;

import me.chrisworks.domain.daos.UserDao;
import me.chrisworks.domain.dtos.LoginDTO;
import me.chrisworks.domain.entities.User;
import me.chrisworks.exceptions.AppException;
import me.chrisworks.services.UserService;
import me.chrisworks.utils.PasswordEncoder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  @Inject
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> fetchUsers() {
    return userDao.findAll().stream().map(User::asOutput).collect(Collectors.toList());
  }

  public User fetchUserById(Long userId) {

    final User userById = userDao.findById(userId);
    if (userById == null)
      throw new AppException("User not found with id: " + userId);

    return userById.asOutput();
  }

  public User addUser(User user) {

    if (user.getPassword() != null && user.getPassword().length() > 3) {
      try {
        user.setPassword(PasswordEncoder.hashPassword(user.getPassword()));
      } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        throw new AppException("Cannot hash password with reason: " + e.getMessage());
      }
    }
    else throw new AppException("User password cannot be null and must be at least three characters");

    return userDao.save(user);
  }

  public User modifyUser(Long userId, User user) {

    if (user.getPassword() != null && user.getPassword().length() > 3) {
      try {
        user.setPassword(PasswordEncoder.hashPassword(user.getPassword()));
      } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        throw new AppException("User password cannot be hashed with reason: " + e.getMessage());
      }
    }

    return userDao.update(userId, user);
  }

  public User authenticate(LoginDTO loginDTO) {

    final User userByEmail = userDao.findByEmail(loginDTO.getEmail());
    if (userByEmail == null)
      throw new AppException("User not found with email: " + loginDTO.getEmail());

    try {

      if (PasswordEncoder.validatePassword(loginDTO.getPassword(), userByEmail.getPassword()))
        return userByEmail.asOutput();
      else throw new AppException("Invalid credentials, check your inputs and try again");

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AppException("Could not validate password with reason: " + e.getMessage());
    }

  }
}
