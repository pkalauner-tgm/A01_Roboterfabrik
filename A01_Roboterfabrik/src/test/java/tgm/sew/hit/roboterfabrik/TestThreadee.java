package tgm.sew.hit.roboterfabrik;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Testklasse fuer den {@link Threadee}
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class TestThreadee {
	
	private Threadee t;
	private int[] numbers = {1,2,3,4,5};

	/**
	 * Diese Methode wird vor jedem Test aufgerufen.
	 * Dabei wird ein neuer Threadee initialisiert
	 */
	@Before
	public void testInit() {
		t = new Threadee(1,2);
	}
	
	/**
	 * Testen, ob bei addTeil das korrekte Teil zum Threadee
	 * hinzugefuegt wurde
	 */
	@Test
	public void testAddTeilAugeLinks() {
		Teil teil = new Teil(Teiltyp.AUGE, numbers);
		t.addTeil(teil);
		assertEquals(teil, t.getAugeLinks());
	}
	
	/**
	 * Testen, ob bei addTeil das korrekte Teil zum Threadee
	 * hinzugefuegt wurde
	 */
	@Test
	public void testAddTeilAugeRechts() {
		Teil teil1 = new Teil(Teiltyp.AUGE, numbers);
		Teil teil2 = new Teil(Teiltyp.AUGE, numbers);
		t.addTeil(teil1);
		t.addTeil(teil2);
		assertEquals(teil2, t.getAugeRechts());
	}
	
	/**
	 * Testen, ob bei addTeil das korrekte Teil zum Threadee
	 * hinzugefuegt wurde
	 */
	@Test
	public void testAddTeilRumpf() {
		Teil teil = new Teil(Teiltyp.RUMPF, numbers);
		t.addTeil(teil);
		assertEquals(teil, t.getRumpf());
	}
	
	/**
	 * Testen, ob bei addTeil das korrekte Teil zum Threadee
	 * hinzugefuegt wurde
	 */
	@Test
	public void testAddTeilArmLinks() {
		Teil teil = new Teil(Teiltyp.ARM, numbers);
		t.addTeil(teil);
		assertEquals(teil, t.getArmLinks());
	}
	
	/**
	 * Testen, ob bei addTeil das korrekte Teil zum Threadee
	 * hinzugefuegt wurde
	 */
	@Test
	public void testAddTeilArmRechts() {
		Teil teil1 = new Teil(Teiltyp.ARM, numbers);
		Teil teil2 = new Teil(Teiltyp.ARM, numbers);
		t.addTeil(teil1);
		t.addTeil(teil2);
		assertEquals(teil2, t.getArmRechts());
	}
	
	/**
	 * Testen, ob bei addTeil das korrekte Teil zum Threadee
	 * hinzugefuegt wurde
	 */
	@Test
	public void testAddTeilKettenantrieb() {
		Teil teil = new Teil(Teiltyp.KETTENANTRIEB, numbers);
		t.addTeil(teil);
		assertEquals(teil, t.getKettenantrieb());
	}
	
	/**
	 * Testen, ob toString einen korrekten String des Roboters ausgibt
	 */
	@Test
	public void testToString() {
		t.addTeil(new Teil(Teiltyp.KETTENANTRIEB, numbers));
		t.addTeil(new Teil(Teiltyp.AUGE, numbers));
		t.addTeil(new Teil(Teiltyp.ARM, numbers));
		t.addTeil(new Teil(Teiltyp.RUMPF, numbers));
		t.addTeil(new Teil(Teiltyp.ARM, numbers));
		t.addTeil(new Teil(Teiltyp.AUGE, numbers));
		assertEquals("Threadee-ID1,Mitarbeiter-ID2,"
				+ "Auge,1,2,3,4,5,Auge,1,2,3,4,5,"
				+ "Rumpf,1,2,3,4,5,"
				+ "Arm,1,2,3,4,5,Arm,1,2,3,4,5,"
				+ "Kettenantrieb,1,2,3,4,5", t.toString());
	}
	
	/**
	 * Testen, ob die Ids des Threadees korrekt gesetzt wurde
	 */
	@Test
	public void testIds() {
		assertEquals(2, t.getMitarbeiterId());
		assertEquals(1, t.getId());
	}

}
