package model;

public class Producto {
	
	//Inicializar variables
	private int codigo;
	private String nombre;
	private double precio;
	
	@Override
	public String toString() {
		return "Producto{" + "codigo=" + codigo + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Producto other = (Producto) obj;
		if (this.codigo != other.codigo) {
			return false;
		}
		return true;
	}
	
	
	//Getters y Setters
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}	
}
