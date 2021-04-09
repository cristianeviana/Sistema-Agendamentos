package br.ufrn.imd.agendamento.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.agendamento.dominio.Agendamento;
import br.ufrn.imd.agendamento.dominio.Setor;
import br.ufrn.imd.agendamento.dominio.Usuario;
import br.ufrn.imd.agendamento.repositorios.AgendamentoRepositorio;

@Named
@SessionScoped
public class AgendamentoMBean implements Serializable {

	private Agendamento agendamento;
	
	private Agendamento detalheAgendamento;
	
	private DataModel<Agendamento> agendamentosModel;
	
	@Inject 
	private UsuarioMBean usuarioMBean;
	 
	@Inject 
	private SetorMBean setorMBean; 
	
	@Inject
	private AgendamentoRepositorio agendamentoRepositorio;
	
	public AgendamentoMBean() {
		agendamento = new Agendamento();
	}
	
	public String listarAgendamentos() {
		agendamentosModel = new ListDataModel<Agendamento> (agendamentoRepositorio.listarAgendamentos());
		return "/pages/agendamento/list.jsf";
	}
	
	public String novoAgendamento() {
		agendamento = new Agendamento();
		usuarioMBean.setUsuario(new Usuario());
		setorMBean.setSetor(new Setor());
		return "cadastroAgendamento.jsf";
	}	
	
	public String cadastrarAgendamento() {
		agendamento.setUsuario(usuarioMBean.cadastrarUsuario());
		agendamento.setSetor(setorMBean.getSetor());
		agendamento = agendamentoRepositorio.adicionar(agendamento);
		

		detalheAgendamento = agendamentoRepositorio.getAgendamentoById(agendamento.getId());
		if(detalheAgendamento != null) {
			FacesMessage msg = new FacesMessage("Agendamento realizado com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("detalheAgendamento", detalheAgendamento);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msg", msg);
			
			return "detalhesAgendamento.jsf";	
		}
		else {
			FacesMessage msg = new FacesMessage("Agendamento não localizado!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			return null;
		}
		
	}
	
	public String removerAgendamento() {
		Agendamento agendamentoRemovido = agendamentosModel.getRowData();
		agendamentoRepositorio.remover(agendamentoRemovido);
		return listarAgendamentos();
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}
	
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	public DataModel<Agendamento> getAgendamentosModel() {
		return agendamentosModel;
	}
	
	public void setAgendamentosModel(DataModel<Agendamento> agendamentosModel) {
		this.agendamentosModel = agendamentosModel;
	}
	
	public Agendamento getDetalheAgendamento() {
		return detalheAgendamento;
	}
	
	public void setDetalheAgendamento(Agendamento detalheAgendamento) {
		this.detalheAgendamento = detalheAgendamento;
	}
}
