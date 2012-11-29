package org.Nicon.Personal.LibCore.Obj;

/**
 * 
 * @author frederick
 */
public class NiconNotes{
    
  private String titulo;
  private String descripcion;
  private String fecha_creacion;
  private int codigo;
  private int Code_group;

  /**
   * 
   * @param titulo
   * @param descripcion
   * @param fecha
   */
  public NiconNotes(String titulo, String descripcion, String fecha){
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fecha_creacion = fecha;
  }

  /**
   * 
   * @param codigo
   * @param titulo
   * @param descripcion
   * @param fecha
   */
  public NiconNotes(int codigo, String titulo, String descripcion, String fecha) {
    this.codigo = codigo;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fecha_creacion = fecha;
  }
  
  /**
   * 
   */
  public NiconNotes(){
      
  }

  /**
   * 
   * @param descripcion
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * 
   * @param fecha_creacion
   */
  public void setFecha_creacion(String fecha_creacion) {
    this.fecha_creacion = fecha_creacion;
  }

  /**
   * 
   * @param titulo
   */
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  /**
   * 
   * @return
   */
  public int getcodigo() {
    return this.codigo;
  }

  /**
   * 
   * @return
   */
  public String getDescripcion() {
    return this.descripcion;
  }

  /**
   * 
   * @return
   */
  public String getFecha_creacion() {
    return this.fecha_creacion;
  }

  /**
   * 
   * @return
   */
  public String getTitulo() {
    return this.titulo;
  }
  
  /**
   * 
   * @return
   */
  public String toStringExport(){
      return "Codigo :"+getcodigo()+"\n"
           + "Titulo:  "+getTitulo()+"\n"
           + "Nota:    "+getDescripcion()+"\n"
           + "fecha creación: "+getFecha_creacion();
  }
}