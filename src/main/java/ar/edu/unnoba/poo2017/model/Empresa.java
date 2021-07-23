/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Andres
 */

@Entity
@Table(name = "empresas")
@NamedQueries({
    @NamedQuery(name = "empresa.disponibles",
           query = "Select e From Empresa e")  
})
public class Empresa extends AbstractEntity {
    private String rason_social;
    private String direccion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_creacion;

    public Empresa() {
    }

    public Empresa(String rason_social, String direccion, Date fecha_creacion) {
        super();
        this.rason_social = rason_social;
        this.direccion = direccion;
        this.fecha_creacion = fecha_creacion;
    }

    public String getRason_social() {
        return rason_social;
    }

    public void setRason_social(String rason_social) {
        this.rason_social = rason_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}
