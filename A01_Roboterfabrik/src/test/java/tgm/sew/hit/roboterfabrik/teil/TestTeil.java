package tgm.sew.hit.roboterfabrik.teil;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.mitarbeiter.Lieferant;

/**
 * Testklasse fuer ein {@link Teil}
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class TestTeil {
	
	Teil t;
	private int[] numbers = {1,2,3,4,5};

	/**
	 * Diese Methode wird vor jedem Test aufgerufen.
	 * Dabei wird ein neues Teil initialisiert
	 */
	@Before
	public void testInit() {
		t = new Teil(Teiltyp.AUGE, numbers);	
	}
	
	/**
	 * Testen, ob der korrekte Typ zurueckgegeben wird
	 */
	@Test
	public void testGetTyp() {
		assertEquals(Teiltyp.AUGE, t.getTyp());
	}
	
	/**
	 * Testen, ob der korrekte Typ gesetzt wird
	 */
	@Test
	public void testSetTyp() {
		t.setTyp(Teiltyp.ARM);
		assertEquals(Teiltyp.ARM, t.getTyp());
	}
	
	/**
	 * Testen, ob die korrekten Zahlen des Teils zurueckgegeben werden
	 */
	@Test
	public void testGetNumbers() {
		for (int i=1; i<=5; i++)
			assertEquals(i, t.getNumbers()[i-1]);
	}
	
	/**
	 * Testen, ob ein Teil korrekt zu einem String zusammengesetzt wird
	 */
	@Test
	public void testToString() {
		assertEquals("Auge,1,2,3,4,5", t.toString());
	}
	
	/**
	 * Testen, ob ein Teil korrekt zu einem String zusammengesetzt wird
	 */
	@Test
	public void testToString2() {
		t = new Teil(Teiltyp.RUMPF, numbers);
		assertEquals("Rumpf,1,2,3,4,5", t.toString());
	}
	
	/**
	 * Testen, ob ein Teil korrekt zu einem String zusammengesetzt wird
	 */
	@Test
	public void testToString3() {
		t = new Teil(Teiltyp.ARM, numbers);
		assertEquals("Arm,1,2,3,4,5", t.toString());
	}
	
	/**
	 * Testen, ob ein Teil korrekt zu einem String zusammengesetzt wird
	 */
	@Test
	public void testToStrin42() {
		t = new Teil(Teiltyp.KETTENANTRIEB, numbers);
		assertEquals("Kettenantrieb,1,2,3,4,5", t.toString());
	}

}
