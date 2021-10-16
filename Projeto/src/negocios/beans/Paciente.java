package negocios.beans;

import java.time.LocalDate;

public class Paciente extends Usuario{
	private ClassificacoesDeRisco classificacao;
	private PlanoDeSaude plano;

	public Paciente(String nome, String cpf, LocalDate dataDeNascimento, String login, String senha,
			ClassificacoesDeRisco classificacao, PlanoDeSaude plano) {
		super(nome, cpf, dataDeNascimento, login, senha);
		this.classificacao = classificacao;
		this.plano = plano;
	}

	public Paciente(String nome, String cpf, LocalDate dataDeNascimento, String login, String senha,
			ClassificacoesDeRisco classificacao) {
		super(nome, cpf, dataDeNascimento, login, senha);
		this.classificacao = classificacao;
	}

	public ClassificacoesDeRisco getClassificacao() {
		return this.classificacao;
	}

	public void setClassificacao(ClassificacoesDeRisco classificacao) {
		this.classificacao = classificacao;
	}

	public PlanoDeSaude getPlano() {
		return this.plano;
	}

	public void setPlano(PlanoDeSaude plano) {
		this.plano = plano;
	}
	
	public String getTipo() {
		return "PACIENTE";
	}
}
