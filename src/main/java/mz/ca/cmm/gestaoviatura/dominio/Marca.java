package mz.ca.cmm.gestaoviatura.dominio;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;



@Entity
public class Marca extends AbstractEntity {

	private String designacao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "marca", orphanRemoval = true)
	private List<Modelo> modelos = new ArrayList<>();
	
	public Marca() {
		
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	
	}
