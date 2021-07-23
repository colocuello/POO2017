/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Subcategoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
@Stateless
public class SubcategoriaDAO extends AbstractDAO<Subcategoria> {

    public SubcategoriaDAO() {
        super(Subcategoria.class);
    }

    public List<Subcategoria> getSubcategorias(Empresa empresa) {
        Query query = em.createNamedQuery("subcategorias.disponibles");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }

}
