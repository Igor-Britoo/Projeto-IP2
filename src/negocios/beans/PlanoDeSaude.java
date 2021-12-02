package negocios.beans;

public enum PlanoDeSaude {
	CASSI("Cassi"), UNIMED("Unimed"), HAPVIDA("Hapvida"),
	AMIL("Amil"), SULAMERICA("SulAm�rica"), SEM_PLANO("Sem plano");
	
	private String plano;

	PlanoDeSaude(String plano) {
		this.plano = plano;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}
	
	
}
