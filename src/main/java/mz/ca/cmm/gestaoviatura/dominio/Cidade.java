package mz.ca.cmm.gestaoviatura.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cidade extends AbstractEntity {

	
	@NotBlank(message = "Informe uma designação")
	@Size(min = 3, max = 45, message = "A designação da cidade deve ter entre {min} e {max} caracteres")
	private String designacao;
	
	private int numero_de_habitantes;
	
	private int numero_de_vacinasCalculado;
		
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade", orphanRemoval = true)
	private List<Pessoa> pessoas = new ArrayList<>();
	 
	@NotNull(message = "Selecione a provincia relativa a cidade")
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

	public int getNumero_de_habitantes() {
		return numero_de_habitantes;
	}

	public void setNumero_de_habitantes(int numero_de_habitantes) {
		this.numero_de_habitantes = numero_de_habitantes;
	}

	public int getNumero_de_vacinasCalculado() {
		return numero_de_vacinasCalculado;
	}

	public void setNumero_de_vacinasCalculado(int numero_de_vacinasCalculado) {
		this.numero_de_vacinasCalculado = numero_de_vacinasCalculado;
	}
	
	
	
	


	
	
}
