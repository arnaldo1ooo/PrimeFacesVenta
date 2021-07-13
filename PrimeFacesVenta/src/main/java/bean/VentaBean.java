package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import model.Producto;
import model.Venta;
import model.VentaDetalle;

@ManagedBean
@ViewScoped
public class VentaBean {
	private Venta venta = new Venta();
	private Producto producto = new Producto();
	private int cantidad;
	private List<VentaDetalle> lista = new ArrayList();
	
	
	//Getters y Setters
	public List<VentaDetalle> getLista() {
		return lista;
	}

	public void setLista(List<VentaDetalle> lista) {
		this.lista = lista;
	}
	
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	
	//Metodos
	public void agregar() {
		VentaDetalle det = new VentaDetalle();
		det.setCantidad(cantidad);
		det.setProducto(producto);
		this.lista.add(det);
	}
	
}
