package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	public void listar(boolean updateVistaTabla) throws Exception {
		PersonaDAO dao;
		try {
			if (!updateVistaTabla) {
				if (!isPostBack()) {
					dao = new PersonaDAO();
					lstPersonas = dao.listar();
				}
			} else { //Update tabla despues de abm
				dao = new PersonaDAO();
				lstPersonas = dao.listar();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public void leerID(Persona per) throws Exception {
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
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.registrar(persona);
			this.listar(true);
		} catch (Exception e) {
			throw e;
		}
	}

	private void modificar() throws Exception {
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.modificar(persona);
			this.listar(true);
		} catch (Exception e) {
			throw e;
		}
	}

	public void eliminarID(Persona per) throws Exception {
		PersonaDAO dao;

		try {
			dao = new PersonaDAO();
			dao.eliminarID(per);
			this.listar(true);

		} catch (Exception e) {
			throw e;
		}
	}

	public void limpiar() {
		this.persona.setCodigo(0);
		this.persona.setNombre("");
		this.persona.setSexo("");
	}

	private boolean isPostBack() { // Si la peticion es a la misma pagina postback=true
		return FacesContext.getCurrentInstance().isPostback();
	}
}
