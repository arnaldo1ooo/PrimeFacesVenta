package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import dao.PersonaDAO;
import model.Persona;

@ManagedBean
@ViewScoped
public class PersonaBean {
	// Inicializar variables
	private Persona persona = new Persona();
	private List<Persona> lstPersonas;
	private String accion;

	// Getters y Setters
	public Persona getPersona() {
		return persona;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(List<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	// Metodos
	public void listar() throws Exception {
		System.out.println("Se ejecuto Metodo Listar");
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			lstPersonas = dao.listar();

			/*
			 * // Imprimir Personas for (Persona i : lstPersonas) {
			 * System.out.println("Nombre " + i.getNombre() + "    Sexo " + i.getSexo()); }
			 */

		} catch (Exception e) {
			throw e;
		}

	}

	public void leerID(Persona per) throws Exception {
		System.out.println("Se ejecuto Metodo LeerID");
		PersonaDAO dao;
		Persona temp;

		try {
			dao = new PersonaDAO();
			dao.leerID(per);
			temp = dao.leerID(per);

			if (temp != null) {
				this.persona = temp;
				this.accion = "Modificar";
			}

		} catch (Exception e) {
			throw e;
		}
	}

	public void operar() throws Exception {
		switch (accion) {
		
		case "Registrar":
			this.registrar();
			this.limpiar();
			break;

		case "Modificar":
			this.modificar();
			this.limpiar();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value switch: " + accion);
		}
	}

	private void registrar() throws Exception {
		System.out.println("Se ejecuto Metodo Registrar");
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.registrar(persona);
			this.listar();
		} catch (Exception e) {
			throw e;
		}
	}

	private void modificar() throws Exception {
		System.out.println("Se ejecuto Metodo Modificar");
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.modificar(persona);
			this.listar();
		} catch (Exception e) {
			throw e;
		}
	}

	public void eliminarID(Persona per) throws Exception {
		System.out.println("Se ejecuto Metodo EliminarID");
		PersonaDAO dao;

		try {
			dao = new PersonaDAO();
			dao.eliminarID(per);
			this.listar();

		} catch (Exception e) {
			throw e;
		}
	}
	
	public void limpiar() {
		this.persona.setCodigo(0);
		this.persona.setNombre("");
		this.persona.setSexo("");
		
		System.out.println("Se limpio campos");
		
	}
}
