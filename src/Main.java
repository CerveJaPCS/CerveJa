
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;


import User.*;

public class Main {
		private static UserDAO myUser = null;
		
	public static void main(String[] args) throws IOException, ParseException, SQLException {
		myUser = UserDAO.getInstance();
		
		Caso();
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
		Cliente teste = new Cliente(myUser);
		teste.info.setNome(TesteNome);
		teste.insertUser();
		System.out.println("Usuário inserido com sucesso!");
		
	    //UserType tipo = UserType.Cliente;
	    //UserType tipoa = UserType.Administrador;
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
