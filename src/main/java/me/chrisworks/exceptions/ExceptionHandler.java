package me.chrisworks.exceptions;

import me.chrisworks.domain.dtos.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<AppException> {

  public Response toResponse(AppException e) {
    return Response.status(Response.Status.BAD_REQUEST).entity(new ExceptionResponse(e.getMessage())).build();
  }
}
