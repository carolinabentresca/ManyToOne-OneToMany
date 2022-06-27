package com.example.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "colegio")
public class Colegio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colegio")
    private int id;
    @Column
    private String nombre;
    @Column
    private String localidad;
    @Column
    private String provincia;
    @Column
    private String pais;
    @OneToMany(mappedBy = "colegio", cascade = CascadeType.ALL)
    private List<Alumno> alumnos = new ArrayList();

    public Colegio() {
    }

    public Colegio(int id) {
        this.id = id;
    }

    public Colegio(int id, String nombre, String localidad, String provincia, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
        for (Alumno elem : alumnos) {
            elem.setColegio(this);
        }
    }

}
