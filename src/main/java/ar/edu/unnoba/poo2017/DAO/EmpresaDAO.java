/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Andres
 */

@Stateless
public class EmpresaDAO extends AbstractDAO<Empresa>{
    
    public EmpresaDAO() {
        super(Empresa.class);
    }
    
    public List<Empresa> getEmpresas(){
        Query query = em.createNamedQuery("empresa.disponibles");
        return query.getResultList();
    }
    
}
