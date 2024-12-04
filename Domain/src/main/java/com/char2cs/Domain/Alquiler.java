package com.char2cs.Domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "turista_cedula")
    private Turista turista;
    @ManyToOne
    @JoinColumn(name = "vehiculo_placa")
    private Vehiculo vehiculo;
    @ManyToOne
    @JoinColumn(name = "punto_alquiler_id")
    private PuntoAlquiler puntoAlquiler;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Column(name = "presupuesto")
    private Double presupuesto;

    public Alquiler() {}

    public Alquiler(Integer id, Date fechaInicio, Date fechaFin, Double presupuesto, Turista turista, Vehiculo vehiculo, PuntoAlquiler puntoAlquiler) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuesto = presupuesto;
        this.turista = turista;
        this.vehiculo = vehiculo;
        this.puntoAlquiler = puntoAlquiler;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public PuntoAlquiler getPuntoAlquiler() {
        return puntoAlquiler;
    }

    public void setPuntoAlquiler(PuntoAlquiler puntoAlquiler) {
        this.puntoAlquiler = puntoAlquiler;
    }
}
