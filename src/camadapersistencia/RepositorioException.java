package camadapersistencia;

public class RepositorioException extends Exception {
	private Exception exception;

	public RepositorioException(Exception ex) {
		exception = ex;
	}

	public void printStackTrace() {
		if (exception != null) {
			exception.printStackTrace();
		}
	}

	public String getMessage() {
		String resposta = "";
		if (exception != null) {
			resposta = exception.getMessage();
		}
		return resposta;
	}
}
