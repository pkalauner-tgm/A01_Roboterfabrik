package tgm.sew.hit.roboterfabrik.lager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;


public class TestLagermitarbeiter {

	private Lagermitarbeiter lm;
	private Lager mockedLager;
	private File f;

	/**
	 * Vor allen Tests wird der Pfad fuer die Files initialisiert sowie als neues File angelegt.
	 * Es wird ebenfalls ein Mock-Objekt von Lager initialisiert
	 */
	@Before
	public void initAll() {
		f = new File("./src/test/resources");
		mockedLager = mock(Lager.class);
		File file = new File(f, "rumpf.csv");
		if (file.exists())
			file.delete();

	}

	/**
	 * Testen, ob die Lager-Files ohne Exception initialisiert werden
	 */
	@Test
	public void testInitRafs1() {
		lm = new Lagermitarbeiter(mockedLager, f);
	}

	/**
	 * Testen, ob die Lager-Files, falls vorhanden, ohne Exception ueberschrieben werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testInitRafs2() throws IOException {
		new RandomAccessFile(new File(f,"auslieferung.csv"), "rw").close();
		new RandomAccessFile(new File(f, "arm.csv"), "rw").close();
		lm = new Lagermitarbeiter(mockedLager, f);
	}

	/**
	 * Testen, ob ein Threadee beim Einlagern korrekt in das File geschrieben wird
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testThreadeeEinlagern() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);

		Threadee mockedThreadee = mock(Threadee.class);
		when(mockedThreadee.toString()).thenReturn("Testthreadee");

		lm.threadeeEinlagern(mockedThreadee);
		RandomAccessFile raf = new RandomAccessFile(new File(f, "auslieferung.csv"), "r");
		assertEquals("Testthreadee", raf.readLine());
		raf.close();
	}

	/**
	 * Testen, ob mehrere Threadees beim Einlagern korrekt in das File geschrieben werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testThreadeeEinlagern2() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);

		Threadee mockedThreadee = mock(Threadee.class);
		when(mockedThreadee.toString()).thenReturn("Testthreadee");

		lm.threadeeEinlagern(mockedThreadee);
		lm.threadeeEinlagern(mockedThreadee);
		RandomAccessFile raf = new RandomAccessFile(new File(f, "auslieferung.csv"), "r");
		assertEquals("Testthreadee", raf.readLine());
	}

	/**
	 * Testen, ob ein Teil beim Einlagern korrekt in das File geschrieben wird
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testEinlagern() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		int[] numbers = {1, 2, 3, 4, 5};

		Stack<Teil> in = new Stack();
		in.add(new Teil(Teiltyp.AUGE, numbers));
		lm.einlagern(in);

		RandomAccessFile raf = new RandomAccessFile(new File(f, "auge.csv"), "r");
		assertEquals("Auge,1,2,3,4,5", raf.readLine());	
	}

	/**
	 * Testen, ob mehrere gleiche Teile beim Einlagern korrekt in das File geschrieben werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testEinlagern2() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		int[] numbers = {1, 2, 3, 4, 5};

		Stack<Teil> in = new Stack();
		in.add(new Teil(Teiltyp.AUGE, numbers));
		in.add(new Teil(Teiltyp.AUGE, numbers));
		lm.einlagern(in);

		RandomAccessFile raf = new RandomAccessFile(new File(f, "auge.csv"), "r");

		assertEquals("Auge,1,2,3,4,5", raf.readLine());
		assertEquals("Auge,1,2,3,4,5", raf.readLine());	
	}

	/**
	 * Testen, ob mehrere unterschiedliche Teile beim Einlagern korrekt in die Files geschrieben werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testEinlagern3() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		int[] numbers = {1, 2, 3, 4, 5};

		Stack<Teil> in = new Stack();
		in.add(new Teil(Teiltyp.KETTENANTRIEB, numbers));
		in.add(new Teil(Teiltyp.ARM, numbers));
		in.add(new Teil(Teiltyp.RUMPF, numbers));
		lm.einlagern(in);
		
		lm.close();
		RandomAccessFile rafArm = new RandomAccessFile(new File(f, "arm.csv"), "r");
		RandomAccessFile rafKettenantrieb = new RandomAccessFile(new File(f, "kettenantrieb.csv"), "r");
		RandomAccessFile rafRumpf = new RandomAccessFile(new File(f, "rumpf.csv"), "r");


		assertEquals("Kettenantrieb,1,2,3,4,5", rafKettenantrieb.readLine());
		assertEquals("Arm,1,2,3,4,5", rafArm.readLine());
		assertEquals("Rumpf,1,2,3,4,5", rafRumpf.readLine());	
	}

	/**
	 * Testen, ob aus einem File korrekt die letzte Zeile geloescht wird
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testDeleteLastLine1() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		RandomAccessFile raf = new RandomAccessFile(new File(f, "rumpf.csv"), "rw");
		raf.writeBytes("Rumpf,1,2,3,4,5");
		lm.deleteLastLine(raf);
		raf.seek(0);
		assertEquals(null, raf.readLine());
	}

	/**
	 * Testen, ob aus einem File korrekt die letzten Zeilen geloescht werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testDeleteLastLine2() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		RandomAccessFile raf = new RandomAccessFile(new File(f, "rumpf.csv"), "rw");
		raf.writeBytes("Rumpf,1,2,3,4,5\n");
		raf.writeBytes("Rumpf,1,2,3,4,5\n");
		raf.writeBytes("Rumpf,1,2,3,4,5");
		lm.deleteLastLine(raf);
		lm.deleteLastLine(raf);
		lm.deleteLastLine(raf);
		raf.seek(0);
		assertEquals(null, raf.readLine());
	}

	/**
	 * Testen, ob aus einem File korrekt die letzten Zeilen geloescht werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testDeleteLastLine3() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		RandomAccessFile raf = new RandomAccessFile(new File(f, "rumpf.csv"), "rw");
		raf.writeBytes("Rumpf,1,2,3,4,5\n");
		raf.writeBytes("Rumpf,6,7,8,9,10\n");
		raf.writeBytes("Rumpf,11,12,13,14,15");
		lm.deleteLastLine(raf);
		lm.deleteLastLine(raf);
		raf.seek(0);
		assertEquals("Rumpf,1,2,3,4,5", raf.readLine());
	}	

	/**
	 * Testen, ob ein String korrekt zu einem Teil zusammengefuegt wird
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void testStringToTeil() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);
		Teil out = lm.stringToTeil("Kettenantrieb,1,2,3,4,5");
		int[] numbers = {1,2,3,4,5};
		assertEquals(Teiltyp.KETTENANTRIEB, out.getTyp());
		for (int i=0; i<numbers.length; i++)
			assertEquals(numbers[i], out.getNumbers()[i]);
	}

	/**
	 * Testen, ob beim Bereitstellen die korrekten Teile fuer einen Threadee zurueckgegeben werden
	 * 
	 * @throws IOException Falls eine Exception beim Lesen/Schreiben auftritt
	 */
	@Test
	public void bereitstellen() throws IOException {
		lm = new Lagermitarbeiter(mockedLager, f);

		RandomAccessFile raf = new RandomAccessFile(new File(f, "rumpf.csv"), "rw");
		raf.writeBytes("Rumpf,1,2,3,4,5");

		raf = new RandomAccessFile(new File(f, "kettenantrieb.csv"), "rw");
		raf.writeBytes("Kettenantrieb,1,2,3,4,5");

		raf = new RandomAccessFile(new File(f, "arm.csv"), "rw");
		raf.writeBytes("Arm,1,2,3,4,5");
		raf.writeBytes("\nArm,6,7,8,9,10");

		raf = new RandomAccessFile(new File(f, "auge.csv"), "rw");
		raf.writeBytes("Auge,1,2,3,4,5");
		raf.writeBytes("\nAuge,6,7,8,9,10");

		Stack<Teil> out = lm.bereitstellen();

		assertEquals("Kettenantrieb,1,2,3,4,5", out.pop().toString());
		assertEquals("Rumpf,1,2,3,4,5", out.pop().toString());
		assertEquals("Arm,1,2,3,4,5", out.pop().toString());
		assertEquals("Auge,1,2,3,4,5", out.pop().toString());
		assertEquals("Arm,6,7,8,9,10", out.pop().toString());
		assertEquals("Auge,6,7,8,9,10", out.pop().toString());	

	}



}
