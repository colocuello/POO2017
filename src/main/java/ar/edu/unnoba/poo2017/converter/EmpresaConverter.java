/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.converter;

import ar.edu.unnoba.poo2017.model.Empresa;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Andres
 */

@FacesConverter(forClass=Empresa.class)
public class EmpresaConverter extends AbstractConverter<Empresa>{

    @Override
    public String getDAOName() {
        return "EmpresaDAO";
    }
    
}
