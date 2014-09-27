package tgm.sew.hit.roboterfabrik.mitarbeiter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Testklasse fuer den {@link Lieferant}en
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class TestLieferant {
	
	private Lieferant l;
	private Sekretariat mockedS;
	
	
	/**
	 * Vor allen Tests wird folgende Methode aufgerufen.
	 * Dabei wird ein Mock-Objekt von Sekretariat initialisiert sowie
	 * ein neuer Lieferant initialisiert
	 */
	@Before
	public void testInit() {
		mockedS = mock(Sekretariat.class);
		l = new Lieferant(mockedS);
	}

	/**
	 * Testen, ob die generierten Teile auch wirklich den
	 * angegebenen Teiltyp haben
	 */
	@Test
	public void testGeneriereTeile() {
		Stack<Teil> teile = l.generiereTeile(Teiltyp.AUGE);
		for (Teil t: teile) {
			assertEquals(Teiltyp.AUGE, t.getTyp());
		}
	}
	
	/**
	 * Testen, ob wie in der Variable TEILE_PRO_LIEFERUNG angegeben
	 * genau 10 Teile geliefert werden
	 */
	@Test
	public void testGeneriereTeile2() {
		Stack<Teil> teile = l.generiereTeile(Teiltyp.AUGE);
		assertEquals(10, teile.capacity());
	}
	
	//TODO Run testen
	

}
