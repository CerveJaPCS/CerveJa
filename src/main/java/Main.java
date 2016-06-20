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
	    
	    LocalDate TesteDN = LocalDate.parse( "09.08.1990", dateFormatter);
	    UserInfo TesteInfo = new UserInfo("TesteMail", "10101010101", "112223334", TesteDN, "Rua TesteMail, 123", "(45)1234-5678");
	    UserType tipo = UserType.Cliente;	    
		Cliente teste = new Cliente(tipo, "alexis.kenji@gmail.com", "teste123", TesteInfo);
		TesteInfo.addUserInfo();
		teste.addUser();

	}
}


