package com.char2cs.DAO;

import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;

import java.util.List;

public interface Crud<T> {

    /**
     * Crea el objecto sin importar que haya otro UUID repetido
     * @param objects
     */
    void create(T objects) throws ObjectAlreadyExistsException;

    /**
     * Busca por UUID
     * @param id
     * @return
     */
    T get(Integer id) throws ObjectNotFoundException;

    /**
     * Busca el objecto, por UUID y lo actualiza.
     * @param objects
     * @return
     */
    boolean update(T objects) throws ObjectNotFoundException, OperationFailedException;

    /**
     * Borra el objecto relacionado por la UUID deseada.
     * @param id
     * @return
     */
    boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException;

    /**
     * Retorna todos los objectos disponibles.
     * @return
     */
    List<T> getAll();
}