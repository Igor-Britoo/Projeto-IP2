package negocios.beans;

import java.time.LocalDate;

public class Funcionario extends Usuario{
	private long id;

	public Funcionario(String nome, String cpf, LocalDate dataDeNascimento, String login, String senha) {
		super(nome, cpf, dataDeNascimento, login, senha);
		this.id = 1232819382198L;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTipo(){
		return "FUNCIONÁRIO";
	}
}
