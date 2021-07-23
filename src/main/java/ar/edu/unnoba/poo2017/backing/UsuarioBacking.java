/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

//import ar.edu.unnoba.poo2017.DAO.PerfilDAO;
import ar.edu.unnoba.poo2017.DAO.EmpresaDAO;
import ar.edu.unnoba.poo2017.DAO.UsuarioDAO;
import ar.edu.unnoba.poo2017.model.Empresa;
//import ar.edu.unnoba.poo2017.model.Perfil;
import ar.edu.unnoba.poo2017.model.Usuario;
import java.io.Serializable;
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
public class UsuarioBacking implements Serializable {

    private Usuario usuario;

    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }

    @EJB
    private UsuarioDAO usuarioDao;

    @EJB
    private EmpresaDAO empresaDao;

    /*public List<Usuario> getUsuarios(){
        Empresa sesionEmpresa = sessionBacking.getUsuario().getEmpresa();
        return usuarioDao.getUsuarios(sesionEmpresa);
    }*/
    
    public List<Usuario> getUsuarios() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        if (empresa == null) {
            return usuarioDao.getUsuarios();
        } else {
            //return usuarioDao.findAllNamedBy("usuario.disponibles", "empresa", empresa.getId());
            return usuarioDao.getUsuariosPorEmpresa(empresa);
        }
    }

    public List<Empresa> getEmpresas() {
        return empresaDao.getEmpresas();
    }

    public String create() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        try {
            if (empresa != null) {
                usuario.setEmpresa(sessionBacking.getUsuario().getEmpresa());
                usuario.setActivo(true);
                usuarioDao.create(usuario);
            } else {
                usuario.setActivo(true);
                usuarioDao.create(usuario);
            }
            return "/Usuario/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }

    }

    public String update() {
        try {
            usuarioDao.update(usuario);
            return "/Usuario/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Usuario usuario) {
        if (!sessionBacking.getUsuario().equals(usuario)) {
            usuario.setActivo(false);
            usuarioDao.update(usuario);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("No puede borrar este usuario");
            context.addMessage(null, message);
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
