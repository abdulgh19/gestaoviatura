package mz.ca.cmm.gestaoviatura.dominio;

public enum PerfilTipo {
	ADMIN(1, "ADMIN"), FUNCIONARIO(2, "FUNCIONARIO");
	
	private long cod;
	private String desc;

	private PerfilTipo(long cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public long getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
}
