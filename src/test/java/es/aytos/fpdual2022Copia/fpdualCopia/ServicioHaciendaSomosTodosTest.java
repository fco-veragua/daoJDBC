package es.aytos.fpdual2022Copia.fpdualCopia;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import es.aytos.fpdual2022Copia.fpdualCopia.dao.InterfazDAO;
import es.aytos.fpdual2022Copia.fpdualCopia.factoria.FactoriaBD;

/**
 * Unit test for simple App.
 */
public class ServicioHaciendaSomosTodosTest {

	private ServicioHaciendaSomosTodos servicioHaciendaSomosTodos = null;

	private InterfazDAO dao = null;

	@Before
	public void inicializar() {
		this.servicioHaciendaSomosTodos = new ServicioHaciendaSomosTodos();
		this.dao = FactoriaBD.obtenerDAO();
	}

//	@Test
//	public void testCreaEsquemaCiudadano() {
//		this.servicioHaciendaSomosTodos.crearEsquemaCiudadanos();
//
//		// assertTrue(this.dao.ejecutarSentencia("INSERT INTO CIUDADANOS
//		// (id,nombre)VALUES (1,'FCO')"));
//		assert (this.dao.ejecutarSentencia("DELETE CIUDADANOS"));
//	}

	@Test
	public void testRellenarTablaCiudadanos() {
		int poblacionTotal = 100;
		this.servicioHaciendaSomosTodos.rellenarTablaCiudadanos(poblacionTotal);
		assertThat(dao.ejecutarConsulta("SELECT * FROM CIUDADANOS").size(), is(poblacionTotal));
	}

}