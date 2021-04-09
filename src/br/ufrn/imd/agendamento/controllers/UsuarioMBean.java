package br.ufrn.imd.agendamento.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.agendamento.dominio.Usuario;
import br.ufrn.imd.agendamento.repositorios.UsuarioRepositorio;

@Named("usuarioMBean")
@SessionScoped
public class UsuarioMBean implements Serializable {

	@Inject
	private UsuarioRepositorio usuarioRepositorio;
	
	private Usuario usuario;
		
	public UsuarioMBean() {
		usuario = new Usuario();
	}
	
	public Usuario cadastrarUsuario() {
		return usuarioRepositorio.adicionar(usuario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
