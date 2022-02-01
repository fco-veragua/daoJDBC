package es.aytos.fpdual2022Copia.fpdualCopia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SQLServerDAO implements InterfazDAO {

	private Connection conexionBD = null;

	public void conectarBD(String nombreDB) {

	}

	// integratedSecurity=true
	public boolean conectar() {
		try {
			this.conexionBD = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=fpdual2022;trustServerCertificate=true", "sa", "sa");
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			System.out.println("Error de conexión: " + ex.getMessage());
			return false;
		}

		return true;
	}

	public boolean isConexionAbierta() {
		try {
			return !this.conexionBD.isClosed();

		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean desconectar() {
		try {
			if (!Objects.isNull(this.conexionBD) && this.isConexionAbierta()) {
				this.conexionBD.close();
			}
		} catch (SQLException e) {
			System.out.println("Error de desconexión: " + e.getMessage());
			return false;
		}

		return true;

	}

	public boolean ejecutarSentencia(String sql) {

		try {
			if (this.conectar()) {
				this.conexionBD.createStatement().executeUpdate(sql);
			}
		} catch (SQLException e) {
			return false;
		} finally {
			this.desconectar();
		}
		return true;
	}

	public List<Map<String, String>> ejecutarConsulta(String sql) {
		List<Map<String, String>> consulta = new ArrayList<Map<String, String>>();

		if (!this.conectar()) {
			return consulta;
		}

		ResultSet resultadoConsulta = null;
		Map<String, String> registro = null;

		List<String> columnas = new ArrayList<String>();

		try {
			Statement stmt = this.conexionBD.createStatement();
			resultadoConsulta = stmt.executeQuery(sql);
			// resultadoConsulta==null
			// Objects.isNull(resultadoConsulta)
			if (Objects.isNull(resultadoConsulta)) {
				return consulta;
			}

			for (int i = 1; i <= resultadoConsulta.getMetaData().getColumnCount(); i++) {
				columnas.add(resultadoConsulta.getMetaData().getColumnName(i));

			}

			while (resultadoConsulta.next()) {
				registro = new HashMap<String, String>();
				for (String columna : columnas) {
					registro.put(columna, resultadoConsulta.getString(columna));
				}

				consulta.add(registro);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.cerrarResultSet(resultadoConsulta);
			this.desconectar();
		}

		return consulta;
	}

	private void cerrarResultSet(ResultSet resultadoConsulta) {
		try {
			resultadoConsulta.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

}