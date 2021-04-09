package br.ufrn.imd.agendamento.repositorios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.agendamento.dominio.Setor;

@Stateless
public class SetorRepositorio {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Setor> listarSetores() {
		return (List<Setor>) em.createQuery("select s from Setor s").getResultList();
	}
}
