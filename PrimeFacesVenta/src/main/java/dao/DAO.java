package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Sirve para la conexion a la BD

public class DAO {
	private Connection con;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void Conectar() {
		try {
			System.out.println("Conectando a bd....");

			final String DB_URL = "jdbc:mysql://localhost:3306/practica_pf_venta" 
					+ "?useSSL=false" 
					+ "&serverTimezone=America/Mexico_City"
					+ "&allowPublicKeyRetrieval=true";
			final String USER = "root";
			final String PASS = "toor5127-";

			con = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Conexion exitosa....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Cerrar() {
		if (con != null) {
			try {
				if (con.isClosed() == false) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
