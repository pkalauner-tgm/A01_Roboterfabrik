package tgm.sew.hit.roboterfabrik.mitarbeiter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.lager.Lager;
import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Testklasse fuer den {@link Montagemitarbeiter}
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class TestMontagemitarbeiter {

	private Montagemitarbeiter mm;
	private Lagermitarbeiter mockedLM;
	private Sekretariat mockedS;
	
	/**
	 * Diese Methode wird vor jedem Test aufgerufen.
	 * Dabei werden Mock-Objekte von Lagermitarbeiter und Sekretariat
	 * sowie ein Montagemitarbeiter initialisiert
	 */
	@Before
	public void testInit() {
		Lagermitarbeiter mockedLM = mock(Lagermitarbeiter.class);
		Sekretariat mockedS = mock(Sekretariat.class);
		mm = new Montagemitarbeiter(3, mockedLM, mockedS);
	}
	
	/**
	 * Testen, ob ein Threadee korrekt zusammengebaut wird
	 */
	@Test
	public void testZusammenbauen() {
		Stack<Teil> teile = new Stack<Teil>();
		int numbers[] = {1,2,3,4,5};
		teile.add(new Teil(Teiltyp.AUGE, numbers));
		teile.add(new Teil(Teiltyp.AUGE, numbers));
		teile.add(new Teil(Teiltyp.RUMPF, numbers));
		teile.add(new Teil(Teiltyp.ARM, numbers));
		teile.add(new Teil(Teiltyp.ARM, numbers));
		teile.add(new Teil(Teiltyp.KETTENANTRIEB, numbers));
		Threadee neu = mm.zusammenbauen(teile, 1);
		assertEquals("Threadee-ID1,Mitarbeiter-ID3,"
				+ "Auge,1,2,3,4,5,Auge,1,2,3,4,5,"
				+ "Rumpf,1,2,3,4,5,"
				+ "Arm,1,2,3,4,5,Arm,1,2,3,4,5,"
				+ "Kettenantrieb,1,2,3,4,5", neu.toString());
	}
	
	//TODO run testen

}
