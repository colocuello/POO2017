/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.converter;


import ar.edu.unnoba.poo2017.model.Presupuesto;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author gabriel
 */
@FacesConverter(forClass=Presupuesto.class)
public class PresupuestoConverter extends AbstractConverter<Presupuesto>{

    @Override
    public String getDAOName() {
        return "PresupuestoDAO";
    }
    
}
