package camadapersistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import negocio.Conta;

public class RepositorioContaMySQL implements RepositorioContas {

	private MecanismoPersistenciaBDR mecanismoPersistencia;

	public RepositorioContaMySQL(MecanismoPersistenciaBDR mecanismoPersistencia) {
		this.mecanismoPersistencia = mecanismoPersistencia;
		try {
			mecanismoPersistencia.conectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserir(Conta conta) throws RepositorioException {
		if (conta == null) {
			throw new IllegalArgumentException("Conta inválida");
		}
		Connection con = null;
		try {
			String query = "INSERT INTO CONTA (id, nickname, posicao, _data, email, login, senha) VALUES('"
					+ conta.getId() + "' , '" + conta.getNickname() + "' , '" + conta.getPosicao() + "','"
					+ conta.getData() + "' , '" + conta.getEmail() + "' , '" + conta.getSenha() + "', '"
					+ conta.getLogin() + "')";
			con = (Connection)

			mecanismoPersistencia.getCanalComunicacao();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			throw new RepositorioException(ex);
		}
	}

	public boolean existe(String numero) throws RepositorioException {
		if (numero == null) {
			throw new IllegalArgumentException("Conta inválida");
		}
		boolean resposta = false;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM CONTA WHERE id='" +

					numero + "'";
			con = (Connection) mecanismoPersistencia.getCanalComunicacao();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			resposta = rs.next();
		} catch (SQLException ex) {
			throw new RepositorioException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception exception1) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception exception2) {
				}
			}
		}
		return resposta;
	}

	public Conta procurar(String numero) throws RepositorioException, ContaNaoCadastradaException {
		if (numero == null) {
			throw new IllegalArgumentException("Conta inválida");
		}
		Connection con = null;
		Statement stmt = null;
		Conta resposta = null;
		ResultSet rs = null;
		try {
			String query = "SELECT nickname FROM CONTA WHERE posicao='" + numero + "'";
			con = (Connection) mecanismoPersistencia.getCanalComunicacao();

			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				resposta = new Conta(rs.getString("id"), rs.getString("nickname"), rs.getInt("posicao"), rs.getString("email"),
						rs.getString("senha"), rs.getString("login"), rs.getDate("data"));
			} else {
				throw new ContaNaoCadastradaException();
			}
		} catch (SQLException ex) {
			throw new RepositorioException(ex);
		} catch (Exception ex) {
			throw new RepositorioException(ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception exception1) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception exception2) {
				}
			}

		}
		return resposta;
	}

	public void atualizar(Conta conta) throws RepositorioException, ContaNaoCadastradaException {
		if (conta == null) {
			throw new IllegalArgumentException("Conta inválida");
		}
		Connection con = null;
		try {
			String query = "UPDATE CONTA SET nickname='" + conta.getNickname() + "'" + ", senha = " + conta.getSenha()
					+ " WHERE " + "id ='" + conta.getId() + "'" + "";
			con = (Connection) mecanismoPersistencia.getCanalComunicacao();
			Statement stmt = con.createStatement();
			int numLinhas = stmt.executeUpdate(query);
			stmt.close();
			if (numLinhas == 0) {
				if (!existe(conta.getId())) {

					throw new ContaNaoCadastradaException();

				}
			}
		} catch (SQLException ex) {
			throw new RepositorioException(ex);
		}
	}

	public void remover(String nickname) throws RepositorioException, ContaNaoCadastradaException {
		if (nickname == null) {
			throw new IllegalArgumentException("Conta inválida");
		}
		Connection con = null;
		try {
			String query = "DELETE FROM CONTA WHERE nickname ='" +

					nickname + "'";
			con = (Connection) mecanismoPersistencia.getCanalComunicacao();

			Statement stmt = con.createStatement();
			int numLinhas = stmt.executeUpdate(query);
			stmt.close();
			if (numLinhas == 0) {
				throw new ContaNaoCadastradaException();
			}
		} catch (SQLException ex) {
			throw new RepositorioException(ex);
		}

	}

	public Enumeration retornaTodos() throws RepositorioException {
		RepositorioContasArray resposta = new RepositorioContasArray(100);
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT numero, nome_cliente , saldo FROM CONTA";
			con = (Connection) mecanismoPersistencia.getCanalComunicacao();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				resposta.inserir(new Conta(rs.getString("id"),

						rs.getString("nickname"), rs.getInt("posicao"), rs.getString("email"), rs.getString("senha"), rs.getString("login"), rs.getDate("data")));
			}
		} catch (SQLException ex) {

			throw new RepositorioException(ex);

		} finally {
			if (rs != null) {

				try {
					rs.close();
				} catch (Exception ex1) {

					throw new RepositorioException(ex1);

				}

			}
			if (stmt != null) {

				try {
					stmt.close();
				} catch (Exception ex2) {

					throw new RepositorioException(ex2);

				}

			}
		}
		return resposta;

	}
}