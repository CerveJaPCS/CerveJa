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
import com.cerveja.Business.AssinaturaDAO;
import com.cerveja.Business.EstadoAssinatura;
import com.cerveja.Business.Pacote;
import com.cerveja.Business.PacoteDAO;
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
		
		
		/** Mostrar detalhes do pacote **/
		System.out.println(PacoteDAO.getInstance().getPacote(18).getPacoteID());
		System.out.println(PacoteDAO.getInstance().getPacote(18).getCreateDate());
		System.out.println(PacoteDAO.getInstance().getPacote(18).getPeriodicidade());
		System.out.println(PacoteDAO.getInstance().getPacote(18).getPrice());
		System.out.println(PacoteDAO.getInstance().getPacote(18).getQuantidade());
		System.out.println(PacoteDAO.getInstance().getPacote(18).getAssinatura().getDiaDebito());
		Set<Produto> prods = PacoteDAO.getInstance().getPacote(18).getProdutos();
		for(Produto p : prods){
			System.out.println(p.getNomeProduto());
			System.out.println(p.getQntPacote());
		}
		
//		AssinaturaDAO.getInstance().deleteAssinatura("teste5@mail.com", 16);
//		for(int i=11; i<17; i++){
//			PacoteDAO.getInstance().deletePacote(i);
//		}
		
//		TesteInfo.addUserInfo();
//		teste.addUser();

//		/** Adicionar assinatura e pacotes **/
//		int diaDebito = 23;
//
//		Assinatura assinatura = new Assinatura();
//		assinatura.addAssinatura(diaDebito);
//		teste.setAssinatura(assinatura);
//		teste.addAssinatura(teste.getAssinatura().getAssinaturaID());
//		
//		EstadoAssinatura ea = EstadoAssinatura.AguardandoPagamento;
//		teste.getAssinatura().setEstadoAssinatura(ea);
//
//		Cliente user = teste.getUserCliente("teste5@mail.com");
//		
////		Assinatura assinatura = new Assinatura();
////		assinatura.setAssinaturaID(16);
//		user.setAssinatura(assinatura);
//		user.getAssinatura();
//		Set<Produto> produtos1 = new HashSet<Produto>();
//		Produto p1 = ProdutoDAO.getInstance().getProduto(5);
//		Produto p2 = ProdutoDAO.getInstance().getProduto(6);
//		p1.setQntPacote(1);
//		p2.setQntPacote(2);
//		produtos1.add(p1);
//		produtos1.add(p2);
//		Pacote pacote1 = new Pacote(produtos1, LocalDate.now(), "semanal", true, user.getAssinatura());
//		pacote1.addPacote();
	
//		UserDAO userdao = UserDAO.getInstance();
//		userdao.addAssinatura("ccc@teste.com", 6);
//		System.out.println(c.getEmail());
//		TesteInfo.addUserInfo();
//		teste.addUser();
//		int id = teste.info.getUserInfoID();
//		teste.delUser("alexis.kenji@gmail.com", teste.info.getUserInfoID());

	}
}

