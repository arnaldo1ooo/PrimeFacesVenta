package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Persona;

public class PersonaDAO extends DAO {

	public List<Persona> listar() throws Exception {
		List<Persona> lista;
		ResultSet rs;

		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareCall("SELECT per_codigo, per_nombre, per_sexo FROM Persona");
			rs = st.executeQuery();

			lista = new ArrayList<Persona>();
			while (rs.next()) {
				Persona per = new Persona();
				per.setCodigo(rs.getInt("per_codigo"));
				per.setNombre(rs.getString("per_nombre"));
				per.setSexo(rs.getString("per_sexo"));

				lista.add(per);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
		return lista;
	}

	public Persona leerID(Persona per) throws Exception {
		Persona pers;
		ResultSet rs;
		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareStatement("SELECT per_codigo, per_nombre, per_sexo FROM persona WHERE per_codigo = ?");
			st.setInt(1, per.getCodigo());
			rs = st.executeQuery();

			while (rs.next()) {
				pers = new Persona();
				pers.setCodigo(rs.getInt("per_codigo"));
				pers.setNombre(rs.getString("per_nombre"));
				pers.setSexo(rs.getString("per_sexo"));

			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
		return per;
	}

	public void registrar(Persona per) throws Exception {
		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareStatement("INSERT INTO persona (per_nombre, per_sexo) value(?,?)");
			st.setString(1, per.getNombre());
			st.setString(2, per.getSexo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
	}
	
	public void modificar(Persona per) throws Exception {
		try {
			this.Conectar();
			PreparedStatement st = this.getCon()
					.prepareStatement("UPDATE persona SET per_nombre=?, per_sexo=? WHERE per_codigo=?");
			st.setString(1, per.getNombre());
			st.setString(2, per.getSexo());
			st.setInt(3, per.getCodigo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
	}

	public Persona eliminarID(Persona per) throws Exception {
		try {
			this.Conectar();
			PreparedStatement st = this.getCon().prepareStatement("DELETE FROM persona WHERE per_codigo = ?");
			st.setInt(1, per.getCodigo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			this.Cerrar();
		}
		return per;
	}

}
