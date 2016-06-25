import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.cerveja.Business.Assinatura;
import com.cerveja.Business.EstadoAssinatura;
import com.cerveja.Business.Pacote;
import com.cerveja.Product.Produto;
import com.cerveja.Product.ProdutoDAO;
import com.cerveja.User.Cliente;
import com.cerveja.User.UserInfo;
import com.cerveja.User.UserType;

public class Main {
	public static void main(String[] args) throws IOException, ParseException, SQLException {
			Caso();
	}
	
	public static void Caso() throws IOException, ParseException, SQLException {
	    
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(
	            FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
	    
	    LocalDate TesteDN = LocalDate.parse( "19.10.1905", dateFormatter);
	    UserInfo TesteInfo = new UserInfo("teste5", "1123101993", "112322258", TesteDN, "Rua Exemplo, 4321", "(45)1999-5678");
	    UserType tipo = UserType.Cliente;	    
		Cliente teste = new Cliente(tipo, "teste5@mail.com", "senha123", TesteInfo);
		
		Cliente user = teste.getUserCliente("teste5@mail.com");
		
//		System.out.println(user.info.getNome());
		
//		TesteInfo.addUserInfo();
//		teste.addUser();
//		int diaDebito = 23;
		Assinatura assinatura = new Assinatura();
		assinatura.setAssinaturaID(16);
//		assinatura.addAssinatura(diaDebito);
//		teste.setAssinatura(assinatura);
//		teste.addAssinatura(teste.getAssinatura().getAssinaturaID());
//		
//		EstadoAssinatura ea = EstadoAssinatura.AguardandoPagamento;
//		teste.getAssinatura().setEstadoAssinatura(ea);
		user.setAssinatura(assinatura);
		user.getAssinatura();
//		System.out.println(user.getAssinatura().getDiaDebito());
//		System.out.println(assinatura.getDiaDebito());
		
		Set<Produto> produtos1 = new HashSet<Produto>();
		produtos1.add(ProdutoDAO.getInstance().getProduto(5));
		produtos1.add(ProdutoDAO.getInstance().getProduto(6));
//		for(Produto p : produtos1){
//			System.out.println(p.getProductId());
//			System.out.println(p.getNomeProduto());
//		}
//		
		
		Pacote pacote1 = new Pacote(produtos1, LocalDate.now(), "semanal", true, user.getAssinatura());
		pacote1.addPacote();
		
//		UserDAO userdao = UserDAO.getInstance();
//		userdao.addAssinatura("ccc@teste.com", 6);
//		System.out.println(c.getEmail());
//		TesteInfo.addUserInfo();
//		teste.addUser();
//		int id = teste.info.getUserInfoID();
//		teste.delUser("alexis.kenji@gmail.com", teste.info.getUserInfoID());

	}
}


