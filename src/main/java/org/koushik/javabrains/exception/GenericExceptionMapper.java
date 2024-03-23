package org.koushik.javabrains.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.koushik.javabrains.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                "https://github.com/koushik/java-brains");

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
