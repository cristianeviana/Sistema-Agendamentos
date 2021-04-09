package br.ufrn.imd.agendamento.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.agendamento.dominio.Funcionario;
import br.ufrn.imd.agendamento.repositorios.FuncionarioRepositorio;

@Named("funcionarioMBean")
@SessionScoped
public class FuncionarioMBean implements Serializable {
	
	@Inject
	private FuncionarioRepositorio funcionarioRepositorio;
	
	private Funcionario funcionario;
	
	private Funcionario funcionarioLogado;
		
	public FuncionarioMBean() {
		funcionario = new Funcionario();
	}

	public String logar() {
		Funcionario funcionarioBd = funcionarioRepositorio.getFuncionario(funcionario.getLogin(), funcionario.getSenha());
		if(funcionarioBd != null) {
			funcionarioLogado = funcionarioBd;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("funcionarioLogado", funcionarioLogado);
			return "/pages/index.jsf";
		}
		else {
			FacesMessage msg = new FacesMessage("Funcionário não encontrado.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			return null;
		}
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionariooLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

}
