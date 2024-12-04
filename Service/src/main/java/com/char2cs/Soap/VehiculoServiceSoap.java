package com.char2cs.Soap;

import com.char2cs.Domain.Vehiculo;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Service.VehiculoService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class VehiculoServiceSoap {
    private static VehiculoService vehiculoService = new VehiculoService(Datamethod.HIBERNATE);

    @WebMethod
    public void create(Vehiculo vehiculo) throws ObjectAlreadyExistsException {
        vehiculoService.create(vehiculo);
    }

    @WebMethod
    public Vehiculo get(Integer id) throws ObjectNotFoundException {
        return vehiculoService.get(id);
    }

    @WebMethod
    public boolean update(Vehiculo vehiculo) throws ObjectNotFoundException, OperationFailedException {
        return vehiculoService.update(vehiculo);
    }

    @WebMethod
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        return vehiculoService.delete(id);
    }

    @WebMethod
    public List<Vehiculo> getAll() {
        return vehiculoService.getAll();
    }
}
