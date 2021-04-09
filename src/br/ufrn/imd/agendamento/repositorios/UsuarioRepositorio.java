package br.ufrn.imd.agendamento.repositorios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.agendamento.dominio.Usuario;


@Stateless	
public class UsuarioRepositorio {

	@PersistenceContext
	private EntityManager em;
	
	public Usuario adicionar(Usuario usuario) {
		if(usuario.getId() == 0)
			em.persist(usuario);
		else
			em.merge(usuario);
		return usuario;
	}
	
	public void remover(Usuario usuario) {
		usuario = em.find(Usuario.class, usuario.getId());
		em.remove(usuario);
	}
}
