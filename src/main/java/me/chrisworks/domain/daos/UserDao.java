package me.chrisworks.domain.daos;

import me.chrisworks.domain.entities.User;
import me.chrisworks.exceptions.AppException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static me.chrisworks.utils.Validators.isValidBirthDay;
import static me.chrisworks.utils.Validators.isValidEmail;

@Stateless
public class UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  public User save(User user) {

    final User userToSave = validateThenPreset(new User(), user);
    entityManager.persist(userToSave);
    return userToSave.asOutput();
  }

  public User update(Long userId, User user) {

    final User userById = findById(userId);
    if (userById == null) throw new AppException("User not found with id: " + userId);

    final User existingUser = validateThenPreset(userById, user);
    Query query = entityManager.createNamedQuery("User.updateById")
        .setParameter("id", userId)
        .setParameter("email", existingUser.getEmail())
        .setParameter("birthDay", existingUser.getBirthDay())
        .setParameter("lastName", existingUser.getLastName())
        .setParameter("firstName", existingUser.getFirstName())
        .setParameter("password", existingUser.getPassword());

    return query.executeUpdate() == 1 ? existingUser.asOutput() : null;
  }

  public User findById(Long userId) {

    return entityManager.find(User.class, userId);
  }

  public User findByEmail(String email) {

    User user = null;
    Query query = entityManager.createNamedQuery("User.findByEmail").setParameter("email", email);
    try {
      user = (User) query.getSingleResult();
    } catch (Exception ignored) {}

    return user;
  }

  public List<User> findAll() {
    return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
  }

  private boolean emailNotTaken(String email) {

    if (findByEmail(email) != null)
      throw new AppException("User already exist with email: " + email);

    return true;
  }

  private User validateThenPreset(User userToPreset, User userToValidate) {

    if (isValidBirthDay(userToValidate.getBirthDay()))
      userToPreset.setBirthDay(userToValidate.getBirthDay());
    if (!userToValidate.getEmail().equalsIgnoreCase(userToPreset.getEmail())
        && isValidEmail(userToValidate.getEmail())
        && emailNotTaken(userToValidate.getEmail()))
      userToPreset.setEmail(userToValidate.getEmail());
    if (userToValidate.getPassword() != null && userToValidate.getPassword().length() > 0)
      userToPreset.setPassword(userToValidate.getPassword());
    if (userToValidate.getLastName() != null && userToValidate.getLastName().length() > 0)
      userToPreset.setLastName(userToValidate.getLastName());
    if (userToValidate.getFirstName() != null && userToValidate.getFirstName().length() > 0)
      userToPreset.setFirstName(userToValidate.getFirstName());

    return userToPreset;
  }
}
