package com.char2cs.rest.Controller;

import com.char2cs.rest.Utils.ParseUtils;
import com.char2cs.rest.soap.client.generated.ConsultaSoapService;
import com.char2cs.rest.soap.client.generated.ConsultaSoap;
import com.char2cs.rest.soap.client.generated.Consulta;
import com.char2cs.rest.soap.client.generated.Mascota;
import com.char2cs.rest.soap.client.generated.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Path("/consulta")
public class ConsultaController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarConsulta(String consultaJson) {
        ConsultaSoapService service = new ConsultaSoapService();
        ConsultaSoap consultaSoap = service.getConsultaSoapPort();

        try {
            Consulta consulta = objectMapper.readValue(consultaJson, Consulta.class);
            Consulta registeredConsulta = consultaSoap.registrarConsulta(consulta);
            return Response.ok(registeredConsulta).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error parsing JSON").build();
        }
    }

    @Path("/date/{date}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsultasByDate(@PathParam("date") String date) {
        ConsultaSoapService service = new ConsultaSoapService();
        ConsultaSoap consultaSoap = service.getConsultaSoapPort();

        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            XMLGregorianCalendar dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    new SimpleDateFormat("yyyy-MM-dd").format(parsedDate)
            );

            List<Consulta> consultas = consultaSoap.getConsultasByDate(dateXML);

            return Response.ok(
                    ParseUtils.parseConsultasIntoResoluciones(consultas)
            ).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error parsing JSON").build();
        }
    }

    @Path("/mascota/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsultasByMascota(@PathParam("id") int id) {
        ConsultaSoapService service = new ConsultaSoapService();
        ConsultaSoap consultaSoap = service.getConsultaSoapPort();

        try {
            Mascota mascota = new Mascota();
            mascota.setId(id);

            List<Consulta> consultas = consultaSoap.getConsultasByMascota(mascota);

            return Response.ok(
                    ParseUtils.parseConsultasIntoResoluciones(consultas)
            ).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error parsing JSON").build();
        }
    }

    @Path("/cliente/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsultasByCliente(@PathParam("id") int id) {
        ConsultaSoapService service = new ConsultaSoapService();
        ConsultaSoap consultaSoap = service.getConsultaSoapPort();

        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);

            List<Consulta> consultas = consultaSoap.getConsultasByCliente(cliente);

            return Response.ok(
                    ParseUtils.parseConsultasIntoResoluciones(consultas)
            ).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error parsing JSON").build();
        }
    }

    @Path("/cliente/{id}/date/{start}/{end}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsultasByClienteAndDate(
            @PathParam("id") int id,
            @PathParam("start") String start,
            @PathParam("end") String end
    ) {
        ConsultaSoapService service = new ConsultaSoapService();
        ConsultaSoap consultaSoap = service.getConsultaSoapPort();

        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);

            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);

            XMLGregorianCalendar startDateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    new SimpleDateFormat("yyyy-MM-dd").format(startDate)
            );
            XMLGregorianCalendar endDateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    new SimpleDateFormat("yyyy-MM-dd").format(endDate)
            );

            List<Consulta> consultas = consultaSoap.getConsultasByClienteAndDate(cliente, startDateXML, endDateXML);

            return Response.ok(
                    ParseUtils.parseConsultasIntoResoluciones(consultas)
            ).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error parsing JSON").build();
        }
    }

    @Path("/cliente/{id}/mascota/{mascotaId}/date/{start}/{end}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsultasByClienteMascotaAndDate(
            @PathParam("id") int id,
            @PathParam("mascotaId") int mascotaId,
            @PathParam("start") String start,
            @PathParam("end") String end
    ) {
        ConsultaSoapService service = new ConsultaSoapService();
        ConsultaSoap consultaSoap = service.getConsultaSoapPort();

        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);

            Mascota mascota = new Mascota();
            mascota.setId(mascotaId);

            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);

            XMLGregorianCalendar startDateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    new SimpleDateFormat("yyyy-MM-dd").format(startDate)
            );
            XMLGregorianCalendar endDateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    new SimpleDateFormat("yyyy-MM-dd").format(endDate)
            );

            List<Consulta> consultas = consultaSoap.getConsultasByClienteMascota(cliente, mascota, startDateXML, endDateXML);

            return Response.ok(
                    ParseUtils.parseConsultasIntoResoluciones(consultas)
            ).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error parsing JSON").build();
        }
    }
}
