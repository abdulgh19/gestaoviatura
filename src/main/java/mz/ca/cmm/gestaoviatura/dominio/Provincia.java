package mz.ca.cmm.gestaoviatura.dominio;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;


@Entity
public class Provincia extends AbstractEntity {
	
	
	private String designacao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia", orphanRemoval = true)
	private List<Cidade> cidades = new ArrayList<>();

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	
	
}
