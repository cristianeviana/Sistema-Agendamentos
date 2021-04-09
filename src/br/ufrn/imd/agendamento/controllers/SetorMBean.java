package br.ufrn.imd.agendamento.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.agendamento.dominio.Setor;
import br.ufrn.imd.agendamento.repositorios.SetorRepositorio;

@Named("setorMBean")
@SessionScoped
public class SetorMBean implements Serializable {

	@Inject private SetorRepositorio setorRepositorio;
	 
	private List<SelectItem> listasetores;
	//private ArrayList<Setor> setores;
	
	private Setor setor;
	
	public SetorMBean() {
		setor = new Setor();
	}
	
	public Setor getSetor() {
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	/*
	 * public ArrayList<Setor> getSetores() { if (setores == null) { setores = new
	 * ArrayList<Setor>(); listasetores = (List<Setor>)
	 * setorRepositorio.listarSetores();
	 * 
	 * for(Setor s : listasetores){ setores.add(s); }
	 * 
	 * } return setores; }
	 */
	
	public List<SelectItem> getListasetores(){
		
		List<Setor> setores = new ArrayList<Setor>();
		this.listasetores = new ArrayList<SelectItem>();
		
		setores = setorRepositorio.listarSetores();
		listasetores.add(new SelectItem(null, "Selecione"));
		for(Setor s : setores){
			listasetores.add(new SelectItem(s.getId(),s.getNome()));
		}
		
		return listasetores;
	}
	
	
	
	public void setListasetores(List<SelectItem> listasetores) {
		this.listasetores = listasetores;
	}
	/*
	 * public void setSetores(ArrayList<Setor> setores) { this.setores = setores; }
	 */
}
