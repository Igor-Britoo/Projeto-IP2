package exceptions;

public class UsuarioNaoExisteException extends Exception{

	private static final long serialVersionUID = -3953316493082541228L;
	
	private Object usuario;
	
	public UsuarioNaoExisteException(Object usuario) {
		super("Esse usuario não existe no repositorio");
		this.usuario = usuario;
	}

	public Object getUsuario() {
		return usuario;
	}

	public void setUsuario(Object usuario) {
		this.usuario = usuario;
	}
	
}
