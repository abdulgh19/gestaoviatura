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
public class Modelo extends AbstractEntity {  
	private String designacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMarca")
	private Marca marca;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "modelo", orphanRemoval = true)
	private List<Viatura> viaturas = new ArrayList<>();

	

	
	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Viatura> getViaturas() {
		return viaturas;
	}

	public void setViaturas(List<Viatura> viaturas) {
		this.viaturas = viaturas;
	}

	

}
