package exceptions;

public class UsuarioJaExisteException extends Exception {
	
	private static final long serialVersionUID = -3701073496865455883L;
	
	private Object usuario;
    
	public UsuarioJaExisteException(Object usuario) {
		super("Usu�rio j� est� cadastrado no reposit�rio e n�o pode ser adicionado novamente");
		this.usuario = usuario;   
	}

	public Object getUsuario() {
		return usuario;
	}

	public void setUsuario(Object usuario) {
		this.usuario = usuario;
	}
	
}
