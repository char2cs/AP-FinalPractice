package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.VehiculoDAO;
import com.char2cs.Domain.Vehiculo;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;

import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.Session;
import com.char2cs.Util.HibernateUtil;

public class VehiculoImp implements VehiculoDAO {
    @Override
    public void create(Vehiculo vehiculo) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Vehiculo existingVehiculo = session.get(Vehiculo.class, vehiculo.getId());
            if (existingVehiculo != null)
                throw new ObjectAlreadyExistsException("Object already exists");

            session.save(vehiculo);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectAlreadyExistsException("Object already exists");
        }
    }

    @Override
    public Vehiculo get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Vehiculo vehiculo = session.get(Vehiculo.class, id);
            if (vehiculo == null)
                throw new ObjectNotFoundException("Object not found");

            return vehiculo;
        }
    }

    @Override
    @Transactional
    public boolean update(Vehiculo vehiculo) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Vehiculo existingVehiculo = session.get(Vehiculo.class, vehiculo.getId());
            if (existingVehiculo == null)
                throw new ObjectNotFoundException("Object not found");

            session.update(vehiculo);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new OperationFailedException("Update operation failed");
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Vehiculo vehiculo = session.get(Vehiculo.class, id);
            if (vehiculo == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(vehiculo);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new OperationFailedException("Delete operation failed");
        }
    }

    @Override
    public List<Vehiculo> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Vehiculo", Vehiculo.class).list();
        }
    }
}
