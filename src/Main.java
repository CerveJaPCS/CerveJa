
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;


import User.*;

public class Main {
	private static final String URL = "jdbc:mysql://labsoft.pcs.usp.br:3306/cerveja";
	private static final String USER = "cerveja";
	private static final String PASS = "cerveja";
	private static Connection CONN = null;
	private static UserDAO userDAO;

	public static void main(String[] args) throws IOException, ParseException, SQLException {
		CONN = getConnection();
		userDAO = new UserDAO(CONN);
		
		Caso();
	}
	
	public static Connection getConnection() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e){
			throw new SQLException("Erro ao abrir conex�o com o BD", e);
		}
	}
	
	public static void Caso() throws IOException, ParseException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
//	    System.out.print("Enter UserID: ");
//	    String TesteUserID = br.readLine();
//	    System.out.print("Enter E-Mail: ");
//	    String TesteEMail = br.readLine();
//	    System.out.print("Enter Senha: ");
//	    String TesteSenha = br.readLine();
	    System.out.print("Enter Nome: ");
	    String TesteNome = br.readLine();
//	    System.out.print("Enter CPF: ");
//	    String TesteCPF = br.readLine();
//	    System.out.print("Enter RG: ");
//	    String TesteRG = br.readLine();
//	    System.out.print("Enter DataDeNascimento: ");
//	    String TesteDataNasc = br.readLine();
//	    System.out.print("Enter Endereco: ");
//	    String TesteEnd = br.readLine();
//	    System.out.print("Enter Telefone: ");
//	    String TesteTel = br.readLine();
		
	    UserType tipo = UserType.Cliente;
	    //UserType tipoa = UserType.Administrador;
	    Cliente teste = new Cliente(userDAO);
	    teste.info.setNome(TesteNome);
	    teste.insertUser();
		//Admin testea = new Admin(TesteUserID, tipoa, TesteEMail, TesteSenha, TesteNome, TesteCPF, TesteRG, TesteDataNasc, TesteEnd, TesteTel);
		
	    
		
//	    System.out.println(teste.getUserID());
//		System.out.println(teste.getUserType());
//		System.out.println(teste.getEmail());
//		System.out.println(teste.getSenha());
//		System.out.println(teste.getAtivo());
//		System.out.println(teste.info.getNome());
//		System.out.println(teste.info.getCPF());
//		System.out.println(teste.info.getRG());
//		System.out.println(teste.info.getDataNascimento());
//		System.out.println(teste.info.getEndereco());
//		System.out.println(teste.info.getTelefone());

	}
}
