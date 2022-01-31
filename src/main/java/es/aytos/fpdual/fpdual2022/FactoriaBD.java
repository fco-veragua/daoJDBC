package es.aytos.fpdual.fpdual2022;

public class FactoriaBD {
	public static InterfazDAO obtenerDAO() {
		return new SQLServerDAO();
	}
}
