package com.char2cs.Service;

import com.char2cs.DAO.*;
import com.char2cs.Domain.PuntoAlquiler;
import com.char2cs.Domain.Vehiculo;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Factory.Factory;

import java.util.List;

public class PuntoAlquilerService implements Crud<PuntoAlquiler> {
    private PuntoAlquilerDAO puntoAlquilerDAO;
    private VehiculoDAO vehiculoDAO;

    public PuntoAlquilerService(
            Datamethod datamethod
    ) {
        this.puntoAlquilerDAO = Factory.getPuntoAlquilerImp(datamethod);
        this.vehiculoDAO = Factory.getVehiculoDAO(datamethod);
    }

    @Override
    public void create(PuntoAlquiler objects) throws ObjectAlreadyExistsException {
        try {
            for (Vehiculo vehiculo : objects.getVehiculos())
                vehiculoDAO.get(vehiculo.getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        puntoAlquilerDAO.create(objects);
    }

    @Override
    public PuntoAlquiler get(Integer id) throws ObjectNotFoundException {
        return puntoAlquilerDAO.get(id);
    }

    @Override
    public boolean update(PuntoAlquiler objects) throws ObjectNotFoundException, OperationFailedException {
        for (Vehiculo vehiculo : objects.getVehiculos())
            vehiculoDAO.update(vehiculo);

        return puntoAlquilerDAO.update(objects);
    }

    @Override
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        return puntoAlquilerDAO.delete(id);
    }

    @Override
    public List<PuntoAlquiler> getAll() {
        return puntoAlquilerDAO.getAll();
    }
}
