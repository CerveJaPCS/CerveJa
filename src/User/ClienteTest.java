package User;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.junit.Test;

public class ClienteTest {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(
    FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
	
    
	LocalDate TesteDN = LocalDate.parse("27.11.1991", dateFormatter);
	UserInfo TesteInfo = new UserInfo("Teste", "527.489.544-88", "85.121.123-4", TesteDN, "Rua Teste, 123", "(11) 1232-4569");
	UserType tipo = UserType.Cliente;
	Cliente teste = new Cliente("UserTest", tipo, "teste.cerveja@pcs2419.com", "senha123", TesteInfo);

	
	@Test
	public void testAtivar() {
		teste.ativar();
		assertTrue(teste.getAtivo());
	}

	@Test
	public void testGetUserID() {
		assertNotNull(teste.getUserID());
		assertEquals("UserTest", teste.getUserID());
	}

	@Test
	public void testGetUserType() {
		assertNotNull(teste.getUserType());
		assertEquals(UserType.Cliente, teste.getUserType());
	}

	@Test
	public void testGetEmail() {
		assertNotNull(teste.getEmail());
		assertEquals("teste.cerveja@pcs2419.com", teste.getEmail());
	}

	@Test
	public void testGetSenha() {
		assertNotNull(teste.getSenha());
		assertEquals("senha123", teste.getSenha());
	}

	@Test
	public void testGetAtivo() {
		assertNotNull(teste.getAtivo());
		assertFalse(teste.getAtivo());
	}


}
