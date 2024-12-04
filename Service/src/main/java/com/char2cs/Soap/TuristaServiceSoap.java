package com.char2cs.Soap;

import com.char2cs.Domain.Turista;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Service.TuristaService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class TuristaServiceSoap {
    private static TuristaService turistaService = new TuristaService(Datamethod.HIBERNATE);

    @WebMethod
    public void create(Turista turista) throws ObjectAlreadyExistsException {
        turistaService.create(turista);
    }

    @WebMethod
    public Turista get(Integer id) throws ObjectNotFoundException {
        return turistaService.get(id);
    }

    @WebMethod
    public boolean update(Turista turista) throws ObjectNotFoundException, OperationFailedException {
        return turistaService.update(turista);
    }

    @WebMethod
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        return turistaService.delete(id);
    }

    @WebMethod
    public List<Turista> getAll() {
        return turistaService.getAll();
    }
}
