package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.TuristaDAO;
import com.char2cs.Domain.Turista;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;

import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.Session;
import com.char2cs.Util.HibernateUtil;

public class TuristaImp implements TuristaDAO {
    @Override
    public void create(Turista turista) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Turista existingTurista = session.get(Turista.class, turista.getId());
            if (existingTurista != null)
                throw new ObjectAlreadyExistsException("Object already exists");

            session.save(turista);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectAlreadyExistsException("Object already exists");
        }
    }

    @Override
    public Turista get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Turista turista = session.get(Turista.class, id);
            if (turista == null)
                throw new ObjectNotFoundException("Object not found");

            return turista;
        }
    }

    @Override
    @Transactional
    public boolean update(Turista turista) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Turista existingTurista = session.get(Turista.class, turista.getId());
            if (existingTurista == null)
                throw new ObjectNotFoundException("Object not found");

            session.update(turista);
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
            Turista turista = session.get(Turista.class, id);
            if (turista == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(turista);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new OperationFailedException("Delete operation failed");
        }
    }

    @Override
    public List<Turista> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Turista", Turista.class).list();
        }
    }
}
