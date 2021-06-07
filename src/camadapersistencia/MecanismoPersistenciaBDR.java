package camadapersistencia;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class MecanismoPersistenciaBDR implements MecanismoPersistencia {

	private Connection conexao;
	private String classeDoDriver;
	private String url;
	private String login;
	private String senha;

	public MecanismoPersistenciaBDR(String url, String login, String senha, String classeDoDriver) throws BDException {
		this.classeDoDriver = classeDoDriver;
		this.url = url;
		this.login = login;
		this.senha = senha;

	}

	public synchronized void conectar() throws BDException {

		try {
			if (login != null)

				conexao = DriverManager.getConnection(url, login, senha);

			else
				conexao = DriverManager.getConnection(url);

		} catch (Exception e) {
			throw new BDException(e, "EXC_CONECTAR");
		}

	}

	public synchronized void desconectar() throws BDException {
		try {
			conexao.close();
		} catch (Exception e) {
			throw new BDException(e, "EXC_DESCONECTAR");
		}
	}

	public synchronized void iniciarTransacao() throws BDException {
		try {
			conexao.setAutoCommit(false);
		} catch (Exception e) {
			throw new BDException(e,

					"EXC_INICIAR_TRANSACAO");
		}
	}

	public synchronized void confirmarTransacao() throws BDException {
		try {
			conexao.commit();
		} catch (Exception e) {
			throw new BDException(e,

					"EXC_CONFIRMAR_TRANSACAO");
		}
	}

	public synchronized void cancelarTransacao() throws BDException {
		try {
			conexao.rollback();
		} catch (Exception e) {
			throw new BDException(e,

					"EXC_CANCELAR_TRANSACAO");
		}
	}

	public synchronized Object getCanalComunicacao() {
		return conexao;
	}
}
