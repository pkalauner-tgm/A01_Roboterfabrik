package tgm.sew.hit.roboterfabrik.lager;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Testklasse fuer das {@link Lager}
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class TestLager {

	/**
	 * Testen, ob die Map mit den Teilen korrekt initalisiert wird
	 */
	@Test
	public void testInit() {
		Lager l = new Lager();
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		assertEquals(new Integer(0), teile.get(Teiltyp.AUGE));
		assertEquals(new Integer(0), teile.get(Teiltyp.ARM));
		assertEquals(new Integer(0), teile.get(Teiltyp.KETTENANTRIEB));
		assertEquals(new Integer(0), teile.get(Teiltyp.RUMPF));
	}
	
	/**
	 * Testen, ob ein Teil korrekt zum Lager hinzugefuegt wird
	 */
	@Test
	public void testAddTeil() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		assertEquals(new Integer(1), teile.get(Teiltyp.AUGE));
	}
	
	/**
	 * Testen, ob meherer Teile korrekt zum Lager hinzugefuegt werden
	 */
	@Test
	public void testAddTeil2() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		l.addTeil(new Teil(Teiltyp.RUMPF, numbers));
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		assertEquals(new Integer(2), teile.get(Teiltyp.AUGE));
		assertEquals(new Integer(1), teile.get(Teiltyp.RUMPF));
	}
	
	/**
	 * Testen, ob ein Teil korrekt aus dem Lager entfernt wird
	 */
	@Test
	public void testRemoveTeil1() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		l.removeTeil(Teiltyp.AUGE);
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		assertEquals(new Integer(1), teile.get(Teiltyp.AUGE));
	}
	
	/**
	 * Testen, ob mehrere Teile korrekt aus dem Lager entfernt werden
	 */
	@Test
	public void testRemoveTeil2() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		l.addTeil(new Teil(Teiltyp.AUGE, numbers));
		l.removeTeil(Teiltyp.AUGE);
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		assertEquals(new Integer(1), teile.get(Teiltyp.AUGE));
	}
	
	/**
	 * Testen, ob bei Entfernen eines Teils, welches nicht mehr vorhanden ist,
	 * eine Exception geworfen wird
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testRemoveTeil3() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		l.removeTeil(Teiltyp.AUGE);
	}
	

	/**
	 * Testen, ob kein Teil vom Typ Auge vorhanden ist
	 */
	@Test
	public void testTeileDa() {
		Lager l = new Lager();
		assertEquals(false, l.teileDa(1, Teiltyp.AUGE));
	}
	
	/**
	 * Testen, ob ein Teil vom Typ Kettenatrieb vorhanden ist,
	 * nachdem dieses zum Lager hinzugefuegt wurde
	 */
	@Test
	public void testTeileDa2() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		l.addTeil(new Teil(Teiltyp.KETTENANTRIEB, numbers));
		assertEquals(true, l.teileDa(1, Teiltyp.KETTENANTRIEB));
	}
	
	/**
	 * Testen, ob ein Teile vom Typ Kettenatrieb vorhanden ist,
	 * nachdem zwei von diesem Typ zum Lager hinzugefuegt wurde
	 */
	@Test
	public void testTeileDa3() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		l.addTeil(new Teil(Teiltyp.KETTENANTRIEB, numbers));
		l.addTeil(new Teil(Teiltyp.KETTENANTRIEB, numbers));
		assertEquals(true, l.teileDa(1, Teiltyp.KETTENANTRIEB));
	}
	
	/**
	 * Testen, ob 2 Teile vom Typ Rumpf vorhanden sind,
	 * obwohl nur eines hinzugefuegt wurde
	 */
	@Test
	public void testTeileDa4() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		l.addTeil(new Teil(Teiltyp.RUMPF, numbers));
		assertEquals(false, l.teileDa(2, Teiltyp.RUMPF));
	}
	
	/**
	 * Testen, ob ein Teile vom Typ Arm vorhanden ist,
	 * nachdem es hinzugefuegt und entfernt wurde
	 */
	@Test
	public void testTeileDa5() {
		Lager l = new Lager();
		int[] numbers = {1,2,3,4,5};
		Map<Teiltyp, Integer> teile = l.getAnzahlTeile();
		l.addTeil(new Teil(Teiltyp.ARM, numbers));
		l.removeTeil(Teiltyp.ARM);
		assertEquals(false, l.teileDa(1, Teiltyp.ARM));
	}

}
