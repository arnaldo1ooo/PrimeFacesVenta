package model;

public class Persona {

	// Inicializar variables
	private int codigo;
	private String nombre;
	private String sexo;

	@Override
	public String toString() {
		return String.format("%s{codigo=%d}", getClass().getSimpleName(), getCodigo());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Persona other = (Persona) obj;
		if (this.codigo != other.codigo) {
			return false;
		}
		return true;
	}

	// Getters y Setters
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
