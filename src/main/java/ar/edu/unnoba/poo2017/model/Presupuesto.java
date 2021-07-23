/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "presupuestos")
@NamedQueries({
    @NamedQuery(name = "presupuesto.todos",
            query = "Select g From Presupuesto g where g.empresa = :empresa")
    ,
@NamedQuery(name = "presupuesto.periodo.sub",
            query = "select p from Presupuesto p where p.periodo = :periodo and p.subcategoria = :subcategoria and p.empresa = :empresa")
    ,
       @NamedQuery(name = "presupuesto.periodo.cat",
            query = "select p from Presupuesto p where p.periodo = :periodo and p.subcategoria.categoria = :categoria and p.empresa = :empresa")
    ,
@NamedQuery(name = "presupuesto.cat",
            query = "select p from Presupuesto p where p.subcategoria.categoria = :categoria and p.empresa = :empresa")})

public class Presupuesto extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private float monto;

    public Presupuesto() {
    }

    public Presupuesto(float monto, Subcategoria subcategoria, Periodo periodo, Empresa empresa) {
        super();
        this.monto = monto;
        this.subcategoria = subcategoria;
        this.periodo = periodo;
        this.empresa = empresa;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
