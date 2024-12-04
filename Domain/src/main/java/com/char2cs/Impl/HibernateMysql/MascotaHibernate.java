package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.MascotaDAO;
import com.char2cs.Domain.Mascota;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MascotaHibernate implements MascotaDAO {
    @Override
    public void create(Mascota objects) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Mascota existingMascota = session.get(Mascota.class, objects.getId());
            if (existingMascota != null)
                throw new ObjectAlreadyExistsException("Object already exists");

            session.save(objects);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            throw new ObjectAlreadyExistsException("Object already exists");
        }
    }

    @Override
    public Mascota get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Mascota mascota = session.get(Mascota.class, id);
            if (mascota == null)
                throw new ObjectNotFoundException("Object not found");

            return mascota;
        }
        catch (Exception e) {
            throw new ObjectNotFoundException("Object not found");
        }
    }

    @Override
    public boolean update(Mascota objects) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Mascota existingMascota = session.get(Mascota.class, objects.getId());
            if (existingMascota == null)
                throw new ObjectNotFoundException("Object not found");

            session.update(objects);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public boolean delete(Integer id) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Mascota mascota = session.get(Mascota.class, id);
            if (mascota == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(mascota);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Mascota> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Mascota", Mascota.class).list();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
