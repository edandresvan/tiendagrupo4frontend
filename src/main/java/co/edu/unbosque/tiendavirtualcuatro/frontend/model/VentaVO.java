package co.edu.unbosque.tiendavirtualcuatro.frontend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.DetalleVentaVO;

public class VentaVO {

  private long codigo;

  private long cedulaCliente;

  private long cedulaUsuario;

  private double totalVenta;

  private double ivaVenta;

  private double valorVenta;

  private List<DetalleVentaVO> detalleVentas = new ArrayList<>();

  /**
   * 
   */
  public VentaVO() {
    super();
  }

  /**
   * @param codigo
   * @param cedulaCliente
   * @param cedulaUsuario
   * @param totalVenta
   * @param ivaVenta
   * @param valorVenta
   * @param detalleVentas
   */
  public VentaVO(long codigo, long cedulaCliente, long cedulaUsuario,
      double totalVenta, double ivaVenta, double valorVenta,
      List<DetalleVentaVO> detalleVentas) {
    super();
    this.codigo = codigo;
    this.cedulaCliente = cedulaCliente;
    this.cedulaUsuario = cedulaUsuario;
    this.totalVenta = totalVenta;
    this.ivaVenta = ivaVenta;
    this.valorVenta = valorVenta;
    this.detalleVentas = detalleVentas;
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
   * @return the cedulaCliente
   */
  public long getCedulaCliente() {
    return this.cedulaCliente;
  }

  /**
   * @param cedulaCliente the cedulaCliente to set
   */
  public void setCedulaCliente(long cedulaCliente) {
    this.cedulaCliente = cedulaCliente;
  }

  /**
   * @return the cedulaUsuario
   */
  public long getCedulaUsuario() {
    return this.cedulaUsuario;
  }

  /**
   * @param cedulaUsuario the cedulaUsuario to set
   */
  public void setCedulaUsuario(long cedulaUsuario) {
    this.cedulaUsuario = cedulaUsuario;
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
   * @return the ivaVenta
   */
  public double getIvaVenta() {
    return this.ivaVenta;
  }

  /**
   * @param ivaVenta the ivaVenta to set
   */
  public void setIvaVenta(double ivaVenta) {
    this.ivaVenta = ivaVenta;
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

  /**
   * @return the detalleVentas
   */
  public List<DetalleVentaVO> getDetalleVentas() {
    return this.detalleVentas;
  }

  /**
   * @param detalleVentas the detalleVentas to set
   */
  public void setDetalleVentas(List<DetalleVentaVO> detalleVentas) {
    this.detalleVentas = detalleVentas;
  }

}