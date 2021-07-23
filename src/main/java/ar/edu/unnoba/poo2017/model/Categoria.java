/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author gabriel
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "categorias")
@NamedQueries({
    @NamedQuery(name = "categorias.disponibles",
            query = "SELECT c FROM Categoria c, Subcategoria s where c.empresa = :empresa and c.borrado = false and c.nombre != s.nombre group by c.id")})
public class Categoria extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private String nombre;
    private String descripcion;
    private boolean borrado;

    public Categoria() {
    }

    public Categoria(String nombre, String descripcion, boolean borrado, Empresa empresa) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.borrado = borrado;
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
