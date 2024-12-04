package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.PuntoAlquilerDAO;
import com.char2cs.Domain.PuntoAlquiler;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;

import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.Session;
import com.char2cs.Util.HibernateUtil;

public class PuntoAlquilerImp implements PuntoAlquilerDAO {
    @Override
    public void create(PuntoAlquiler puntoAlquiler) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            PuntoAlquiler existingPuntoAlquiler = session.get(PuntoAlquiler.class, puntoAlquiler.getId());
            if (existingPuntoAlquiler != null)
                throw new ObjectAlreadyExistsException("Object already exists");

            session.save(puntoAlquiler);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectAlreadyExistsException("Object already exists");
        }
    }

    @Override
    public PuntoAlquiler get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            PuntoAlquiler puntoAlquiler = session.get(PuntoAlquiler.class, id);
            if (puntoAlquiler == null)
                throw new ObjectNotFoundException("Object not found");

            return puntoAlquiler;
        }
    }

    @Override
    @Transactional
    public boolean update(PuntoAlquiler puntoAlquiler) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            PuntoAlquiler existingPuntoAlquiler = session.get(PuntoAlquiler.class, puntoAlquiler.getId());
            if (existingPuntoAlquiler == null)
                throw new ObjectNotFoundException("Object not found");

            session.update(puntoAlquiler);
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
            PuntoAlquiler puntoAlquiler = session.get(PuntoAlquiler.class, id);
            if (puntoAlquiler == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(puntoAlquiler);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new OperationFailedException("Delete operation failed");
        }
    }

    @Override
    public List<PuntoAlquiler> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from PuntoAlquiler", PuntoAlquiler.class).list();
        }
    }
}
