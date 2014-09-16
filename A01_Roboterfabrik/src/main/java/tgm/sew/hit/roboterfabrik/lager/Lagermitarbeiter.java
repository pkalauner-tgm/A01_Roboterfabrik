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
public class Lagermitarbeiter {
	private static final Logger LOG = LogManager.getLogger(Lagermitarbeiter.class);
	private EnumMap<Teiltyp, RandomAccessFile> rafs;
	private Lager lager;
	private RandomAccessFile rafThreadee;

	public Lagermitarbeiter(Lager lager, File lagerVerzeichnis) {
		this.lager = lager;
		try {
			initRafs(lagerVerzeichnis);
		} catch (FileNotFoundException e) {
			LOG.error("Fehler beim Initialisieren der RandomAccessFiles.");
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
		File f = new File(lagerVerzeichnisFile, "auslieferung.csv");
		if (f.exists())
			f.delete();
		this.rafThreadee = new RandomAccessFile(f.getAbsolutePath(), "rw");
		for (Teiltyp cur : Teiltyp.values()) {
			f = new File(lagerVerzeichnisFile, cur.toString().toLowerCase() + ".csv");
			LOG.debug(f.getAbsolutePath());
			// alte Files loeschen
			if (f.exists())
				f.delete();
			rafs.put(cur, new RandomAccessFile(f.getAbsolutePath(), "rw"));
		}
	}

	public void threadeeEinlagern(Threadee threadee) {
		// TODO ins Raf schreiben
	}

	public synchronized Stack<Teil> bereitstellen() {
		Stack<Teil> out = new Stack<Teil>();

		for (int i = 0; i < 2; i++) {
			lager.removeTeil(Teiltyp.AUGE);
			out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.AUGE))));
			lager.removeTeil(Teiltyp.ARM);
			out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.ARM))));
		}
		lager.removeTeil(Teiltyp.RUMPF);
		out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.RUMPF))));
		lager.removeTeil(Teiltyp.KETTENANTRIEB);
		out.add(stringToTeil(deleteLastLine(rafs.get(Teiltyp.KETTENANTRIEB))));
		return out;
	}

	/**
	 * Schreibt ins Lagerfile
	 * 
	 * @param teile
	 *            die einzulagernden Teile
	 */
	public synchronized void einlagern(Stack<Teil> teile) {
		for (Teil cur : teile) {
			try {
				RandomAccessFile raf = rafs.get(cur.getTyp());
				if (raf.length() == 0)
					raf.writeBytes(cur.toString());
				else
					raf.writeBytes("\n" + cur.toString());
				lager.addTeil(cur);
			} catch (IOException e) {
				LOG.error("Fehler waehrend dem Schreibvorgang", e);
			}
		}
	}

	/**
	 * Loescht aus einem Teile-File das letzte Teil heraus
	 * 
	 * @param f
	 *            RandomAccessFile des jeweiligen Teils
	 * @return die geloeschte Zeile
	 */
	private synchronized String deleteLastLine(RandomAccessFile f) {
		byte b = 0;
		long length;
		String lastLine = null;
		try {
			length = f.length();
			StringBuffer sb = new StringBuffer();

			do {
				length -= 1;
				f.seek(length);
				b = f.readByte();
				if (b != 10)
					sb.append((char) b);
			} while (b != 10 && length > 0);
			lastLine = sb.reverse().toString();
			f.setLength(length);
		} catch (IOException e) {
			LOG.error("Fehler beim Loeschen der letzten Zeile", e);
			System.exit(1);
		}
		return lastLine;
	}

	private static Teil stringToTeil(String s) {
		Teiltyp typ = null;

		String[] arr = s.split(",");
		for (Teiltyp cur : Teiltyp.values()) {
			if (cur.getName().equalsIgnoreCase(arr[0])) {
				typ = cur;
				break;
			}
		}

		int[] numbers = new int[arr.length - 1];
		for (int i = 0; i < numbers.length; i++) {
			try {
				numbers[i] = Integer.parseInt(arr[i + 1]);
			} catch (NumberFormatException e) {
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
		return lager.teileDa(2, Teiltyp.AUGE) && lager.teileDa(2, Teiltyp.ARM) && lager.teileDa(1, Teiltyp.RUMPF) && lager.teileDa(1, Teiltyp.KETTENANTRIEB);
	}
}