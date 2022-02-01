package es.aytos.fpdual2022Copia.fpdualCopia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.aytos.fpdual2022Copia.fpdualCopia.dao.InterfazDAO;
import es.aytos.fpdual2022Copia.fpdualCopia.factoria.FactoriaBD;
import es.aytos.fpdual2022Copia.fpdualCopia.modelo.Ciudadano;

/**
 * Hello world!
 *
 */
public class ServicioHaciendaSomosTodos {
	final String CADENA_CIERRE = ")";

	public static void main(String[] args) {
		// NADA
	}

	public void crearEsquemaCiudadanos() {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE CIUDADANOS(id NUMERIC NOT NULL PRIMARY KEY, nombre VARCHAR(150), direccion VARCHAR(150), facturacionAnual NUMERIC, paisResidencia VARCHAR(50))";

		InterfazDAO dao = FactoriaBD.obtenerDAO();
		this.borrarEsquemaCiudadano(dao);
		dao.ejecutarSentencia(sql);

	}

	public void borrarEsquemaCiudadano(InterfazDAO dao) {
		// TODO Auto-generated method stub
		dao.ejecutarSentencia("DROP TABLE CIUDADANOS");

	}

	private String tirard100() {
		return String.valueOf(new Random().nextInt(100) % 100);
	}

	private String sqlTratarTexto(String cadena) {
		return "'" + cadena + "'";
	}

	public void rellenarTablaCiudadanos(int cantidad) {
		// TODO Auto-generated method stub
		InterfazDAO dao = FactoriaBD.obtenerDAO();
		String sqlInsert = ("INSERT INTO CIUDADANOS(id,nombre, direccion, facturacionAnual, paisResidencia) VALUES (");

		List<Ciudadano> poblacion = new ArrayList<Ciudadano>();
		Ciudadano ciudadano = null;
		for (int i = 0; i < cantidad; i++) {
			ciudadano = new Ciudadano();
			ciudadano.setId(String.valueOf(i));
			ciudadano.setNombre("Nombre:" + i);
			ciudadano.setDireccion("Direccion:" + i);
			ciudadano.setFacturacionAnual(this.tirard100());
			ciudadano.setPaisResidencia(i + "");
			dao.ejecutarSentencia(sqlInsert + "");

			poblacion.add(ciudadano);
		}

		for (Ciudadano c : poblacion) {
			dao.ejecutarSentencia(sqlInsert + traducirASQLValues(c) + CADENA_CIERRE);
		}
	}

	private String traducirASQLValues(Ciudadano c) {
		// TODO Auto-generated method stub
		return c.getId() + "," + this.sqlTratarTexto(c.getNombre()) + "," + this.sqlTratarTexto(c.getDireccion()) + ", "
				+ c.getFacturacionAnual() + "," + this.sqlTratarTexto(c.getPaisResidencia());
	}
}