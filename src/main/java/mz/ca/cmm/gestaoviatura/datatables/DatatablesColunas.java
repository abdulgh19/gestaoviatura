package mz.ca.cmm.gestaoviatura.datatables;

public class DatatablesColunas {


	public static final String[] PROVINCIA = {"id", "designacao"};

	public static final String[] MARCA = {"id", "designacao"};
	
	public static final String[] CIDADE = {"id", "designacao", "provincia.designacao"};
	
	public static final String[] PESSOA = {"id", "designacao", "cidade.designacao"};
	
	public static final String[] VIATURA = {"id", "designacao", "pessoa.designacao", "modelo.designacao"};
	
	public static final String[] MODELO = {"id", "designacao", "marca.designacao"};



	
}



