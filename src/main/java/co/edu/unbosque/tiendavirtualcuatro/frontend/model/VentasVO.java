package co.edu.unbosque.tiendavirtualcuatro.frontend.model;

public class VentasVO {
	
	  private long codigo_venta;
	  private long cedula_cliente;
	  private long cedula_usuario;
	  private double total_venta;
	  private double iva_venta;	
	  private double valor_venta;
	  
	public VentasVO() {
		super();
	}

	public VentasVO(long codigo_venta, long cedula_cliente, long cedula_usuario, double total_venta, double iva_venta,
			double valor_venta) {
		super();
		this.codigo_venta = codigo_venta;
		this.cedula_cliente = cedula_cliente;
		this.cedula_usuario = cedula_usuario;
		this.total_venta = total_venta;
		this.iva_venta = iva_venta;
		this.valor_venta = valor_venta;
	}

	public long getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public long getCedula_cliente() {
		return cedula_cliente;
	}

	public void setCedula_cliente(long cedula_cliente) {
		this.cedula_cliente = cedula_cliente;
	}

	public long getCedula_usuario() {
		return cedula_usuario;
	}

	public void setCedula_usuario(long cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
	}

	public double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(double total_venta) {
		this.total_venta = total_venta;
	}

	public double getIva_venta() {
		return iva_venta;
	}

	public void setIva_venta(double iva_venta) {
		this.iva_venta = iva_venta;
	}

	public double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(double valor_venta) {
		this.valor_venta = valor_venta;
	}

	@Override
	public String toString() {
		return "VentasVO [codigo_venta=" + codigo_venta + ", cedula_cliente=" + cedula_cliente + ", cedula_usuario="
				+ cedula_usuario + ", total_venta=" + total_venta + ", iva_venta=" + iva_venta + ", valor_venta="
				+ valor_venta + "]";
	}	  
}
