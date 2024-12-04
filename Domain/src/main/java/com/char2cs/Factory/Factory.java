package com.char2cs.Factory;

import com.char2cs.DAO.ClienteDAO;
import com.char2cs.DAO.ConsultaDAO;
import com.char2cs.DAO.MascotaDAO;
import com.char2cs.DAO.MedicoDAO;
import com.char2cs.Impl.HibernateMysql.ClienteHibernate;
import com.char2cs.Impl.HibernateMysql.ConsultaHibernate;
import com.char2cs.Impl.HibernateMysql.MascotaHibernate;
import com.char2cs.Impl.HibernateMysql.MedicoHibernate;

public class Factory {
    public static ConsultaDAO consultaDAO(Datamethod datamethod) {
        switch (datamethod) {
            case HIBERNATE:
                return new ConsultaHibernate();
            default:
                return null;
        }
    }

    public static ClienteDAO clienteDAO(Datamethod datamethod) {
        switch (datamethod) {
            case HIBERNATE:
                return new ClienteHibernate();
            default:
                return null;
        }
    }

    public static MascotaDAO mascotaDAO(Datamethod datamethod) {
        switch (datamethod) {
            case HIBERNATE:
                return new MascotaHibernate();
            default:
                return null;
        }
    }

    public static MedicoDAO medicoDAO(Datamethod datamethod) {
        switch (datamethod) {
            case HIBERNATE:
                return new MedicoHibernate();
            default:
                return null;
        }
    }
}
