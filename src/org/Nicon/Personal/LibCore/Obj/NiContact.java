package org.Nicon.Personal.LibCore.Obj;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Esta es la clase que define el modelo de datos de un contacto NiconPersonal, denominado
 * NiContact posee los metodos de acceso y modificacion a datos encapsulados
 * @author Frederick Adolfo Salazar Sanchez
 * @version 1
 * 
 */
public class NiContact{
    
  private int Codigo;
  
  private String Nombres;
  private String Apellidos;
  private String Apodo;
  private String Direccion;
  private String ciudad;
  private String celular;
  private String fijo;
  private String email;
  private String photo;
  private String fecha_nac;
  private String Grupo;

  /**
   * Este es el metodo constructor base para crear un objeto NiContact y almacenarlo en el
   * Backend sqlite.
   * @param Codigo
   * @param Nombres
   * @param Apellidos
   * @param Apodo
   * @param Direccion
   * @param ciudad
   * @param celular
   * @param fijo
   * @param email
   * @param fecha_nac
   * @param Grupo
   * 
   * @author Frederick Adolfo Salazar Sanchez
   */
  
  public NiContact(int Codigo, String Nombres, String Apellidos, String Apodo, String Direccion, String ciudad, String celular, String fijo, String email, String fecha_nac, String Grupo) {
    this.Codigo = Codigo;
    this.Nombres = Nombres;
    this.Apellidos = Apellidos;
    this.Apodo = Apodo;
    this.Direccion = Direccion;
    this.ciudad = ciudad;
    this.celular = celular;
    this.fijo = fijo;
    this.email = email;
    this.fecha_nac = fecha_nac;
    this.Grupo = Grupo;
  }

  /**
   * constructor generico que no recibe parametros, por ende será un NiContact null
   * @author Frederick Adolfo Salazar Sanchez
   * 
   */
  public NiContact(){
  }

  /**
   * 
   * @param Apellidos
   */
  public void setApellidos(String Apellidos) {
    this.Apellidos = Apellidos;
  }

  /**
   * 
   * @param Apodo
   */
  public void setApodo(String Apodo) {
    this.Apodo = Apodo;
  }

  /**
   * 
   * @param Codigo
   */
  public void setCodigo(int Codigo) {
    this.Codigo = Codigo;
  }

  /**
   * 
   * @param Direccion
   */
  public void setDireccion(String Direccion) {
    this.Direccion = Direccion;
  }

  /**
   * 
   * @param Nombres
   */
  public void setNombres(String Nombres) {
    this.Nombres = Nombres;
  }

  /**
   * 
   * @param celular
   */
  public void setCelular(String celular) {
    this.celular = celular;
  }

  /**
   * 
   * @param ciudad
   */
  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  /**
   * 
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * 
   * @param fecha_nac
   */
  public void setFecha_nac(String fecha_nac) {
    this.fecha_nac = fecha_nac;
  }

  /**
   * 
   * @param fijo
   */
  public void setFijo(String fijo) {
    this.fijo = fijo;
  }

  /**
   * 
   * @return String Apellidos de contacto
   */
  public String getApellidos() {
    return this.Apellidos;
  }

  /**
   * 
   * @return String Apodo de contacto
   */
  public String getApodo() {
    return this.Apodo;
  }

  /**
   * 
   * @return int codigo de contacto
   */
  public int getCodigo() {
    return this.Codigo;
  }

  /**
   * 
   * @return
   */
  public String getDireccion() {
    return this.Direccion;
  }

  /**
   * 
   * @return
   */
  public String getNombres() {
    return this.Nombres;
  }

  /**
   * 
   * @return
   */
  public String getCelular() {
    return this.celular;
  }

  /**
   * 
   * @return
   */
  public String getCiudad() {
    return this.ciudad;
  }

  /**
   * 
   * @return
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * 
   * @return
   */
  public String getFecha_nac() {
    return this.fecha_nac;
  }

  /**
   * 
   * @return
   */
  public String getFijo() {
    return this.fijo;
  }

  /**
   * 
   * @param Grupo
   */
  public void setGrupo(String Grupo) {
    this.Grupo = Grupo;
  }

  /**
   * 
   * @return
   */
  public String getGrupo() {
    return this.Grupo;
  }  

  /**
   * 
   * @return
   */
  @Override
    public String toString() {
        return "Datos del Contacto :\n\n" + "ContactID=  " + Codigo + "\nNombres:  " + Nombres + "\nApellidos:  " + Apellidos + "\nApodo:  " + Apodo + "\nPertenece a:  " + Grupo;
    }
    
  /**
   * 
   * @return
   */
  public String toStringExport(){
        return "ContactID:  "+Codigo+",  Nombres:  "+Nombres +" "+Apellidos+", Alias:  "+Apodo+", Ciudad: "+ciudad+". Dirección:  "+Direccion+", Celular:  "+this.celular+",  Fijo:  "+this.fijo+", Email:  "+this.email+", Fecha Nacimiento:  "+this.fecha_nac;
    } 
  
}
  