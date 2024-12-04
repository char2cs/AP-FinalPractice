package com.char2cs.Soap;

import com.char2cs.Domain.Cliente;
import com.char2cs.Domain.Consulta;
import com.char2cs.Domain.Mascota;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Service.ConsultaService;
import com.char2cs.Factory.Datamethod;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class ConsultaSoap {
    private static final Datamethod datamethod = Datamethod.HIBERNATE;
    private static final ConsultaService consultaService = new ConsultaService(datamethod);

    @WebMethod
    public Consulta registrarConsulta(
            Consulta consulta
    ) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        return consultaService.registrarConsulta(consulta);
    }

    @WebMethod
    public List<Consulta> getConsultasByDate(
            Date fecha
    ) throws OperationFailedException {
        return consultaService.getConsultasByDate(fecha);
    }

    @WebMethod
    public List<Consulta> getConsultasByMascota(
            Mascota mascota
    ) throws ObjectNotFoundException, OperationFailedException {
        return consultaService.getConsultasByMascota(mascota);
    }

    @WebMethod
    public List<Consulta> getConsultasByCliente(
            Cliente cliente
    ) throws ObjectNotFoundException, OperationFailedException {
        return consultaService.getConsultasByCliente(cliente);
    }

    @WebMethod
    public List<Consulta> getConsultasByClienteAndDate(
            Cliente cliente,
            Date start,
            Date end
    ) throws ObjectNotFoundException, OperationFailedException {
        return consultaService.getConsultasByCliente(cliente, start, end);
    }

    @WebMethod
    public List<Consulta> getConsultasByClienteMascota(
            Cliente cliente,
            Mascota mascota,
            Date start,
            Date end
    ) throws ObjectNotFoundException, OperationFailedException {
        return consultaService.getConsultasByClienteMascota(cliente, mascota, start, end);
    }
}
