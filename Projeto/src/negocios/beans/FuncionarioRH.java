package negocios.beans;

import java.time.LocalDate;

public class FuncionarioRH extends Funcionario{
	public static final long idAdmin = 94819381181992828L;
	
	public FuncionarioRH(String nome, String cpf, LocalDate dataDeNascimento, String login, String senha) {
		super(nome, cpf, dataDeNascimento, login, senha);
		this.setId(idAdmin);
	}

	public static long getIdAdmin() {
		return idAdmin;
	}

	@Override
	public String getTipo() {
		return super.getTipo() + " RH";
	}
	
}
