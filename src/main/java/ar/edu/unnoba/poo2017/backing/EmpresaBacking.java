/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.EmpresaDAO;
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
 * @author Andres
 */

@Named
@ViewScoped
public class EmpresaBacking implements Serializable {
    private Empresa empresa;
    
    @PostConstruct
    public void init(){
        this.empresa = new Empresa();
    }
    
    @EJB
    private EmpresaDAO empresaDAO;
    
    //@Inject
    //private UsuarioBacking usuarioBacking;
    
    //@Inject
    //private SessionBacking sesion;
    
    public String create(){
        try{
            empresaDAO.create(empresa);
            return "/Empresa/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
    
   public List<Empresa> getEmpresas(){
        return empresaDAO.getEmpresas();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public String update(){
        try{
            empresaDAO.update(empresa);
            return "/Empresa/index?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }   
    
}
