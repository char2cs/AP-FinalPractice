package com.char2cs.Service;

import com.char2cs.DAO.*;
import com.char2cs.Domain.Alquiler;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Factory.Factory;

import java.util.List;

public class AlquilerService implements Crud<Alquiler> {
    private AlquilerDAO alquilerDAO;
    private PuntoAlquilerDAO puntoAlquilerDAO;
    private VehiculoDAO vehiculoDAO;
    private TuristaDAO turistaDAO;

    public AlquilerService(
            Datamethod datamethod
    ) {
        this.alquilerDAO = Factory.getAlquilerDAO(datamethod);
        this.puntoAlquilerDAO = Factory.getPuntoAlquilerImp(datamethod);
        this.vehiculoDAO = Factory.getVehiculoDAO(datamethod);
        this.turistaDAO = Factory.getTuristaDAO(datamethod);
    }

    @Override
    public void create(Alquiler alquiler) throws ObjectAlreadyExistsException {
        try {
            puntoAlquilerDAO.get(alquiler.getPuntoAlquiler().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            turistaDAO.get(alquiler.getTurista().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            vehiculoDAO.get(alquiler.getVehiculo().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        alquilerDAO.create(alquiler);
    }

    @Override
    public Alquiler get(Integer id) throws ObjectNotFoundException {
        return alquilerDAO.get(id);
    }

    @Override
    public boolean update(Alquiler objects) throws ObjectNotFoundException, OperationFailedException {
        return alquilerDAO.update(objects);
    }

    @Override
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        return alquilerDAO.delete(id);
    }

    @Override
    public List<Alquiler> getAll() {
        return alquilerDAO.getAll();
    }

    public boolean alquilar(
            Alquiler alquiler
    ) {
        try {
            puntoAlquilerDAO.get(alquiler.getPuntoAlquiler().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            turistaDAO.get(alquiler.getTurista().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            vehiculoDAO.get(alquiler.getVehiculo().getId());
        }
        catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            alquilerDAO.get(alquiler.getId());

            alquilerDAO.update(alquiler);
        }
        catch (ObjectNotFoundException e) {
            try {
                alquilerDAO.create(alquiler);
            }
            catch (ObjectAlreadyExistsException err) {
                err.printStackTrace();
                return false;
            }
        }
        catch (OperationFailedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
