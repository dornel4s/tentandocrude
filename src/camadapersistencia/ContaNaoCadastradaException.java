package camadapersistencia;

public class ContaNaoCadastradaException extends Exception {

	public ContaNaoCadastradaException() {
		super("Conta n�o cadastrada");
		
	}
	public ContaNaoCadastradaException(String exception) {
		super(exception);
		
	}
	
}

