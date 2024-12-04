package com.char2cs.Soap;

import com.char2cs.Domain.PuntoAlquiler;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Service.PuntoAlquilerService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class PuntoAlquilerServiceSoap {
    private static PuntoAlquilerService puntoAlquilerService = new PuntoAlquilerService(Datamethod.HIBERNATE);

    @WebMethod
    public boolean create(PuntoAlquiler puntoAlquiler) throws ObjectAlreadyExistsException {
        puntoAlquilerService.create(puntoAlquiler);
        return true;
    }

    @WebMethod
    public PuntoAlquiler get(Integer id) throws ObjectNotFoundException {
        return puntoAlquilerService.get(id);
    }

    @WebMethod
    public boolean update(PuntoAlquiler puntoAlquiler) throws ObjectNotFoundException, OperationFailedException {
        puntoAlquilerService.update(puntoAlquiler);
        return true;
    }

    @WebMethod
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        puntoAlquilerService.delete(id);
        return true;
    }

    @WebMethod
    public List<PuntoAlquiler> getAll() {
        return puntoAlquilerService.getAll();
    }
}
