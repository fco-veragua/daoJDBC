package es.aytos.fpdual2022Copia.fpdualCopia.utilidades;

import java.util.Random;

public class Utilidades {

	private final static Integer D4 = 4;

	private final static Integer D6 = 6;

	private final static Integer D8 = 8;

	private final static Integer D10 = 10;

	private final static Integer D12 = 12;

	private final static Integer D20 = 20;

	private final static Integer D100 = 100;

	// private static final int COD_SPAIN = 1;
	private static final int COD_ANDORRA = 2;
	private static final int COD_GIBRALTAR = 3;
	private static final int COD_LUXEMBURGO = 4;
	private static final int COD_ISLASBERMUDAS = 5;
	private static final int COD_IRLANDA = 6;
	private static final String RESIDENCIA_SPAIN = "240";
	private static final String RESIDENCIA_EXTRANJERO = "100";

	private static String tirardX(int x) {
		return String.valueOf(new Random().nextInt(x) + 1);
	}

	public static String tirar1d4() {
		return tirardX(D4);
	}

	public static String tirar1d6() {
		return tirardX(D6);
	}

	public static String tirar1d8() {
		return tirardX(D8);
	}

	public static String tirar1d12() {
		return tirardX(D12);
	}

	public static String tirar1d10() {
		return tirardX(D10);
	}

	public static String tirar1d20() {
		return tirardX(D20);
	}

	public static String tirar1d100() {
		return tirardX(D100);
	}

	public static String obtenerPaisResidencia() {
		String paisResidencia = "";
		Integer pais = Integer.valueOf(tirar1d6());
		switch (pais) {
		case COD_ANDORRA:
			paisResidencia = "ANDORRA";
			break;
		case COD_GIBRALTAR:
			paisResidencia = "GIBRALTAR ESPAÑOL";
			break;
		case COD_LUXEMBURGO:
			paisResidencia = "LUXEMBURGO";
			break;
		case COD_ISLASBERMUDAS:
			paisResidencia = "ISLAS BERMUDAS";
			break;
		case COD_IRLANDA:
			paisResidencia = "IRLANDA";
			break;

		default:
			paisResidencia = "ESPAÑA";
			break;
		}
		return paisResidencia;
	}

	public static String obtenerDiasResidenciaSpain() {
		if (resideEnSpain()) {
			return RESIDENCIA_SPAIN;
		}
		return RESIDENCIA_EXTRANJERO;
	}

	private static boolean resideEnSpain() {
		// TODO Auto-generated method stub
		return !"1".equals(tirar1d8());
	}

//	public static String obtenerDiasResidenciaSpain() {
//		Integer dias = Integer.valueOf(tirar1d4());
//		String diasResidencia = "365";
//		while ("2".equals(dias) || "3".equals(dias) || "4".equals(dias)) {
//			diasResidencia = RESIDENCIA_SPAIN;
//		}
//
//		diasResidencia = RESIDENCIA_EXTRANJERO;
//		return diasResidencia;
//
//	}

	public static String sqlTratarTexto(String cadena) {
		return "'" + cadena + "'";
	}
}
