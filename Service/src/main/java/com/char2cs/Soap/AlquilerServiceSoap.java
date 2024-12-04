package com.char2cs.Soap;

import com.char2cs.Domain.Alquiler;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Service.AlquilerService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class AlquilerServiceSoap {
    private static AlquilerService alquilerService = new AlquilerService(Datamethod.HIBERNATE);

    @WebMethod
    public boolean create(Alquiler alquiler) throws ObjectAlreadyExistsException {
        alquilerService.create(alquiler);
        return true;
    }

    @WebMethod
    public Alquiler get(Integer id) throws ObjectNotFoundException {
        return alquilerService.get(id);
    }

    @WebMethod
    public boolean update(Alquiler alquiler) throws ObjectNotFoundException, OperationFailedException {
        alquilerService.update(alquiler);
        return true;
    }

    @WebMethod
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        alquilerService.delete(id);
        return true;
    }

    @WebMethod
    public List<Alquiler> getAll() {
        return alquilerService.getAll();
    }

    @WebMethod
    public boolean alquilar(Alquiler alquiler) {
        return alquilerService.alquilar(alquiler);
    }
}
