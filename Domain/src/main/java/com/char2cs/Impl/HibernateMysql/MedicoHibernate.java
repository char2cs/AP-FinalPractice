package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.MedicoDAO;
import com.char2cs.Domain.Medico;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MedicoHibernate implements MedicoDAO {
    @Override
    public void create(Medico objects) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Medico existingMedico = session.get(Medico.class, objects.getId());
            if (existingMedico != null)
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
    public Medico get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Medico medico = session.get(Medico.class, id);
            if (medico == null)
                throw new ObjectNotFoundException("Object not found");

            return medico;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ObjectNotFoundException("Object not found");
        }
    }

    @Override
    public boolean update(Medico objects) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Medico existingMedico = session.get(Medico.class, objects.getId());
            if (existingMedico == null)
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
            Medico medico = session.get(Medico.class, id);
            if (medico == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(medico);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Medico> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Medico", Medico.class).list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
