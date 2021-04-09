package br.ufrn.imd.agendamento.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Setor {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SETOR")
	@SequenceGenerator(name="SEQ_SETOR", sequenceName="id_seq_setor", allocationSize=1)
	private int id;
		
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
