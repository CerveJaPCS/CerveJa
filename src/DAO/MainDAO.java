package DAO;

import java.sql.SQLException;

public class MainDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestDAO dao = TestDAO.getInstance();
		try{
			dao.insert("Nome Teste");
			dao.printTestTable();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
