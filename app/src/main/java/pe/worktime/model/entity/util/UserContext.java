package pe.worktime.model.entity.util;

import java.util.List;

import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Usuario;

public class UserContext {
	
	private List<Empresa> empresas;
	private List<Fundo> fundos;
	private Usuario user;
	/*
	public UserContext(List<Fundo> fundos, Usuario user) {
		this.fundos = fundos;
		this.user = user;
	}*/
	
	public UserContext(List<Empresa> empresas, Usuario user) {
		this.empresas = empresas;
		this.user = user;
	}
	
	public List<Fundo> getFundos() {
		return fundos;
	}
	
	public void setFundos(List<Fundo> fundos) {
		this.fundos = fundos;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	
	
	
	
}