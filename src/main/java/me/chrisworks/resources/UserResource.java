package me.chrisworks.resources;

import me.chrisworks.domain.dtos.LoginDTO;
import me.chrisworks.domain.entities.User;
import me.chrisworks.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  @Inject
  private UserService userService;

  @GET
  @Path("test")
  public String testEndpoint() {
    return "Test endpoint";
  }

  @GET
  public Response fetchUsers() {
    return Response.ok(userService.fetchUsers()).build();
  }

  @GET
  @Path("/{userId}")
  public Response fetchUser(@PathParam("userId") Long userId) {
    return Response.ok(userService.fetchUserById(userId)).build();
  }

  @POST
  public Response createUser(User user) {
    return Response.ok(userService.addUser(user)).build();
  }

  @PUT
  @Path("/{userId}")
  public Response modifyUser(@PathParam("userId") Long userId, User user) {
    return Response.ok(userService.modifyUser(userId, user)).build();
  }

  @POST
  @Path("/authenticate")
  public Response createUser(LoginDTO loginDTO) {
    return Response.ok(userService.authenticate(loginDTO)).build();
  }
}
