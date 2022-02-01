package es.aytos.fpdual2022Copia.fpdualCopia.factoria;

import es.aytos.fpdual2022Copia.fpdualCopia.dao.InterfazDAO;
import es.aytos.fpdual2022Copia.fpdualCopia.dao.SQLServerDAO;

public class FactoriaBD {
	public static InterfazDAO obtenerDAO() {
		return new SQLServerDAO();
	}
}