package mz.ca.cmm.gestaoviatura.dominio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 

@Entity
public class Viatura extends AbstractEntity {
  
	private String matricula;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Pessoa_idPessoa")
	private Pessoa pessoa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idModelo")
	private Modelo modelo;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}


				
}
