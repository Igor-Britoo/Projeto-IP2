package negocios.beans;

public enum Especialidade {
	PEDIATRA("Pediatra"), OBSTETRA("Obstetra"),
	GINECOLOGISTA("Ginecologista"), UROLOGISTA("Urologista"),
	OFTALMOLOGISTA("Oftalmologista"), DENTISTA("Dentista");
	
	private String especialidade;
	
	Especialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
}
