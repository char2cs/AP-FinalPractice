package com.char2cs.Domain;

import javax.persistence.*;

@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "matricula")
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_alquiler_id")
    private PuntoAlquiler puntoAlquiler;

    public Vehiculo() {}

    public Vehiculo(Integer id, String marca, String modelo, String matricula, PuntoAlquiler puntoAlquiler) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.puntoAlquiler = puntoAlquiler;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public PuntoAlquiler getPuntoAlquiler() {
        return puntoAlquiler;
    }

    public void setPuntoAlquiler(PuntoAlquiler puntoAlquiler) {
        this.puntoAlquiler = puntoAlquiler;
    }
}