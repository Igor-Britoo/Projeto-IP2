package negocios.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Medico extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 5428309103104374269L;
	private Especialidade areaDeAtuacao;
	
	public Medico() {}
	
	public Medico(String nome, String cpf, LocalDate dataDeNascimento, Especialidade areaDeAtuacao, String crm) {
		super(nome, cpf, dataDeNascimento);
		this.areaDeAtuacao = areaDeAtuacao;
	}

	public Especialidade getAreaDeAtuacao() {
		return areaDeAtuacao;
	}

	public void setAreaDeAtuacao(Especialidade areaDeAtuacao) {
		this.areaDeAtuacao = areaDeAtuacao;
	}
	
	public String getTipo() {
		return "MÉDICO";
	}

	@Override
	public String toString() {
		return String.format("%-16s || %s || %-15s ",
				this.getTipo(),
				super.toString(),
				this.getAreaDeAtuacao().getEspecialidade());
	}
	
}
