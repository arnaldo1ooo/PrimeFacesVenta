package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Producto;

public class ProductoDAO extends DAO {

	public List<Producto> listar() throws Exception {
		List<Producto> lista;
		ResultSet rs;

		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareCall("SELECT pro_codigo, pro_nombre, pro_precio FROM Producto");
			rs = st.executeQuery();

			lista = new ArrayList<Producto>();
			while (rs.next()) {
				Producto pro = new Producto();
				pro.setCodigo(rs.getInt("pro_codigo"));
				pro.setNombre(rs.getString("pro_nombre"));
				pro.setPrecio(rs.getDouble("pro_precio"));

				lista.add(pro);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
		return lista;
	}

	public Producto leerID(Producto pro) throws Exception {
		Producto pros;
		ResultSet rs;
		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareStatement("SELECT pro_codigo, pro_nombre, pro_precio FROM Producto WHERE pro_codigo = ?");
			st.setInt(1, pro.getCodigo());
			rs = st.executeQuery();

			while (rs.next()) {
				pros = new Producto();
				pros.setCodigo(rs.getInt("pro_codigo"));
				pros.setNombre(rs.getString("pro_nombre"));
				pros.setPrecio(rs.getDouble("pro_precio"));

			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
		return pro;
	}

	public void registrar(Producto pro) throws Exception {
		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareStatement("INSERT INTO Producto (pro_nombre, pro_precio) value(?,?)");
			st.setString(1, pro.getNombre());
			st.setDouble(2, pro.getPrecio());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
	}
	
	public void modificar(Producto pro) throws Exception {
		try {
			this.Conectar();
			PreparedStatement st = this.getCon()
					.prepareStatement("UPDATE Producto SET pro_nombre=?, pro_precio=? WHERE pro_codigo=?");
			st.setString(1, pro.getNombre());
			st.setDouble(2, pro.getPrecio());
			st.setInt(3, pro.getCodigo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
	}

	public Producto eliminarID(Producto pro) throws Exception {
		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareStatement("DELETE FROM Producto WHERE pro_codigo = ?");
			st.setInt(1, pro.getCodigo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
		return pro;
	}

}
