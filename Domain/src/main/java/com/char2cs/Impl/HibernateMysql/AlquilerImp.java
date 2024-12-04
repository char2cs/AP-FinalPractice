package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.AlquilerDAO;
import com.char2cs.Domain.Alquiler;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;

import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.Session;
import com.char2cs.Util.HibernateUtil;

public class AlquilerImp implements AlquilerDAO {
    @Override
    public void create(Alquiler alquiler) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Alquiler existingAlquiler = session.get(Alquiler.class, alquiler.getId());
            if (existingAlquiler != null)
                throw new ObjectAlreadyExistsException("Object already exists");

            session.save(alquiler);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectAlreadyExistsException("Object already exists");
        }
    }

    @Override
    public Alquiler get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Alquiler alquiler = session.get(Alquiler.class, id);
            if (alquiler == null)
                throw new ObjectNotFoundException("Object not found");

            return alquiler;
        }
    }

    @Override
    @Transactional
    public boolean update(Alquiler alquiler) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Alquiler existingAlquiler = session.get(Alquiler.class, alquiler.getId());
            if (existingAlquiler == null)
                throw new ObjectNotFoundException("Object not found");

            session.update(alquiler);
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
            Alquiler alquiler = session.get(Alquiler.class, id);
            if (alquiler == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(alquiler);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new OperationFailedException("Delete operation failed");
        }
    }

    @Override
    public List<Alquiler> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Alquiler", Alquiler.class).list();
        }
    }
}
