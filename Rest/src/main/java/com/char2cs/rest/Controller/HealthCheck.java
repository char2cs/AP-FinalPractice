package com.char2cs.rest.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthCheck {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkHealth() {
        return Response.ok("Hello, World!").build();
    }
}