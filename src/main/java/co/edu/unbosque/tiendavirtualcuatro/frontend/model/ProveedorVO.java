package co.edu.unbosque.tiendavirtualcuatro.frontend.model;

/**
 * @author
 *
 */

public class ProveedorVO {

  private long nit;

  private String nombre;

  private String direccion;

  private String ciudad;

  private String telefono;

  /**
   * 
   */
  public ProveedorVO() {
    super();
  }

  /**
   * @param nit
   * @param nombre
   * @param direccion
   * @param ciudad
   * @param telefono
   */
  public ProveedorVO(long nit, String nombre, String direccion, String ciudad, String telefono) {
    super();
    this.nit = nit;
    this.nombre = nombre;
    this.direccion = direccion;
    this.ciudad = ciudad;
    this.telefono = telefono;
  }

  /**
   * @return the nit
   */
  public long getNit() {
    return this.nit;
  }

  /**
   * @param nit the nit to set
   */
  public void setNit(long nit) {
    this.nit = nit;
  }

  /**
   * @return the nombre
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return the direccion
   */
  public String getDireccion() {
    return this.direccion;
  }

  /**
   * @param direccion the direccion to set
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /**
   * @return the ciudad
   */
  public String getCiudad() {
    return this.ciudad;
  }

  /**
   * @param ciudad the ciudad to set
   */
  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  /**
   * @return the telefono
   */
  public String getTelefono() {
    return this.telefono;
  }

  /**
   * @param telefono the telefono to set
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
