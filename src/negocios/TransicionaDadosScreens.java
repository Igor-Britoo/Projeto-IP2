package negocios;

import negocios.beans.Especialidade;

public class TransicionaDadosScreens {
	
	private static TransicionaDadosScreens instance;
	
	private int indexUser;
	private String typeUser;
	private String cpf;
	private Especialidade especialidadeSelected;
	private String cpfMedicoSelected;
	
	private TransicionaDadosScreens() {
		this.cpf = "0000000";
		this.especialidadeSelected = Especialidade.OBSTETRA;
		this.indexUser = 0;
		this.typeUser = "";
		this.cpfMedicoSelected = "0000000";
	}

	public static TransicionaDadosScreens getInstance() {
		if (instance == null) {
			instance = new TransicionaDadosScreens();
		}
		return instance;
	}

	public int getIndexUser() {
		return indexUser;
	}

	public void setIndexUser(int indexUser) {
		this.indexUser = indexUser;
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	public Especialidade getEspecialidadeSelected() {
		return especialidadeSelected;
	}

	public void setEspecialidadeSelected(Especialidade especialidadeSelected) {
		this.especialidadeSelected = especialidadeSelected;
	}

	public String getCpfMedicoSelected() {
		return cpfMedicoSelected;
	}

	public void setCpfMedicoSelected(String cpfMedicoSelected) {
		this.cpfMedicoSelected = cpfMedicoSelected;
	}

	public static void setInstance(TransicionaDadosScreens instance) {
		TransicionaDadosScreens.instance = instance;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

	
	
}
