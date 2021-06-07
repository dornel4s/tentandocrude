package camadapersistencia;

public class BDException extends Exception {
	
	private Exception exception;

	public BDException(Exception ex) {
		
	        this(ex, "");
	    }

	public BDException(String mensagem) {
	        this(null, mensagem);
	    }

	public BDException(Exception ex, String mensagem) {
	        super(mensagem);
	        exception = ex;
	    }

	public void printStackTrace() {
		super.printStackTrace();
		if (exception != null) {
			exception.printStackTrace();
		}
	}

	public String getMessage() {
		String resposta = super.getMessage();
		if (exception != null) {
			resposta = resposta + "\n" + exception.getMessage();
		}
		return resposta;
	}

}
