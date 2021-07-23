/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.CategoriaDAO;
import ar.edu.unnoba.poo2017.DAO.SubcategoriaDAO;
import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Subcategoria;
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
public class SubcategoriaBacking implements Serializable {

    private Subcategoria subcategoria;

    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        this.subcategoria = new Subcategoria();
    }

    @EJB
    private SubcategoriaDAO subcategoriaDAO;

    @EJB
    private CategoriaDAO categoriaDAO;

    public String create() {
        try {
            subcategoria.setEmpresa(sessionBacking.getUsuario().getEmpresa());
            subcategoriaDAO.create(subcategoria);
            return "/Subcategoria/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public List<Subcategoria> getSubcategorias() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return subcategoriaDAO.getSubcategorias(empresa);
    }

    public List<Categoria> getCategorias() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return categoriaDAO.getCategorias(empresa);
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String update() {
        try {
            subcategoriaDAO.update(subcategoria);
            return "/Subcategoria/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Subcategoria subcategoria) {
        subcategoria.setBorrado(true);
        subcategoriaDAO.update(subcategoria);

    }

}
