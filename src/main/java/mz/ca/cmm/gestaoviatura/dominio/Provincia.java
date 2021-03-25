package mz.ca.cmm.gestaoviatura.dominio;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Provincia extends AbstractEntity {
	
	
	@NotBlank(message = "Informe uma designação")
	@Size(max = 7, message = "A designação da provincia deve ter no maximo 7 caracteres")
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
