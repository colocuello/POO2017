/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.backing;

import ar.edu.unnoba.poo2017.DAO.CategoriaDAO;
import ar.edu.unnoba.poo2017.DAO.GastoDAO;
import ar.edu.unnoba.poo2017.DAO.PeriodoDAO;
import ar.edu.unnoba.poo2017.DAO.PresupuestoDAO;
import ar.edu.unnoba.poo2017.DAO.SubcategoriaDAO;
import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.dom4j.DocumentException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;

/**
 *
 * @author gabriel
 */
@ViewScoped
@Named
public class ReporteBacking implements Serializable {

    private Presupuesto presupuesto;
    private Gasto gasto;
    private Subcategoria subcategoria;
    private Periodo periodo;
    private Categoria categoria;

    private ExcelOptions excelOpt;

    private PDFOptions pdfOpt;

    @Inject
    private SessionBacking sessionBacking;
    @Inject
    private SubcategoriaDAO subcategoriaDAO;
    @Inject
    private CategoriaDAO categoriaDAO;
    @Inject
    private PeriodoDAO periodoDAO;
    @Inject
    private PresupuestoDAO presupuestoDAO;
    @Inject
    private GastoDAO gastoDAO;

    @PostConstruct
    public void init() {
        this.gasto = new Gasto();
        this.presupuesto = new Presupuesto();
        this.subcategoria = new Subcategoria();
        this.periodo = new Periodo();
        this.categoria = new Categoria();
    }

    public List<Periodo> getPeriodos() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return periodoDAO.getPeriodos(empresa);
    }

    public List<Subcategoria> getSubcategorias() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return subcategoriaDAO.getSubcategorias(empresa);
    }

    public List<Gasto> getGastos() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return gastoDAO.getGastos(empresa);
    }

    public List<Presupuesto> getPresupuestos() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return presupuestoDAO.getPresupuestos(empresa);
    }

    public List<Presupuesto> presupuestoPeriodoSub() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return presupuestoDAO.presupuestoPeriodoSub(this.periodo, this.subcategoria, empresa);
    }

    public float presupuestoT() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return presupuestoDAO.presupuestoT(this.periodo, this.subcategoria, empresa);
    }

    public List<Gasto> gastoPeriodoSub() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return gastoDAO.gastoPeriodoSub(this.periodo.getDesde(), this.periodo.getHasta(), subcategoria, empresa);
    }

    public float gastoPeriodoSubT() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return gastoDAO.gastoPeriodoSubT(this.periodo.getDesde(), this.periodo.getHasta(), subcategoria, empresa);
    }

    public List<Gasto> gastoPeriodoCat() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return gastoDAO.gastoPeriodoCat(this.periodo.getDesde(), this.periodo.getHasta(), categoria, empresa);
    }

    public List<Categoria> getCategorias() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return categoriaDAO.getCategorias(empresa);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }

    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }

    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException, com.lowagie.text.DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "img" + File.separator + "pdf.png";

        pdf.add(Image.getInstance(logo));
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the fileName
     */
}
