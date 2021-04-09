package br.ufrn.imd.agendamento.repositorios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.agendamento.dominio.Agendamento;
import br.ufrn.imd.agendamento.dominio.Funcionario;

@Stateless
public class AgendamentoRepositorio {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Agendamento> listarAgendamentos() {
		return (List<Agendamento>) em.createQuery("select a from Agendamento a").getResultList();
	}
	
	public Agendamento adicionar(Agendamento agendamento) {
		if(agendamento.getId() == 0)
			em.persist(agendamento);
		else
			em.merge(agendamento);
		return agendamento;
	}
	
	public void remover(Agendamento agendamento) {
		agendamento = em.find(Agendamento.class, agendamento.getId());
		em.remove(agendamento);
	}
	
	public Agendamento getAgendamentoById(int id) {
		try {
			Query q = em.createQuery("select a from Agendamento a where id = :id");
			q.setParameter("id", id);
			return (Agendamento) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
