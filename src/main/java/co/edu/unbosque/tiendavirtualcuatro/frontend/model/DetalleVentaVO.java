package co.edu.unbosque.tiendavirtualcuatro.frontend.model;

public class DetalleVentaVO {

  private long codigo;

  private long codigoVenta;

  private long codigoProducto;

  private int cantidadProducto;

  private double totalVenta;

  private double valorIva;

  private double valorVenta;

  /**
   * 
   */
  public DetalleVentaVO() {
    super();
  }

  /**
   * @param codigo
   * @param codigoVenta
   * @param codigoProducto
   * @param cantidadProducto
   * @param totalVenta
   * @param valorIva
   * @param valorVenta
   */
  public DetalleVentaVO(long codigo, long codigoVenta, long codigoProducto,
      int cantidadProducto, double totalVenta, double valorIva,
      double valorVenta) {
    super();
    this.codigo = codigo;
    this.codigoVenta = codigoVenta;
    this.codigoProducto = codigoProducto;
    this.cantidadProducto = cantidadProducto;
    this.totalVenta = totalVenta;
    this.valorIva = valorIva;
    this.valorVenta = valorVenta;
  }

  /**
   * @return the codigo
   */
  public long getCodigo() {
    return this.codigo;
  }

  /**
   * @param codigo the codigo to set
   */
  public void setCodigo(long codigo) {
    this.codigo = codigo;
  }

  /**
   * @return the codigoVenta
   */
  public long getCodigoVenta() {
    return this.codigoVenta;
  }

  /**
   * @param codigoVenta the codigoVenta to set
   */
  public void setCodigoVenta(long codigoVenta) {
    this.codigoVenta = codigoVenta;
  }

  /**
   * @return the codigoProducto
   */
  public long getCodigoProducto() {
    return this.codigoProducto;
  }

  /**
   * @param codigoProducto the codigoProducto to set
   */
  public void setCodigoProducto(long codigoProducto) {
    this.codigoProducto = codigoProducto;
  }

  /**
   * @return the cantidadProducto
   */
  public int getCantidadProducto() {
    return this.cantidadProducto;
  }

  /**
   * @param cantidadProducto the cantidadProducto to set
   */
  public void setCantidadProducto(int cantidadProducto) {
    this.cantidadProducto = cantidadProducto;
  }

  /**
   * @return the totalVenta
   */
  public double getTotalVenta() {
    return this.totalVenta;
  }

  /**
   * @param totalVenta the totalVenta to set
   */
  public void setTotalVenta(double totalVenta) {
    this.totalVenta = totalVenta;
  }

  /**
   * @return the valorIva
   */
  public double getValorIva() {
    return this.valorIva;
  }

  /**
   * @param valorIva the valorIva to set
   */
  public void setValorIva(double valorIva) {
    this.valorIva = valorIva;
  }

  /**
   * @return the valorVenta
   */
  public double getValorVenta() {
    return this.valorVenta;
  }

  /**
   * @param valorVenta the valorVenta to set
   */
  public void setValorVenta(double valorVenta) {
    this.valorVenta = valorVenta;
  }

}