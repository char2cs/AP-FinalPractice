package com.char2cs.Service;

import com.char2cs.DAO.Crud;
import com.char2cs.DAO.PuntoAlquilerDAO;
import com.char2cs.DAO.VehiculoDAO;
import com.char2cs.Domain.Vehiculo;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Factory.Factory;

import java.util.List;

public class VehiculoService implements Crud<Vehiculo> {
    private VehiculoDAO vehiculoDAO;
    private PuntoAlquilerDAO puntoAlquilerDAO;

    public VehiculoService(
            Datamethod datamethod
    ) {
        this.vehiculoDAO = Factory.getVehiculoDAO(datamethod);
        this.puntoAlquilerDAO = Factory.getPuntoAlquilerImp(datamethod);
    }

    @Override
    public void create(Vehiculo objects) throws ObjectAlreadyExistsException {
        try {
            puntoAlquilerDAO.get(objects.getPuntoAlquiler().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        vehiculoDAO.create(objects);
    }

    @Override
    public Vehiculo get(Integer id) throws ObjectNotFoundException {
        return vehiculoDAO.get(id);
    }

    @Override
    public boolean update(Vehiculo objects) throws ObjectNotFoundException, OperationFailedException {
        try {
            puntoAlquilerDAO.get(objects.getPuntoAlquiler().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return vehiculoDAO.update(objects);
    }

    @Override
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        return vehiculoDAO.delete(id);
    }

    @Override
    public List<Vehiculo> getAll() {
        return vehiculoDAO.getAll();
    }
}
