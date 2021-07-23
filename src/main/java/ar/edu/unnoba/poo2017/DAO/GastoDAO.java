/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.DAO;

import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.model.Subcategoria;
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
public class GastoDAO extends AbstractDAO<Gasto> {

    public GastoDAO() {
        super(Gasto.class);
    }

    public List<Gasto> getGastos(Empresa empresa) {
        Query query = em.createNamedQuery("gasto.todos");
        query.setParameter("empresa", empresa);
        return query.getResultList();
    }

    public List<Gasto> gastoPeriodoSub(Date desde, Date hasta, Subcategoria subcategoria, Empresa empresa) {
        Query query = em.createNamedQuery("gasto.periodo.sub");
        query.setParameter("desde", desde);
        query.setParameter("hasta", hasta);
        query.setParameter("subcategoria", subcategoria);
        query.setParameter("empresa", empresa);
        List<Gasto> gastos = query.getResultList();
        if (gastos.isEmpty()) {
            return null;
        } else {
            return gastos;
        }
    }

    public float gastoPeriodoSubT(Date desde, Date hasta, Subcategoria subcategoria, Empresa empresa) {
        Query query = em.createNamedQuery("gasto.periodo.sub");
        query.setParameter("desde", desde);
        query.setParameter("hasta", hasta);
        query.setParameter("subcategoria", subcategoria);
        query.setParameter("empresa", empresa);
        List<Gasto> gastos = query.getResultList();
        float suma = 0;
        for (Gasto g : gastos) {
            suma = suma + g.getImporte();
        }
        return suma;
    }

    public List<Gasto> gastoPeriodoCat(Date desde, Date hasta, Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("gasto.periodo.cat");
        query.setParameter("desde", desde);
        query.setParameter("hasta", hasta);
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Gasto> gastos = query.getResultList();
        if (gastos.isEmpty()) {
            return null;
        } else {
            return gastos;
        }
    }

    public float gastoPeriodoCatT(Date desde, Date hasta, Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("gasto.periodo.cat");
        query.setParameter("desde", desde);
        query.setParameter("hasta", hasta);
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Gasto> gastos = query.getResultList();
        float suma = 0;
        for (Gasto g : gastos) {
            suma = suma + g.getImporte();
        }
        return suma;
    }

    public double gastoMaximo(List<Gasto> listG) {
        double max = 0;
        for (Gasto g : listG) {
            if (g.getImporte() > max) {
                max = g.getImporte();
            }
        }
        return max;
    }

    public float gastoCatTotal(Categoria categoria, Empresa empresa) {
        Query query = em.createNamedQuery("gasto.cat");
        query.setParameter("categoria", categoria);
        query.setParameter("empresa", empresa);
        List<Gasto> gastos = query.getResultList();
        float suma = 0;
        for (Gasto g : gastos) {
            suma = suma + g.getImporte();
        }
        return suma;
    }
}
