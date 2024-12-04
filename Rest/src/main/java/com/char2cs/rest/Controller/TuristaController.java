package com.char2cs.rest.Controller;

import com.char2cs.rest.soap.client.generated.Turista;
import com.char2cs.rest.soap.client.generated.TuristaServiceSoapService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/turista")
public class TuristaController {
    private final TuristaServiceSoapService turistaServiceSoapService = new TuristaServiceSoapService();

    @Path("/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet(@PathParam("id") int id) {
        try {
            var turista = turistaServiceSoapService.getTuristaServiceSoapPort().get(id);
            return Response.ok(turista).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

//    @POST()
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response doPost(String turista) {
//        try {
//            Turista turistaObject = new ObjectMapper().readValue(turista, Turista.class);
//            var id = turistaServiceSoapService.getTuristaServiceSoapPort().create(turistaObject);
//            return Response.ok(id).build();
//        }
//        catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }
}
