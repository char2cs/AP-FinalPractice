package com.char2cs;

import com.char2cs.Domain.Alquiler;
import com.char2cs.Domain.PuntoAlquiler;
import com.char2cs.Domain.Turista;
import com.char2cs.Domain.Vehiculo;
import com.char2cs.Factory.Factory;
import com.char2cs.Factory.Datamethod;
import com.char2cs.Service.AlquilerService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        PuntoAlquiler puntoAlquiler = new PuntoAlquiler(
                23,
                "Calle 23",
                "Calle 23",
                "011 2844-7029",
                "mateo@rounds.com.ar",
                "23 a 24 horas",
                new ArrayList<>()
        );

        Vehiculo vehiculo = new Vehiculo(
                23,
                "Toyota",
                "Corolla",
                "Sedan",
                puntoAlquiler
        );

        puntoAlquiler.getVehiculos().add(vehiculo);

        Factory.getVehiculoDAO(Datamethod.HIBERNATE).create(vehiculo);
        Factory.getPuntoAlquilerImp(Datamethod.HIBERNATE).create(puntoAlquiler);

        Turista turista = new Turista(
                23,
                "Mateo",
                "Rounds",
                "45518340",
                "011 2844-7029",
                "Ituziango 670",
                "mateo@rounds.com.ar"
        );

        Factory.getTuristaDAO(Datamethod.HIBERNATE).create(turista);

        AlquilerService alquilerService = new AlquilerService(Datamethod.HIBERNATE);

        Boolean test = alquilerService.alquilar(new Alquiler(
                23,
                null,
                null,
                100.00,
                turista,
                vehiculo,
                puntoAlquiler
        ));

        System.out.println(test);
    }
}