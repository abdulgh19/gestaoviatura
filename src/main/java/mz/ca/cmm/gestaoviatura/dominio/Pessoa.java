package mz.ca.cmm.gestaoviatura.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pessoa extends AbstractEntity {
	
	private String nome;
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", orphanRemoval = true)
	private List<Viatura> viaturas = new ArrayList<>(); 
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCidade")
	private Cidade cidade;


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Viatura> getViaturas() {
		return viaturas;
	}


	public void setViaturas(List<Viatura> viaturas) {
		this.viaturas = viaturas;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}




	
}
