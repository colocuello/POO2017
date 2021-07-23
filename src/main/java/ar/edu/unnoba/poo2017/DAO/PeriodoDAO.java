/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.model.Presupuesto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author gabriel
 */
@Stateless
public class PeriodoDAO extends AbstractDAO<Periodo>{
    public PeriodoDAO(){
        super(Periodo.class);
    }
    
    public List<Periodo> getPeriodos(Empresa empresa){
        Query query = em.createNamedQuery("periodo.todos");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }
}