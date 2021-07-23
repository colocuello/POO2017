/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.PeriodoDAO;
import ar.edu.unnoba.poo2017.backing.PeriodoBacking;
import ar.edu.unnoba.poo2017.DAO.PresupuestoDAO;
import ar.edu.unnoba.poo2017.DAO.SubcategoriaDAO;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jpgm
 */
@Named
@ViewScoped
public class PresupuestoBacking implements Serializable {

    private Presupuesto presupuesto;
          
    @Inject
    private SessionBacking sessionBacking;

    @PostConstruct
    public void init() {
        this.presupuesto = new Presupuesto();
    }

    @EJB
    private PresupuestoDAO presupuestoDao;

    @EJB
    private SubcategoriaDAO subcategoriaDAO;

    @EJB
    private PeriodoDAO periodoDAO;

    public PeriodoDAO getPeriodoDAO() {
        return periodoDAO;
    }

    public void setPeriodoDAO(PeriodoDAO periodoDAO) {
        this.periodoDAO = periodoDAO;
    }

    public List<Periodo> getPeriodos() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return periodoDAO.getPeriodos(empresa);
    }

    public List<Subcategoria> getSubcategorias() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return subcategoriaDAO.getSubcategorias(empresa);
    }

    public List<Presupuesto> getPresupuestos() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return presupuestoDao.getPresupuestos(empresa);
    }

    public String create() {
        try {
            presupuesto.setEmpresa(sessionBacking.getUsuario().getEmpresa());
            presupuestoDao.create(presupuesto);
            return "/Presupuesto/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String update() {
        try {
            presupuestoDao.update(presupuesto);
            return "/Presupuesto/index?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Presupuesto presupuesto) {
        presupuestoDao.delete(presupuesto);
    }

}
