package com.char2cs.Impl.HibernateMysql;

import com.char2cs.DAO.ConsultaDAO;
import com.char2cs.Domain.Consulta;
import com.char2cs.Exception.ObjectAlreadyExistsException;
import com.char2cs.Exception.ObjectNotFoundException;
import com.char2cs.Exception.OperationFailedException;
import com.char2cs.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class ConsultaHibernate implements ConsultaDAO {
    @Override
    public void create(Consulta objects) throws ObjectAlreadyExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Consulta existingConsulta = session.get(Consulta.class, objects.getId());
            if (existingConsulta != null)
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
    public Consulta get(Integer id) throws ObjectNotFoundException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Consulta consulta = session.get(Consulta.class, id);
            if (consulta == null)
                throw new ObjectNotFoundException("Object not found");

            return consulta;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ObjectNotFoundException("Object not found");
        }
    }

    @Override
    public boolean update(Consulta objects) throws ObjectNotFoundException, OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Consulta existingConsulta = session.get(Consulta.class, objects.getId());
            if (existingConsulta == null)
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
            Consulta existingConsulta = session.get(Consulta.class, id);
            if (existingConsulta == null)
                throw new ObjectNotFoundException("Object not found");

            session.delete(existingConsulta);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Consulta> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consulta", Consulta.class).list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Consulta> findByDate(Date date) throws OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consulta where fecha = :date", Consulta.class)
                    .setParameter("date", date)
                    .list();
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Consulta> findByMascota(Integer id) throws OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consulta where Consulta.mascota.id = :id", Consulta.class)
                    .setParameter("id", id)
                    .list();
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Consulta> findByCliente(Integer id) throws OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consulta where Consulta.cliente.id = :id", Consulta.class)
                    .setParameter("id", id)
                    .list();
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Consulta> findByCliente(Integer id, Date start, Date end) throws OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consulta where Consulta.cliente.id = :id and Consulta.fecha between :start and :end", Consulta.class)
                    .setParameter("id", id)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .list();
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }

    @Override
    public List<Consulta> findByClienteMascota(Integer idCliente, Integer idMascota, Date start, Date end) throws OperationFailedException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consulta where Consulta.cliente.id = :idCliente and Consulta.mascota.id = :idMascota and Consulta.fecha between :start and :end", Consulta.class)
                    .setParameter("idCliente", idCliente)
                    .setParameter("idMascota", idMascota)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .list();
        }
        catch (Exception e) {
            throw new OperationFailedException("Operation failed");
        }
    }
}
