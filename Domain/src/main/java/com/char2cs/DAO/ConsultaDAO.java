package com.char2cs.DAO;

import com.char2cs.Domain.Consulta;
import com.char2cs.Exception.OperationFailedException;

import java.util.Date;
import java.util.List;

public interface ConsultaDAO extends Crud<Consulta> {
    List<Consulta> findByDate(Date date) throws OperationFailedException;

    List<Consulta> findByMascota(Integer id) throws OperationFailedException;

    List<Consulta> findByCliente(Integer id) throws OperationFailedException;

    List<Consulta> findByCliente(Integer id, Date start, Date end) throws OperationFailedException;

    List<Consulta> findByClienteMascota(Integer idCliente, Integer idMascota, Date start, Date end) throws OperationFailedException;
}
