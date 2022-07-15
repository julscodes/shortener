package com.julscodes.shortener;

import com.julscodes.shortener.api.v1.pojo.ErrorResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ShortenerExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {

        String message = exception.getMessage();
        Response response = exception.getResponse();
        Response.Status status = Response.Status.fromStatusCode(response.getStatus());

        ErrorResponse errorResponse = new ErrorResponse(message);

        return Response.status(status)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
