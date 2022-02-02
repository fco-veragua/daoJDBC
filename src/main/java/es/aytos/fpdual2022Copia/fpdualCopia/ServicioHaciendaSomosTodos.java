package es.aytos.fpdual2022Copia.fpdualCopia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import es.aytos.fpdual2022Copia.fpdualCopia.dao.InterfazDAO;
import es.aytos.fpdual2022Copia.fpdualCopia.factoria.FactoriaBD;
import es.aytos.fpdual2022Copia.fpdualCopia.modelo.Ciudadano;
import es.aytos.fpdual2022Copia.fpdualCopia.utilidades.Utilidades;

/**
 * Hello world!
 *
 */
public class ServicioHaciendaSomosTodos {

	private static final Integer TOPE_DIAS_MAXIMO_SPAIN_PARA_NO_DECLARAR = 180;
	private InterfazDAO dao = null;
	final String CADENA_CIERRE = ")";
	private final int ACTUACION_KIKI_CODIGO = 1;

	private final String ACTUACION_KIKI_DESCRIPCION = "MUERTE POR KIKI";

	private final int ACTUACION_ROTURA_PIERNAS_CODIGO = 2;

	private final String ACTUACION_ROTURA_PIERNAS_DESCRIPCION = "ROTURA PIERNAS";

	private final int ACTUACION_CORBATA_CODIGO = 3;

	private final String ACTUACION_CORBATA_DESCRIPCION = "CORBATA COLOMBIANA";

	private final int ACTUACION_MUTILACION_CODIGO = 4;

	private final String ACTUACION_MUTILACION_DESCRIPCION = "CORTADA DE MANOS GUILLOTINA STYLE";

	private final int ACTUACION_VENDIDO_CODIGO = 5;

	private final String ACTUACION_VENDIDO_DESCRIPCION = "VENDIDO COMO ESCLAVO";

	private final int ACTUACION_MEDIEVO_CODIGO = 6;

	private final String ACTUACION_MEDIEVO_DESCRIPCION = "REALIZAR MEDIEVO";

	private final int ACTUACION_MULTACA_CODIGO = 7;

	private final String ACTUACION_MULTACA_DESCRIPCION = "MULTACA INFERNAL";

	private final int ACTUACION_LIBRE_CODIGO = 8;

	private final String ACTUACION_LIBRE_DESCRIPCION = "SE LIBRA POR ERROR JUDICIAL";

	public static void main(String[] args) {
		System.out.println("NO ESTÁS EJECUTANDO EL TEST TONTO");
	}

	public void crearEsquemaCiudadanos() {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE CIUDADANOS(id NUMERIC NOT NULL PRIMARY KEY, nombre VARCHAR(150), direccion VARCHAR(150), facturacionAnual NUMERIC, paisResidencia VARCHAR(50), diasPermanenciaSpain NUMERIC)";

		InterfazDAO dao = FactoriaBD.obtenerDAO();
		this.borrarEsquemaCiudadano(dao);
		dao.ejecutarSentencia(sql);

	}

	public void borrarEsquemaCiudadano(InterfazDAO dao) {
		// TODO Auto-generated method stub
		dao.ejecutarSentencia("DROP TABLE CIUDADANOS");

	}

	public void rellenarTablaCiudadanos(int cantidad) {
		// TODO Auto-generated method stub
		InterfazDAO dao = FactoriaBD.obtenerDAO();
		String sqlInsert = ("INSERT INTO CIUDADANOS(id, nombre, direccion, facturacionAnual, paisResidencia, diasPermanenciaSpain) VALUES (");

		List<Ciudadano> poblacion = new ArrayList<Ciudadano>();
		Ciudadano ciudadano = null;
		for (int i = 0; i < cantidad; i++) {
			ciudadano = new Ciudadano();
			ciudadano.setId(String.valueOf(i));
			ciudadano.setNombre("Nombre:" + i);
			ciudadano.setDireccion("Direccion:" + i);
			ciudadano.setFacturacionAnual(Utilidades.tirar1d100());
			ciudadano.setPaisResidencia(Utilidades.obtenerPaisResidencia() + "");
			ciudadano.setDiasPermanenciaSpain(Utilidades.obtenerDiasResidenciaSpain());
			dao.ejecutarSentencia(sqlInsert + "");

			poblacion.add(ciudadano);
		}

		for (Ciudadano c : poblacion) {
			dao.ejecutarSentencia(sqlInsert + c.traducirASQLValues() + CADENA_CIERRE);
		}
	}

	public List<Ciudadano> obtenerCiudadanos() {
		List<Map<String, String>> ciudadanosSQL = this.getDao().ejecutarConsulta("SELECT * FROM CIUDADANOS");
// SELECT * FROM CIUDADANOS WHERE paisResidencia != 'ESPAÑA'
		List<Ciudadano> poblacion = new ArrayList<Ciudadano>();
		Ciudadano citizen = null;

		for (Map<String, String> ciudadanoSQL : ciudadanosSQL) {
			citizen = new Ciudadano();
			citizen.setId(ciudadanoSQL.get("id"));
			citizen.setNombre(ciudadanoSQL.get("nombre"));
			citizen.setDireccion(ciudadanoSQL.get("direccion"));
			citizen.setFacturacionAnual(ciudadanoSQL.get("facturacionAnual"));
			citizen.setPaisResidencia(ciudadanoSQL.get("paisResidencia"));
			citizen.setDiasPermanenciaSpain(ciudadanoSQL.get("diasPermanencia"));

			poblacion.add(citizen);
		}

		return poblacion;

	}

	private InterfazDAO getDao() {
		if (Objects.isNull(this.dao)) {
			this.dao = FactoriaBD.obtenerDAO();
		}

		return this.dao;
	}

	private boolean declaraResidirExtranjero(Ciudadano c) {
		return !c.getPaisResidencia().equals("ESPAÑA");
	}

	private boolean haEstadoViviendoSpain(Ciudadano c) {
		return Integer.valueOf(c.getDiasPermanenciaSpain()) > TOPE_DIAS_MAXIMO_SPAIN_PARA_NO_DECLARAR;
	}

	public List<Ciudadano> obtenerCiudadanosMorosos() {
		List<Ciudadano> ciudadanos = obtenerCiudadanos();
		List<Ciudadano> morosos = new ArrayList<Ciudadano>();

		for (Ciudadano c : ciudadanos) {
			if (this.declaraResidirExtranjero(c) && this.haEstadoViviendoSpain(c)) {
				morosos.add(c);
			}
		}
		return morosos;
	}

	public void rellenarTablaMorosos() {
		List<Ciudadano> morosos = this.obtenerCiudadanosMorosos();

		String sqlInsert = ("INSERT INTO MOROSOS(id, nombre, actuacion) VALUES(");
		for (Ciudadano m : morosos) {
			this.getDao().ejecutarSentencia(sqlInsert + m.getId() + "," + Utilidades.sqlTratarTexto(m.getNombre()) + ","
					+ Utilidades.sqlTratarTexto(this.decidirActuacion()) + this.CADENA_CIERRE);
		}
	}

	private String decidirActuacion() {
		int actuacion = Integer.parseInt(Utilidades.tirar1d8());
		String actuacionDesc = "";
		switch (actuacion) {
		case ACTUACION_KIKI_CODIGO:
			actuacionDesc = this.ACTUACION_KIKI_DESCRIPCION;
			break;
		case ACTUACION_ROTURA_PIERNAS_CODIGO:
			actuacionDesc = this.ACTUACION_ROTURA_PIERNAS_DESCRIPCION;
			break;
		case ACTUACION_CORBATA_CODIGO:
			actuacionDesc = this.ACTUACION_CORBATA_DESCRIPCION;
			break;
		case ACTUACION_MUTILACION_CODIGO:
			actuacionDesc = this.ACTUACION_MUTILACION_DESCRIPCION;
			break;
		case ACTUACION_VENDIDO_CODIGO:
			actuacionDesc = this.ACTUACION_VENDIDO_DESCRIPCION;
			break;
		case ACTUACION_MEDIEVO_CODIGO:
			actuacionDesc = this.ACTUACION_MEDIEVO_DESCRIPCION;
			break;
		case ACTUACION_MULTACA_CODIGO:
			actuacionDesc = this.ACTUACION_MULTACA_DESCRIPCION;
			break;
		case ACTUACION_LIBRE_CODIGO:
			actuacionDesc = this.ACTUACION_LIBRE_DESCRIPCION;
			break;
		}

		return actuacionDesc;
	}
}