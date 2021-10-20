package co.edu.unbosque.tiendavirtualcuatro.frontend.model;

/**
 * Representa un proveedor que abastece productos a la tienda.
 * 
 * @author
 */
public class ProveedorVO {

  /**
   * Número de Identificación Tributaria del proveedor.
   */
  private long nit;

  /**
   * Nombre del proveedor.
   */
  private String nombre;

  /**
   * Dirección del proveedor.
   */
  private String direccion;

  /**
   * Ciudad del proveedor.
   */
  private String ciudad;

  /**
   * Número de teléfono del proveedor.
   */
  private String telefono;

  /**
   * Crea un nuevo proveedor.
   */
  public ProveedorVO() {
    super();

  }

  /**
   * Crea un nuevo proveedor con los datos especificados.
   * 
   * @param nit       Número de Identificación Tributaria del proveedor.
   * @param nombre    Nombre del proveedor.
   * @param direccion Dirección del proveedor.
   * @param ciudad    Ciudad del proveedor.
   * @param telefono  Número de teléfono del proveedor.
   */
  public ProveedorVO(long nit, String nombre, String direccion, String ciudad,
      String telefono) {
    super();
    this.nit = nit;
    this.nombre = nombre;
    this.direccion = direccion;
    this.ciudad = ciudad;
    this.telefono = telefono;
  }

  /**
   * Obtiene el número de Identificación Tributaria del proveedor.
   * 
   * @return Número de Identificación Tributaria del proveedor.
   */
  public long getNit() {
    return this.nit;
  }

  /**
   * Establece el número de Identificación Tributaria del proveedor.
   * 
   * @param nit Número de Identificación Tributaria del proveedor.
   */
  public void setNit(long nit) {
    this.nit = nit;
  }

  /**
   * Obtiene el nombre del proveedor.
   * 
   * @return Nombre del proveedor.
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Establece el nombre del proveedor.
   * 
   * @param nombre Nombre del proveedor.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Obtiene la dirección del proveedor.
   * 
   * @return Dirección del proveedor.
   */
  public String getDireccion() {
    return this.direccion;
  }

  /**
   * Establece la dirección del proveedor.
   * 
   * @param direccion Dirección del proveedor.
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /**
   * Obtiene la ciudad del proveedor.
   * 
   * @return Ciudad del proveedor.
   */
  public String getCiudad() {
    return this.ciudad;
  }

  /**
   * Establece la ciudad del proveedor
   * 
   * @param ciudad Ciudad del proveedor
   */
  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  /**
   * Obtiene el número de teléfono del proveedor.
   * 
   * @return Número de teléfono del proveedor.
   */
  public String getTelefono() {
    return this.telefono;
  }

  /**
   * Establece el número de teléfono del proveedor.
   * 
   * @param telefono Número de teléfono del proveedor.
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
