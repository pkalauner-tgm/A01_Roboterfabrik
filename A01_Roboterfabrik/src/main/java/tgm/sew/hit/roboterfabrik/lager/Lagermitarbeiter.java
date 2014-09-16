package tgm.sew.hit.roboterfabrik.lager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.EnumMap;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.mitarbeiter.Mitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Lagermitarbeiter
 * 
 * Zustaendig fuer das Schreiben und Lesen der Files
 *
 * @author Paul Kalauner 4AHITT
 *
 */
public class Lagermitarbeiter extends Mitarbeiter {
	private static final Logger LOG = LogManager.getLogger(Lagermitarbeiter.class);
	private EnumMap<Teiltyp, RandomAccessFile> rafs;
	private Lager lager;

	public Lagermitarbeiter(int id, Lager lager, File lagerVerzeichnis) {
		super(id);
		this.lager = lager;
		try {
			initRafs(lagerVerzeichnis);
		} catch (FileNotFoundException e) {
			LOG.error("Fehler beim initialisieren der RandomAccessFiles.");
		}
	}

	/**
	 * Initialisiert die RandomAccessFiles
	 * 
	 * @param lagerVerzeichnisFile
	 * @throws FileNotFoundException
	 */
	private void initRafs(File lagerVerzeichnisFile) throws FileNotFoundException {
		LOG.debug("Initialisiere RandomAccessFiles");
		this.rafs = new EnumMap<Teiltyp, RandomAccessFile>(Teiltyp.class);
		for (Teiltyp cur : Teiltyp.values())
			rafs.put(cur, new RandomAccessFile(lagerVerzeichnisFile.getAbsolutePath() + File.separator + cur.toString().toLowerCase() + ".csv", "rw"));
	}

	public void threadeeEinlagern(Threadee threadee) {
		// TODO: schreibe in RAF
	}
	
	public Stack<Teil> bereitstellen() {

		Stack<Teil> out = new Stack<Teil>();

		for (int i = 0; i < 2; i++) {
			out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.AUGE))));
			lager.removeTeil(Teiltyp.AUGE);
			out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.ARM))));
			lager.removeTeil(Teiltyp.ARM);
		}
		out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.RUMPF))));
		lager.removeTeil(Teiltyp.RUMPF);
		out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.KETTENANTRIEB))));
		lager.removeTeil(Teiltyp.KETTENANTRIEB);
		return out;
	}

	/**
	 * Schreibt ins Lagerfile
	 * 
	 * @param teile
	 *            die einzulagernden Teile
	 */
	public void einlagern(Stack<Teil> teile) {
		for (Teil cur : teile) {
			try {
				rafs.get(cur.getTyp()).writeBytes(cur.getTyp().toString().toLowerCase() + arrayToText(cur.getNumbers()) + "\n");
				lager.addTeil(cur);
			} catch (IOException e) {
				LOG.error("Fehler waehrend dem Schreibvorgang", e);
			}
		}
	}

	/**
	 * Wandelt das Array aus Ganzzahlen in einen String um
	 * 
	 * @param numbers
	 * @return String der folgendem Format entspricht: 1,2,3,4
	 */
	private static String arrayToText(int[] numbers) {
		StringBuilder sb = new StringBuilder();
		for (int i : numbers)
			sb.append("," + i);
		return sb.toString();
	}

	/**
	 * Loescht aus einem Teile-File das letzte Teil heraus
	 * 
	 * @param f
	 *            RandomAccessFile des jeweiligen Teils
	 * @return die geloeschte Zeile
	 */
	private static String deleteLastLine(RandomAccessFile f) {

		byte b = 0;
		long length;
		String lastLine = null;
		try {
			length = f.length() - 1;
			StringBuffer sb = new StringBuffer();

			do {
				length -= 1;
				f.seek(length);
				b = f.readByte();
				sb.append((char) b);

			} while (b != 10 && length > 0);
			lastLine = sb.reverse().toString();
			f.setLength(length + 1);
		} catch (IOException e) {
			LOG.error("Fehler beim loeschen der letzten Zeile", e);
		}
		return lastLine;
	}

	private static Teil stringToTeil(String s) {
		Teiltyp typ = null;
		String[] arr = s.split(",");
		for (Teiltyp cur : Teiltyp.values()) {
			if (cur.toString().equalsIgnoreCase(arr[0]))
				typ = cur;
		}

		int[] numbers = new int[arr.length-1];
		for (int i = 0; i< numbers.length;i++) {
			try {
			numbers[i] = Integer.parseInt(arr[i+1]);
			} catch(NumberFormatException e) {
				LOG.error("Fehler beim Konvertieren von String zu Teil", e);
			}
		}
		return new Teil(typ, numbers);
	}

	/**
	 * Ueberprueft, ob genug Teile fuer einen Threadee da sind. Ein Threadee besteht aus 2 Augen, 2 Armen, 1 Rumpf und 1
	 * Kettenantrieb.
	 * 
	 * @return Ob genug Teile fuer einen Threadee da sind
	 */
	public boolean genugTeile() {
		//TODO: wie viele roboter koennen gebaut werden
		return lager.teileDa(2, Teiltyp.AUGE) && lager.teileDa(2, Teiltyp.ARM) && lager.teileDa(1, Teiltyp.RUMPF) && lager.teileDa(1, Teiltyp.KETTENANTRIEB);
	}
}