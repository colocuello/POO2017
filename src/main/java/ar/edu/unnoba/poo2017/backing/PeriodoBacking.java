/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.PeriodoDAO;
import ar.edu.unnoba.poo2017.DAO.UsuarioDAO;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import ar.edu.unnoba.poo2017.model.Usuario;
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
 * @author gabriel
 */
@Named
@ViewScoped
public class PeriodoBacking implements Serializable {
    private Periodo periodo;
    
    @Inject
    private SessionBacking sessionBacking;
    
    @PostConstruct
    public void init(){
        this.periodo = new Periodo();
    }
    
    @EJB
    private PeriodoDAO periodoDao;
    
    public List<Periodo> getPeriodos(){
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return periodoDao.getPeriodos(empresa);
    }
    
   
    
    public String create(){
        try{
            periodo.setEmpresa(sessionBacking.getUsuario().getEmpresa());
            periodoDao.create(periodo);
            return "/Periodo/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public String update(){
        try{
            periodoDao.update(periodo);
            return "/Periodo/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
    public void delete(Periodo periodo){
          periodoDao.delete(periodo);        
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
    
    
}