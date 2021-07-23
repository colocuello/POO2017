/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "gastos")
@NamedQueries({
    @NamedQuery(name = "gasto.todos",
            query = "Select g From Gasto g where g.empresa = :empresa")
    ,
   @NamedQuery(name = "gasto.periodo.sub",
            query = "select g from Gasto g where g.fecha between :desde and :hasta and g.subcategoria = :subcategoria and g.empresa = :empresa")
    ,
    @NamedQuery(name = "gasto.periodo.cat",
            query = "select g from Gasto g where g.fecha between :desde and :hasta and g.subcategoria.categoria = :categoria and g.empresa = :empresa")
    ,
    @NamedQuery(name = "gasto.cat",
            query = "select g from Gasto g where g.subcategoria.categoria = :categoria and g.empresa = :empresa")
})
public class Gasto extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String descripcion;
    private float importe;

    public Gasto() {
    }

    public Gasto(Date fecha, float importe, Subcategoria subcategoria, Empresa empresa) {
        super();
        this.fecha = fecha;
        this.importe = importe;
        this.subcategoria = subcategoria;
        this.empresa = empresa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
