/**
 * 
 */
package co.edu.unbosque.tiendavirtualcuatro.frontend.model;

import java.util.Map;

/**
 * Representa un producto que la tienda ofrece a sus clientes.
 * 
 * @author 
 */
public class ProductoVO {

  /**
   * Código del producto.
   */
  private long codigo;

  /**
   * Nombre del producto.
   */
  private String nombre;

  /**
   * Precio de compra del producto.
   */
  private double precioCompra;

  /**
   * Porcentaje de IVA de compra del producto.
   */
  private double ivaCompra;

  /**
   * Precio de venta del producto.
   */
  private double precioVenta;

  /**
   * NIT del proveedor de este producto.
   */
  private long nitProveedor;
  
  /**
   * Proveedor de este producto.
   */
  private ProveedorVO proveedor;

  

  /**
   * Crea un nuevo objeto Producto.
   */
  public ProductoVO() {
    super();
  }

  /**
   * Crea un nuevo objeto Producto con los valores especificados.
   * 
   * @param codigo       Código del producto.
   * @param nombre       Nombre del producto.
   * @param precioCompra Precio de compra del producto.
   * @param precioVenta  Precio de venta del producto.
   * @param ivaCompra    Porcentaje de IVA de compra del producto.
   * @param nitProveedor Proveedor del producto.
   */
  public ProductoVO(
      long codigo,
      String nombre,
      double precioCompra,
      double ivaCompra,
      double precioVenta,
      long nitProveedor) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.precioCompra = precioCompra;
    this.ivaCompra = ivaCompra;
    this.precioVenta = precioVenta;
    this.nitProveedor = nitProveedor;
  }

  /**
   * Obtiene el código del producto.
   * 
   * @return Código del producto.
   */
  public long getCodigo() {
    return this.codigo;
  }

  /**
   * Establece el código del producto.
   * 
   * @param codigo Código del producto.
   */
  public void setCodigo(long codigo) {
    this.codigo = codigo;
  }

  /**
   * Obtiene el nombre del producto.
   * 
   * @return Nombre del producto.
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Establece el nombre del producto.
   * 
   * @param nombre Nombre del producto.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Obtiene el precio de compra del producto.
   * 
   * @return precioCompra Precio de compra del producto.
   */
  public double getPrecioCompra() {
    return this.precioCompra;
  }

  /**
   * Establece el precio de compra del producto.
   * 
   * @param precioCompra Precio de compra del producto.
   */
  public void setPrecioCompra(double precioCompra) {
    this.precioCompra = precioCompra;
  }

  /**
   * Obtiene el porcentaje de IVA de compra del producto.
   * 
   * @return ivaCompra Porcentaje de IVA de compra del producto.
   */
  public double getIvaCompra() {
    return this.ivaCompra;
  }

  /**
   * Establece el porcentaje de IVA de compra del producto.
   * 
   * @param ivaCompra Porcentaje de IVA de compra del producto.
   */
  public void setIvaCompra(double ivaCompra) {
    this.ivaCompra = ivaCompra;
  }

  /**
   * Obtiene el precio de venta del producto.
   * 
   * @return precioVenta Precio de venta del producto.
   */
  public double getPrecioVenta() {
    return this.precioVenta;
  }

  /**
   * Establece el precio de venta del producto.
   * 
   * @param precioVenta Precio de venta del producto.
   */
  public void setPrecioVenta(double precioVenta) {
    this.precioVenta = precioVenta;
  }

  /**
   * Obtiene el NIT del proveedor del producto.
   * 
   * @return nitProveedor NIT del proveedor del producto.
   */
  public long getNitProveedor() {
    return this.nitProveedor;
  }

  /**
   * Establece el NIT del proveedor del producto.
   * 
   * @param nitProveedor NIT del proveedor del producto.
   */
  public void setNitProveedor(long nitProveedor) {
    this.nitProveedor = nitProveedor;
  }
  
  /**
   * Obtiene el proveedor de este producto.
   * @return proveedor Proveedor de este producto
   */
  public ProveedorVO getProveedor() {
    return this.proveedor;
  }

  /**
   * Establece el proveedor de este producto.
   * @param proveedor Proveedor de este producto
   */
  public void setProveedor(ProveedorVO proveedor) {
    this.proveedor = proveedor;
  }

}
