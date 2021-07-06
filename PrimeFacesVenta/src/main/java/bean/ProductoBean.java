package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.PersonaDAO;
import dao.ProductoDAO;
import model.Producto;

@ManagedBean
@ViewScoped
public class ProductoBean {
	// Inicializar variables
	private Producto producto = new Producto();
	private List<Producto> lstProductos;
	private String accion;

	// Getters y Setters
	public Producto getProducto() {
		return producto;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(List<Producto> lstProductos) {
		this.lstProductos = lstProductos;
	}

	// Metodos
	public void listar(boolean updateVistaTabla) throws Exception {
		ProductoDAO dao;
		try {
			if (!updateVistaTabla) {
				if (!isPostBack()) {
					dao = new ProductoDAO();
					lstProductos = dao.listar();
				}
			} else { //Update tabla despues de abm
				dao = new ProductoDAO();
				lstProductos = dao.listar();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public void leerID(Producto per) throws Exception {
		ProductoDAO dao;
		Producto temp;

		try {
			dao = new ProductoDAO();
			dao.leerID(per);
			temp = dao.leerID(per);

			if (temp != null) {
				this.producto = temp;
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
		ProductoDAO dao;
		try {
			dao = new ProductoDAO();
			dao.registrar(producto);
			this.listar(true);
		} catch (Exception e) {
			throw e;
		}
	}

	private void modificar() throws Exception {
		ProductoDAO dao;
		try {
			dao = new ProductoDAO();
			dao.modificar(producto);
			this.listar(true);
		} catch (Exception e) {
			throw e;
		}
	}

	public void eliminarID(Producto per) throws Exception {
		ProductoDAO dao;

		try {
			dao = new ProductoDAO();
			dao.eliminarID(per);
			this.listar(true);

		} catch (Exception e) {
			throw e;
		}
	}
	
	public void limpiar() {
		this.producto.setCodigo(0);
		this.producto.setNombre("");
		this.producto.setPrecio(0);
	}
	
	private boolean isPostBack() { //Si la peticion es a la misma pagina postback=true
		return FacesContext.getCurrentInstance().isPostback();
	}
}
