package com.cerveja.User;

import java.sql.SQLException;

import com.cerveja.Business.*;

public class Cliente extends Usuario {
	
	Assinatura assinatura;
	private UserDAO userdao = UserDAO.getInstance();
	private AssinaturaDAO assinaturadao = AssinaturaDAO.getInstance();
	
	public Cliente(UserType userType, String email,
			String senha, UserInfo info) {
		super(userType, email, senha, info);
	}
	

	public Assinatura getAssinatura() throws SQLException {
		this.assinatura = assinaturadao.getAssinatura(assinatura.getAssinaturaID());
		return this.assinatura;
	}

	public void setAssinatura(Assinatura assinatura) throws SQLException {
		this.assinatura = assinatura;
	}
	
	public void addAssinatura(int id) throws SQLException{
		userdao.addAssinatura(this.getEmail(), id);
	}
	
}
