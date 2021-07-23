/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public List<Usuario> getUsuarios() {
        Query query = em.createNamedQuery("usuario.disponibles");
        return query.getResultList();
    }

    public List<Usuario> getUsuariosPorEmpresa(Empresa empresa) {
        Query query = em.createNamedQuery("usuario.disponibles.por_empresas");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }

    public Usuario getUsuario(String email, String password) {
        Query query = em.createNamedQuery("usuario.por_email_y_password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<Usuario> usuarios = query.getResultList();
        if (usuarios.isEmpty()) {
            return null;
        } else {
            return usuarios.get(0);
        }
    }
}
