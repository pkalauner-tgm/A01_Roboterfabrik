package tgm.sew.hit.roboterfabrik.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.lager.Lager;
import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.mitarbeiter.Lieferant;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Testklasse fuer das {@link Sekretariat}
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class TestSekretariat {

	private Sekretariat s;
	private Lagermitarbeiter mockedLM;
	
	/**
	 * Diese Methode wird vor jedem Test aufgerufen
	 * Dabei wird ein neues Mock-Objekt von Lagermitarbeiter sowie
	 * das Sekretariat initialisiert
	 */
	@Before
	public void initSekretariat() {
		mockedLM = mock(Lagermitarbeiter.class);
		s = new Sekretariat(mockedLM, 10, 10);
	}
	
	/**
	 * Testen, ob die Mitarbeiter-ID korrekt generiert wird
	 */
	@Test
	public void testGeneriereMitarbeiterId() {
		assertEquals(1, s.generiereMitarbeiterId());
	}
	
	/**
	 * Testen, ob die Mitarbeiter-ID auch nach mehrmaligem Aufruf
	 * korrekt generiert wird
	 */
	@Test
	public void testGeneriereMitarbeiterId2() {
		for (int i=0; i<999; i++)
			s.generiereMitarbeiterId();
		assertEquals(1000, s.generiereMitarbeiterId());
	}
	
	/**
	 * Testen, ob die Threadee-ID korrekt generiert wird
	 */
	@Test
	public void testGeneriereThreadeeId() {
		assertEquals(1, s.generiereThreadeeId());
	}
	
	/**
	 * Testen, ob eine Exception geworfen wird,
	 * wenn zu viele Threadee-IDs angefordert wurden
	 */
	@Test (expected=RuntimeException.class)
	public void testGeneriereThreadeeId2() {
		s.setThreadeeId(Long.MAX_VALUE);
		s.generiereThreadeeId();
	}
	
	/**
	 * Testen, ob eine Lieferung korrekt angenommen wird
	 * 
	 * @throws IOException Falls ein Fehler beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testEmpfangeLieferung() throws IOException {
		Lager lager = new Lager();
		File f = new File("src/test/resources");
		Lagermitarbeiter l = new Lagermitarbeiter(lager, f);
		Sekretariat s = new Sekretariat(l, 10, 10);
		
		Stack<Teil> teile = new Stack<Teil>();
		int[] numbers = {1,2,3,4,5};
		teile.add(new Teil(Teiltyp.ARM, numbers));
		
		s.empfangeLieferung(teile);
		assertEquals(new Integer(1), lager.getAnzahlTeile().get(Teiltyp.ARM));
		
		l.close();
	}
	
	/**
	 * Testen, ob eine Lieferung korrekt angenommen wird
	 * 
	 * @throws IOException Falls ein Fehler beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testEmpfangeLieferung2() throws IOException {
		Lager lager = new Lager();
		File f = new File("src/test/resources");
		Lagermitarbeiter l = new Lagermitarbeiter(lager, f);
		Sekretariat s = new Sekretariat(l, 10, 10);
		
		Stack<Teil> teile = new Stack<Teil>();
		int[] numbers = {1,2,3,4,5};
		teile.add(new Teil(Teiltyp.ARM, numbers));
		teile.add(new Teil(Teiltyp.ARM, numbers));
		
		s.empfangeLieferung(teile);
		assertEquals(new Integer(2), lager.getAnzahlTeile().get(Teiltyp.ARM));
		
		l.close();
	}
	

}
