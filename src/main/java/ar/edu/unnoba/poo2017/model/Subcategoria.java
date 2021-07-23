/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name = "subcategorias")
@NamedQueries({
    @NamedQuery(name = "subcategorias.disponibles",
            query = "Select s From Subcategoria s where s.borrado = false and s.empresa = :empresa")})
public class Subcategoria extends Categoria {

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Subcategoria() {
    }

    public Subcategoria(Categoria categoria, String nombre, String descripcion, boolean borrado, Empresa empresa) {
        super(nombre, descripcion, borrado, empresa);
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
