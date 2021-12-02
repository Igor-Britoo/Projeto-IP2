package negocios.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private LocalDate dataDeNascimento;
	
	public Pessoa() {}
	
	public Pessoa(String nome, String cpf, LocalDate dataDeNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;

	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataDeNascimento() {
		return this.dataDeNascimento;
	}
	
	public String getDataDeNascimentoString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		return formatador.format(this.dataDeNascimento);
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) return false;
		if(super.equals(obj)) return true;	
		if(this.getClass() != obj.getClass()) return false;
		return ((Pessoa)obj).getCpf() != null && ((Pessoa)obj).getCpf().equals(this.cpf);
		
	}
	
	@Override
	public String toString() {
		return String.format("%-20s || %13s || %20s",
				this.nome,
				this.cpf,
				this.getDataDeNascimentoString());
	}
}
