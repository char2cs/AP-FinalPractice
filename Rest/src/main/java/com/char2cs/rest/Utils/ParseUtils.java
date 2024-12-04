package com.char2cs.rest.Utils;

import com.char2cs.rest.Entity.Resolucion;
import com.char2cs.rest.soap.client.generated.Consulta;

import java.util.List;
import java.util.stream.Collectors;

public class ParseUtils {
    public static List<Resolucion> parseConsultasIntoResoluciones(
            List<Consulta> consultas
    ) {
        return consultas.stream().map(consulta -> {
            Resolucion resolucion = new Resolucion(
                    consulta.getMascota().getNombre(),
                    consulta.getCliente().getNombre(),
                    consulta.getFecha().toString(),
                    consulta.getMotivo(),
                    consulta.getDiagnostico(),
                    consulta.getMedico().getNombre()
            );
            return resolucion;
        }).collect(Collectors.toList());
    }

}
