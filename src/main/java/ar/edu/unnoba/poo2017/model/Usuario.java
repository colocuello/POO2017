/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.poo2017.model; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gabriel
 */
@Entity
@Table(name="usuarios")
@NamedQueries({
    @NamedQuery(name = "usuario.disponibles",
           query = "Select u From Usuario u"),
    @NamedQuery(name = "usuario.disponibles.por_empresas",
           query = "Select u From Usuario u where u.empresa = :empresa"),
    @NamedQuery(name = "usuario.por_email_y_password",
           query = "Select u From Usuario u where u.email = :email and u.password = :password and u.activo = true") 
})
public class Usuario extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
        
    private String username;
    private String email;
    private String password;
    private boolean activo;
    private String apellido;
  
    public Usuario() {
    }
    
    public Usuario(String email, String password, boolean activo, String apellido, String nombre, Empresa empresa) {
        super();
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.apellido = apellido;
        this.username = nombre;
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombre) {
        this.username = nombre;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
     
}
