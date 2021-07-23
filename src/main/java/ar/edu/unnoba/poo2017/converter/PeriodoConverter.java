/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.converter;

import ar.edu.unnoba.poo2017.model.Periodo;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author gabriel
 */
@FacesConverter(forClass=Periodo.class)
public class PeriodoConverter extends AbstractConverter<Periodo>{

    @Override
    public String getDAOName() {
        return "PeriodoDAO";
    }
    
}