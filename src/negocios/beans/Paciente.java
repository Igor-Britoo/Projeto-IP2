package negocios.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Paciente extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1795569444104914475L;
	private PlanoDeSaude planoDeSaude;
	private String senha;
	
	private String planoDeSaudeString;
	
	public Paciente() {}

	public Paciente(String nome, String cpf, LocalDate dataDeNascimento, PlanoDeSaude planoDeSaude, String senha) {
		super(nome, cpf, dataDeNascimento);
		this.planoDeSaude = planoDeSaude;
		this.senha = senha;
	}

	public PlanoDeSaude getPlanoDeSaude() {
		return planoDeSaude;
	}

	public void setPlanoDeSaude(PlanoDeSaude planoDeSaude) {
		this.planoDeSaude = planoDeSaude;
	}
	
	public String getPlanoDeSaudeString() {
		return planoDeSaudeString;
	}

	public void setPlanoDeSaudeString(String planoDeSaudeString) {
		switch(planoDeSaudeString) {
		case "Cassi":
			this.planoDeSaude = PlanoDeSaude.CASSI;
			break;
		
		case "Unimed":
			this.planoDeSaude = PlanoDeSaude.UNIMED;
			break;
			
		case "Hapvida":
			this.planoDeSaude = PlanoDeSaude.HAPVIDA;
			break;
		
		case "Amil":
			this.planoDeSaude = PlanoDeSaude.AMIL;
			break;
			
		case "SulAmérica":
			this.planoDeSaude = PlanoDeSaude.SULAMERICA;
			break;
		
		case "Sem plano":
			this.planoDeSaude = PlanoDeSaude.SEM_PLANO;
			break;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return "PACIENTE";
	}

	@Override
	public String toString() {
		return String.format("%-16s || %s || %-15s",
				this.getTipo(),
				super.toString(),
				this.planoDeSaude.getPlano());
	}
	
	
}
