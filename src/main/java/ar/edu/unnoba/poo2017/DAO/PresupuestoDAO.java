/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import ar.edu.unnoba.poo2017.model.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author jpgm
 */
@Stateless
public class PresupuestoDAO extends AbstractDAO<Presupuesto> {

    public PresupuestoDAO() {
        super(Presupuesto.class);
    }

    public List<Presupuesto> getPresupuestos(Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.todos");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }

    public List<Presupuesto> presupuestoPeriodoSub(Periodo periodo, Subcategoria subcategoria, Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.periodo.sub");
        query.setParameter("periodo", periodo);
        query.setParameter("subcategoria", subcategoria);
        query.setParameter("empresa", empresa);
        List<Presupuesto> presupuestos = query.getResultList();
        if (presupuestos.isEmpty()) {
            return null;
        } else {
            return presupuestos;
        }

    }

    public float presupuestoT(Periodo periodo, Subcategoria subcategoria, Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.periodo.sub");
        query.setParameter("periodo", periodo);
        query.setParameter("subcategoria", subcategoria);
        query.setParameter("empresa", empresa);
        List<Presupuesto> presupuestos = query.getResultList();

        float suma = 0;
        for (Presupuesto p : presupuestos) {
            suma = suma + p.getMonto();
        }
        return suma;

    }

    public List<Presupuesto> presupuestoPeriodoCat(Periodo periodo, Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.periodo.cat");
        query.setParameter("periodo", periodo);
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Presupuesto> presupuestos = query.getResultList();
        if (presupuestos.isEmpty()) {
            return null;
        } else {
            return presupuestos;
        }
    }

    public float presupuestoCatT(Periodo periodo, Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.periodo.cat");
        query.setParameter("periodo", periodo);
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Presupuesto> presupuestos = query.getResultList();

        float suma = 0;
        for (Presupuesto p : presupuestos) {
            suma = suma + p.getMonto();
        }
        return suma;

    }

    public List<Presupuesto> presupuestoCat(Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.cat");
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Presupuesto> presupuestos = query.getResultList();
        if (presupuestos.isEmpty()) {
            return null;
        } else {
            return presupuestos;
        }
    }

    public float presupuestoCatTotal(Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("presupuesto.cat");
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Presupuesto> presupuestos = query.getResultList();

        float suma = 0;
        for (Presupuesto p : presupuestos) {
            suma = suma + p.getMonto();
        }
        return suma;
    }

}
