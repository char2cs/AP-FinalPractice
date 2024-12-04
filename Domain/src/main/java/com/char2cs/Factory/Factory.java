package com.char2cs.Factory;

import com.char2cs.DAO.AlquilerDAO;
import com.char2cs.DAO.PuntoAlquilerDAO;
import com.char2cs.DAO.TuristaDAO;
import com.char2cs.DAO.VehiculoDAO;
import com.char2cs.Impl.HibernateMysql.AlquilerImp;
import com.char2cs.Impl.HibernateMysql.PuntoAlquilerImp;
import com.char2cs.Impl.HibernateMysql.TuristaImp;
import com.char2cs.Impl.HibernateMysql.VehiculoImp;

public class Factory {
    public static AlquilerDAO getAlquilerDAO(
            Datamethod datamethod
    ) {
        switch (datamethod) {
            case HIBERNATE:
                return new AlquilerImp();
            default:
                return null;
        }
    }

    public static PuntoAlquilerDAO getPuntoAlquilerImp(
            Datamethod datamethod
    ) {
        switch (datamethod) {
            case HIBERNATE:
                return new PuntoAlquilerImp();
            default:
                return null;
        }
    }

    public static TuristaDAO getTuristaDAO(
            Datamethod datamethod
    ) {
        switch (datamethod) {
            case HIBERNATE:
                return new TuristaImp();
            default:
                return null;
        }
    }

    public static VehiculoDAO getVehiculoDAO(
            Datamethod datamethod
    ) {
        switch (datamethod) {
            case HIBERNATE:
                return new VehiculoImp();
            default:
                return null;
        }
    }
}
