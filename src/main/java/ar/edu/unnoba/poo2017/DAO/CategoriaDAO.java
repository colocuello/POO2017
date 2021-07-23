/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
@Stateless
public class CategoriaDAO extends AbstractDAO<Categoria> {

    public CategoriaDAO() {
        super(Categoria.class);
    }

    public List<Categoria> getCategorias(Empresa empresa) {
        Query query = em.createNamedQuery("categorias.disponibles");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }

}
