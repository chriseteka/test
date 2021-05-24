package me.chrisworks.domain.entities;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.updateById", query = "UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName," +
        " u.email = :email, u.birthDay = :birthDay, u.password = :password WHERE u.id = :id")
})
public class User {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  @JsonbDateFormat(value = "yyyy-MM-dd")
  private LocalDate birthDay;
  private String password;

  public User() {}

  public User(String firstName, String lastName, String email, LocalDate birthDay, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.birthDay = birthDay;
    this.password = password;
  }

  public User asOutput() {

    User user = new User();
    user.setId(this.getId());
    user.setEmail(this.getEmail());
    user.setLastName(this.getLastName());
    user.setBirthDay(this.getBirthDay());
    user.setFirstName(this.getFirstName());
    return user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(LocalDate birthDay) {
    this.birthDay = birthDay;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String toString() {
    return String.format("{\"firstName\": %s, \"lastName\": %s, \"email\": %s, \"birthDay\": %s}",
        firstName, lastName, email, birthDay);
  }
}
