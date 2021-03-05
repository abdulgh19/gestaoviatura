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
public class Cidade extends AbstractEntity {

	private String designacao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade", orphanRemoval = true)
	private List<Pessoa> pessoas = new ArrayList<>();
	  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProvincia")
	private Provincia provincia;


	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	
	
}
