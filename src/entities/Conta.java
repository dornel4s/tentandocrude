package entities;

import java.sql.Date;

public class Conta {
	private int id;
	private String nickname;
	private int posicao;
	private Date data;
	private String email;
	private String senha;
	private String login;

	public Conta(int id_conta, String nickname, String email, String senha, String login, Date data) {
		this.id = id_conta;
		this.nickname = nickname;
		this.email = email;
		this.senha = senha;
		this.login = login;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id_conta) {
		this.id = id_conta;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	

}
