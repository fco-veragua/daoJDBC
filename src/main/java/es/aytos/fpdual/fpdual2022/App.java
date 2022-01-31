package es.aytos.fpdual.fpdual2022;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// System.out.println("Hello World!");

		InterfazDAO dao = FactoriaBD.obtenerDAO();

		insertarFco(dao);
		imprimirContenidoTablaPrueba(dao);

		/*
		 * if (dao.conectar()) { System.out.println("OK!!"); } else {
		 * System.out.println("KO!!"); }
		 */

		/*
		 * String query = "INSERT INTO PRUEVA VALUES (6, 'CHRIS')"; if
		 * (!dao.ejecutarSentencia(query)) { System.out.println("ERROR"); }
		 */

		if (dao.desconectar()) {
			System.out.println("DESCONECTAR OK!!");
		} else {
			System.out.println("DESCONECTAR KO!!");
		}

	}

	private static void insertarFco(InterfazDAO dao) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO PRUEVA VALUES (6, 'CHRIS')";
		if (!dao.ejecutarSentencia(query)) {
			System.out.println("ERROR");
		}
	}

	private static void imprimirContenidoTablaPrueba(InterfazDAO dao) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM PRUEVA";
		List<Map<String, String>> personas = dao.ejecutarConsulta(query);

		for (Map<String, String> persona : personas) {
			System.out.println("clave=" + persona.get("ID") + ", valor=" + persona.get("NOMBRE"));
		}

		// personas.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " +
		// v));

	}

}