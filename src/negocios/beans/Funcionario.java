package negocios.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class Funcionario extends Pessoa implements Serializable{

	private static final long serialVersionUID = -6625743861965560779L;
	private String senha;
	
	public Funcionario() {}
	
	public Funcionario(String nome, String cpf, LocalDate dataDeNascimento, String senha) {
		super(nome, cpf, dataDeNascimento);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo(){
		return "FUNCIONARIO";
	}

	@Override
	public String toString() {
		return String.format("%-16s || %s" ,
				this.getTipo(),
				super.toString());
	}
	
	
}
