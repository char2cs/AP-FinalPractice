package com.char2cs.Service;

import com.char2cs.DAO.ClienteDAO;
import com.char2cs.DAO.ConsultaDAO;
import com.char2cs.DAO.MascotaDAO;
import com.char2cs.DAO.MedicoDAO;
import com.char2cs.Domain.Cliente;
import com.char2cs.Domain.Consulta;
import com.char2cs.Domain.Mascota;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Factory.Factory;

import java.util.Date;
import java.util.List;

public class ConsultaService {
    private ConsultaDAO consultaDAO;
    private ClienteDAO clienteDAO;
    private MedicoDAO medicoDAO;
    private MascotaDAO mascotaDAO;

    public ConsultaService(
            Datamethod datamethod
    ) {
        consultaDAO = Factory.consultaDAO(datamethod);
        clienteDAO = Factory.clienteDAO(datamethod);
        medicoDAO = Factory.medicoDAO(datamethod);
        mascotaDAO = Factory.mascotaDAO(datamethod);
    }

    public Consulta registrarConsulta(
            Consulta consulta
    ) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        // ? Checkeamos que el medico este presente en la base
        medicoDAO.get(consulta.getMedico().getId());

        // ? Checkeamos que la mascota y cliente este presente en la base
        Cliente cliente = clienteDAO.get(consulta.getCliente().getId());
        mascotaDAO.get(consulta.getMascota().getId());

        // ? Checkeamos que la mascota pertenezca al cliente
        if (cliente.getMascotas().stream().noneMatch(
            mascota -> mascota.getId() == consulta.getMascota().getId()
        ))
            throw new ObjectNotFoundException("La mascota no pertenece al cliente");

        // ? Guardamos la consulta
        consultaDAO.create(consulta);

        return consultaDAO.get(consulta.getId());
    }

    public List<Consulta> getConsultasByDate(
            Date fecha
    ) throws OperationFailedException {
        return consultaDAO.findByDate(fecha);
    }

    public List<Consulta> getConsultasByMascota(
            Mascota mascota
    ) throws ObjectNotFoundException, OperationFailedException {
        mascotaDAO.get(mascota.getId());

        return consultaDAO.findByMascota(mascota.getId());
    }

    public List<Consulta> getConsultasByCliente(
            Cliente cliente
    ) throws ObjectNotFoundException, OperationFailedException {
        clienteDAO.get(cliente.getId());

        return consultaDAO.findByCliente(cliente.getId());
    }

    public List<Consulta> getConsultasByCliente(
            Cliente cliente,
            Date start,
            Date end
    ) throws ObjectNotFoundException, OperationFailedException {
        clienteDAO.get(cliente.getId());

        return consultaDAO.findByCliente(cliente.getId(), start, end);
    }

    public List<Consulta> getConsultasByClienteMascota(
            Cliente cliente,
            Mascota mascota,
            Date start,
            Date end
    ) throws ObjectNotFoundException, OperationFailedException {
        clienteDAO.get(cliente.getId());
        mascotaDAO.get(mascota.getId());

        return consultaDAO.findByClienteMascota(cliente.getId(), mascota.getId(), start, end);
    }
}
