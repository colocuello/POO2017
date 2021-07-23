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
import ar.edu.unnoba.poo2017.model.Categoria;
import ar.edu.unnoba.poo2017.model.Empresa;
import ar.edu.unnoba.poo2017.model.Gasto;
import ar.edu.unnoba.poo2017.model.Periodo;
import ar.edu.unnoba.poo2017.model.Presupuesto;
import ar.edu.unnoba.poo2017.model.Subcategoria;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author gabriel
 */
@ViewScoped
@Named
public class GraficoBacking implements Serializable {

    @Inject
    private SessionBacking sessionBacking;
    
    @EJB
    private CategoriaDAO categoriaDAO;
    @EJB
    private PeriodoDAO periodoDAO;
    @EJB
    private PresupuestoDAO presupuestoDAO;
    @EJB
    private GastoDAO gastoDAO;

    private Presupuesto presupuesto;
    private Gasto gasto;
    private Subcategoria subcategoria;
    private Periodo periodo;
    private Categoria categoria;

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<Periodo> getPeriodos() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        return periodoDAO.getPeriodos(empresa);
    }

    private BarChartModel modeloBarras;
    private float mayorY;
    private float mayorY1;

    private LineChartModel lineModel2;

    @PostConstruct
    public void init() {
        this.gasto = new Gasto();
        this.presupuesto = new Presupuesto();
        this.subcategoria = new Subcategoria();
        this.periodo = new Periodo();
        this.categoria = new Categoria();
        createAnimatedModels();
        createLineModels();
    }

    public float getMayorY() {
        return mayorY;
    }

    public void setMayorY(float mayorY) {
        this.mayorY = mayorY;
    }

    public float getMayorY1() {
        return mayorY1;
    }

    public void setMayorY1(float mayorY1) {
        this.mayorY1 = mayorY1;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }

    private void createLineModels() {
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Desvio");
        lineModel2.setLegendPosition("e");
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Categoria"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Monto");
        yAxis.setMin(0);
        yAxis.setMax(this.mayorY + 500);
    }

    private LineChartModel initCategoryModel() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        
        float gastoMax = 0;
        float presupuestoMax = 0;
        LineChartModel model = new LineChartModel();

        List<Categoria> categorias = categoriaDAO.getCategorias(empresa);
        ChartSeries gastos = new ChartSeries();
        gastos.setLabel("Gastos");

        for (Categoria c : categorias) {
            float sum1 = gastoDAO.gastoCatTotal(c, empresa);
            gastos.set(c.getNombre(), sum1);
            if (sum1 > gastoMax) {
                gastoMax = sum1;
            }
        }

        ChartSeries presupuestos = new ChartSeries();
        presupuestos.setLabel("Presupuestos");

        for (Categoria c : categorias) {
            float sum2 = presupuestoDAO.presupuestoCatTotal(c, empresa);
            presupuestos.set(c.getNombre(), sum2);
            if (sum2 > presupuestoMax) {
                presupuestoMax = sum2;
            }
        }

        if (gastoMax > presupuestoMax) {
            setMayorY(gastoMax);
        } else {
            setMayorY(presupuestoMax);
        }

        model.addSeries(gastos);
        model.addSeries(presupuestos);

        return model;
    }

    /////////////////////////////BARRAS/////////////////////////////
    public BarChartModel getModeloBarras() {
        return modeloBarras;
    }

    public void setModeloBarras(BarChartModel modeloBarras) {
        this.modeloBarras = modeloBarras;
    }

    public void createAnimatedModels() {

        modeloBarras = initBarModel();
        modeloBarras.setTitle("Desvio");
        modeloBarras.setAnimate(true);
        modeloBarras.setLegendPosition("ne");
        Axis yAxis = modeloBarras.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Monto");
        yAxis.setMax(this.mayorY1 + 500);
    }

    private BarChartModel initBarModel() {
        Empresa empresa = sessionBacking.getUsuario().getEmpresa();
        
        float gastoMax = 0;
        float presupuestoMax = 0;

        BarChartModel model = new BarChartModel();

        List<Categoria> listC = categoriaDAO.getCategorias(empresa);

        ChartSeries gastos = new ChartSeries();
        gastos.setLabel("Gastos");

        ChartSeries presupuestos = new ChartSeries();
        presupuestos.setLabel("Presupuestos");

        for (Categoria c : listC) {
            float sum1 = gastoDAO.gastoPeriodoCatT(periodo.getDesde(), periodo.getHasta(), c, empresa);
            gastos.set(c.getNombre(), sum1);
            if (sum1 > gastoMax) {
                gastoMax = sum1;
            }

            float pr = presupuestoDAO.presupuestoCatT(periodo, c, empresa); //presupueto asignado a este periodo y categoria
            presupuestos.set(c.getNombre(), pr);
            if (pr > presupuestoMax) {
                presupuestoMax = pr;
            }
        }

        if (gastoMax > presupuestoMax) {
            setMayorY1(gastoMax);
        } else {
            setMayorY1(presupuestoMax);
        }

        model.addSeries(gastos);
        model.addSeries(presupuestos);

        return model;
    }

}
