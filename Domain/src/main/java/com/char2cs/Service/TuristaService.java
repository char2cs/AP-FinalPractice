package com.char2cs.Service;

import com.char2cs.DAO.Crud;
import com.char2cs.DAO.TuristaDAO;
import com.char2cs.Domain.Turista;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Factory.Factory;

import java.util.List;

public class TuristaService implements Crud<Turista> {
    private TuristaDAO turistaDAO;

    public TuristaService(
            Datamethod datamethod
    ) {
        this.turistaDAO = Factory.getTuristaDAO(datamethod);
    }

    @Override
    public void create(Turista objects) throws ObjectAlreadyExistsException {
        turistaDAO.create(objects);
    }

    @Override
    public Turista get(Integer id) throws ObjectNotFoundException {
        return turistaDAO.get(id);
    }

    @Override
    public boolean update(Turista objects) throws ObjectNotFoundException, OperationFailedException {
        return turistaDAO.update(objects);
    }

    @Override
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        return turistaDAO.delete(id);
    }

    @Override
    public List<Turista> getAll() {
        return turistaDAO.getAll();
    }
}
