/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
/**
 *
 * @author gabriel
 * @param <T>
 */
public abstract class AbstractDAO<T> {
    final Class<T> typeParameterClass;

    @PersistenceContext(unitName = "poo2017")
    protected  EntityManager em;
    
    public AbstractDAO(Class<T> type){
        this.typeParameterClass = type;
    }
    
    public void create(T t) throws EJBException{
        em.persist(t);
    }
    
    public void update(T t) throws EJBException{
        em.merge(t);
    }
    
    public void delete(T t) throws EJBException{
        em.remove(em.contains(t) ? t : em.merge(t));
    }
    
    public T find(Long id){
        return em.find(typeParameterClass, id);
    } 
    
    
    public List<T> findAllNamedBy(String namedQuery, String parameter, Long value) {
        try {
            return em.createNamedQuery(namedQuery)
                    .setParameter(parameter, value).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
