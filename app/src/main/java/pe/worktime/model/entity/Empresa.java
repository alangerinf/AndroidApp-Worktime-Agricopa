package pe.worktime.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String codEmpresa;
	private String empresa;
	private List<Fundo> fundos = new ArrayList<Fundo>();
	
	public Empresa() {
		super();
		this.codEmpresa = "";
		this.empresa = "";
	}
	
	public Empresa(String codEmpresa, String empresa) {
		super();
		this.codEmpresa = codEmpresa;
		this.empresa = empresa;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public List<Fundo> getFundos() {
		return fundos;
	}

	public void setFundos(List<Fundo> fundos) {
		this.fundos = fundos;
	}
	
	public String toString(){
		return "Codigo: " + this.codEmpresa + " Empresa: " + this.empresa;
	}
	
}
