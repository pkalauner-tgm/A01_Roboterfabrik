package tgm.sew.hit.roboterfabrik;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Diese Klasse testet die Funktionalitaet der Simulations-Klasse
 * 
 * @author Paul Kalauner 4AHITT
 * @version 1.0
 *
 */
public class TestSimulation {
	private File resFolder;

	/**
	 * Erstellt die Testordner
	 */
	@Before
	public void init() {
		resFolder = new File("src/test/resources/SimulationTest");
		if (!resFolder.exists())
			resFolder.mkdirs();
	}

	/**
	 * Testet die {@code checkArgs()} Methode mit gueltigen Argumenten
	 */
	@Test
	public void testCheckArgsValid() {
		String[] args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "10", "--monteure", "25" };
		assertEquals(true, Simulation.checkArgs(args));
	}

	/**
	 * Testet die {@code checkArgs()} Methode mit gueltigen Argumenten, die an den Grenzen liegen
	 */
	@Test
	public void testCheckArgsValidGrenzen() {
		String[] args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "1000", "--monteure", "1000" };
		assertEquals(true, Simulation.checkArgs(args));
	}

	/**
	 * Testet die {@code checkArgs()} Methode mit ungueltigen Argumenten
	 */
	@Test
	public void testCheckArgsNotValid() {
		String[] args = new String[] { "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "10", "--monteure", "25" };
		assertEquals(false, Simulation.checkArgs(args));
	}

	/**
	 * Testet die {@code checkArgs()} Methode mit ungueltigen Argumenten, die an den Grenzen liegen
	 */
	@Test
	public void testCheckArgsNotValidGrenzen() {
		String[] args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "3600001", "--lieferanten", "1000", "--monteure", "1000" };
		assertEquals(false, Simulation.checkArgs(args));
		args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "1001", "--monteure", "1000" };
		assertEquals(false, Simulation.checkArgs(args));
		args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "1000", "--monteure", "1001" };
		assertEquals(false, Simulation.checkArgs(args));
	}

	/**
	 * Testet die {@code checkArgs()} Methode mit negativen Argumenten
	 */
	@Test
	public void testCheckArgsNotValidGrenzen2() {
		String[] args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "-1", "--lieferanten", "1000", "--monteure", "1000" };
		assertEquals(false, Simulation.checkArgs(args));
		args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "-1", "--monteure", "1000" };
		assertEquals(false, Simulation.checkArgs(args));
		args = new String[] { "--lager", resFolder.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "1000", "--monteure", "-1" };
		assertEquals(false, Simulation.checkArgs(args));
	}

	/**
	 * Testet, ob ein nicht vorhandenes Log Verzeichnis richtig erstellt wird
	 */
	@Test
	public void testNichtExistierendesLogVerzeichnis() {
		File logV = new File(resFolder, "simulation");
		if (logV.exists())
			deleteDir(logV);
		String[] args = new String[] { "--lager", resFolder.getPath(), "--logs", logV.getPath(), "--laufzeit", "1", "--lieferanten", "10", "--monteure", "25" };
		Simulation.checkArgs(args);
		assertEquals(true, logV.exists());
	}

	/**
	 * Testet, ob ein nicht vorhandenes Lager Verzeichnis richtig erstellt wird
	 */
	@Test
	public void testNichtExistierendesLagerVerzeichnis() {
		File lagerV = new File(resFolder, "lager");
		if (lagerV.exists())
			deleteDir(lagerV);
		String[] args = new String[] { "--lager", lagerV.getPath(), "--logs", resFolder.getPath(), "--laufzeit", "1", "--lieferanten", "10", "--monteure", "25" };
		Simulation.checkArgs(args);
		assertEquals(true, lagerV.exists());
	}

	/**
	 * Loescht ein Verzeichnis
	 * 
	 * @param dir
	 *            das zu loeschende Verzeichnis
	 */
	private static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			for (File c : dir.listFiles())
				deleteDir(c);
		}
		dir.delete();
	}
}