package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.ClienteDAO;
import com.char2cs.Domain.Cliente;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ClienteHibernate implements ClienteDAO {
    @Override
    public void create(Cliente objects) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente existingCliente = session.get(Cliente.class, objects.getId());
            if (existingCliente != null)
                throw new ObjectAlreadyExistsException("Object already exists");

            session.save(objects);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ObjectAlreadyExistsException("Object already exists");
        }
    }

    @Override
    public Cliente get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente cliente = session.get(Cliente.class, id);
            if (cliente == null)
                throw new ObjectNotFoundException("Object not found");

            return cliente;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ObjectNotFoundException("Object not found");
        }
    }

    @Override
    public boolean update(Cliente objects) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente existingCliente = session.get(Cliente.class, objects.getId());
            if (existingCliente == null)
                throw new ObjectNotFoundException("Object not found");

            session.update(objects);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente existingCliente = session.get(Cliente.class, id);
            if (existingCliente == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(existingCliente);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Cliente> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Cliente", Cliente.class).list();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
