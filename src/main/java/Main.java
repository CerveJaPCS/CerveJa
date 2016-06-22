import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;


import com.cerveja.User.*;

import java.time.LocalDate;
import java.time.format.*;

public class Main {
	public static void main(String[] args) throws IOException, ParseException, SQLException {
			Caso();
	}
	
	public static void Caso() throws IOException, ParseException, SQLException {
	    
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(
	            FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
	    
	    LocalDate TesteDN = LocalDate.parse( "09.08.1991", dateFormatter);
	    UserInfo TesteInfo = new UserInfo("TesteMail2", "10101010122", "112223358", TesteDN, "Rua TesteMail, 123", "(45)1999-5678");
	    UserType tipo = UserType.Cliente;	    
		Cliente teste = new Cliente(tipo, "alexis.kenji@gmail.com", "teste1234", TesteInfo);
//		TesteInfo.addUserInfo();
//		teste.addUser();
//		int id = teste.info.getUserInfoID();
//		teste.delUser("alexis.kenji@gmail.com", teste.info.getUserInfoID());

	}
}


