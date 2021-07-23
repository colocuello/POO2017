/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.EmpresaDAO;
//import ar.edu.unnoba.poo2017.DAO.PerfilDAO;
import ar.edu.unnoba.poo2017.DAO.UsuarioDAO;
import ar.edu.unnoba.poo2017.model.Empresa;
//import ar.edu.unnoba.poo2017.model.Perfil;
import ar.edu.unnoba.poo2017.model.Usuario;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gabriel
 */
@SessionScoped
@Named
public class SessionBacking implements Serializable {

    private String email;
    private String password;
    private Usuario usuario;

    private Empresa empresa;

    @EJB
    private UsuarioDAO usuarioDAO;

    //@EJB
    //private EmpresaDAO empresaDAO;
    @Inject
    private transient PropertyResourceBundle bundle;

    @PostConstruct
    public void init() {
    }

    public String login() {
        this.usuario = usuarioDAO.getUsuario(this.email, this.password);
        if (usuario == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(bundle.getString("user.login.invalid"));
            context.addMessage(null, message);
            return null;
        }
        /*if(usuario.getEmpresa() == null){
            return "/admin?faces-redirect=true";
        }*/
        return "/template?faces-redirect=true";
    }

    public String logout() {
        usuario = new Usuario();
        return "/index?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*public ResourceBundle getMessages() {
        return messages;
    }

    public void setMessages(ResourceBundle messages) {
        this.messages = messages;
    }*/
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
