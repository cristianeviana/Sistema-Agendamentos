package br.ufrn.imd.agendamento.repositorios;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrn.imd.agendamento.dominio.Funcionario;

@Stateless	
public class FuncionarioRepositorio {

	@PersistenceContext
	private EntityManager em;

	public Funcionario getFuncionario(String login, String senha) {
		try {
			Query q = em.createQuery("select f from Funcionario f where login = :login and senha = :senha");
			q.setParameter("login", login);
			q.setParameter("senha", senha);
			return (Funcionario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
