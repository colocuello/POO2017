/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;


import ar.edu.unnoba.poo2017.DAO.GastoDAO;
import ar.edu.unnoba.poo2017.DAO.SubcategoriaDAO;
import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jpgm
 */
@Named
@ViewScoped
public class GastoBacking implements Serializable {
    private Gasto gasto;
    
    @Inject
    private SessionBacking sessionBacking;
    
    @PostConstruct
    public void init(){
        this.gasto = new Gasto();
    }
    
    @EJB
    private GastoDAO gastoDao;
    
    @EJB
    private SubcategoriaDAO subcategoriaDAO;
    
    public List<Subcategoria> getSubcategorias(){
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return subcategoriaDAO.getSubcategorias(empresa);
    }
    
    public List<Gasto> getGastos(){
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return gastoDao.getGastos(empresa);
    }
    
    public String create(){
        try{
            gasto.setEmpresa(sessionBacking.getUsuario().getEmpresa());
            gastoDao.create(gasto);
            return "/Gasto/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }
    
    public String update(){
        try{
            gastoDao.update(gasto);
            return "/Gasto/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public void delete(Gasto gasto){
        gastoDao.delete(gasto);
    }
    
    
}
