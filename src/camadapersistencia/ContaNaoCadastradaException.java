package camadapersistencia;

public class ContaNaoCadastradaException extends Exception {

	public ContaNaoCadastradaException() {
		super("Conta não cadastrada");
		
	}
	public ContaNaoCadastradaException(String exception) {
		super(exception);
		
	}
	
}

