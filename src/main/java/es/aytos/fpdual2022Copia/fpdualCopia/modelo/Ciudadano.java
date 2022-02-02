package es.aytos.fpdual2022Copia.fpdualCopia.modelo;

import es.aytos.fpdual2022Copia.fpdualCopia.utilidades.Utilidades;

public class Ciudadano {
	private String id;

	private String nombre;

	private String direccion;

	private String facturacionAnual;

	private String paisResidencia;

	private String diasPermanenciaSpain;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFacturacionAnual() {
		return facturacionAnual;
	}

	public void setFacturacionAnual(String facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}

	public String getPaisResidencia() {
		return paisResidencia;
	}

	public void setPaisResidencia(String paisResidencia) {
		this.paisResidencia = paisResidencia;
	}

	public String getDiasPermanenciaSpain() {
		return diasPermanenciaSpain;
	}

	public void setDiasPermanenciaSpain(String diasPermanenciaSpain) {
		this.diasPermanenciaSpain = diasPermanenciaSpain;
	}

	public String traducirASQLValues() {
		// TODO Auto-generated method stub
		return this.getId() + "," + Utilidades.sqlTratarTexto(this.getNombre()) + ","
				+ Utilidades.sqlTratarTexto(this.getDireccion()) + ", " + this.getFacturacionAnual() + ","
				+ Utilidades.sqlTratarTexto(this.getPaisResidencia()) + ", " + this.getDiasPermanenciaSpain();
	}

}
