/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.CategoriaDAO;
import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gabriel
 */
@Named
@ViewScoped
public class CategoriaBacking implements Serializable {

    private Categoria categoria;

    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        this.categoria = new Categoria();
    }

    @EJB
    private CategoriaDAO categoriaDAO;

    public String create() {
        //Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        try {
            categoria.setEmpresa(sessionBacking.getUsuario().getEmpresa());
            categoriaDAO.create(categoria);
            return "/Categoria/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public List<Categoria> getCategorias() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return categoriaDAO.getCategorias(empresa);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String update() {
        try {
            categoriaDAO.update(categoria);
            return "/Categoria/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Categoria categoria) {
        categoria.setBorrado(true);
        categoriaDAO.update(categoria);

    }

}
