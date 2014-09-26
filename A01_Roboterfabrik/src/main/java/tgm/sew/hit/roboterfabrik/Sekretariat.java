package tgm.sew.hit.roboterfabrik;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.mitarbeiter.Lieferant;
import tgm.sew.hit.roboterfabrik.mitarbeiter.Montagemitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;

/**
 * 
 * Sekretariat
 * 
 * Zustaendig fuer das generieren der IDs
 * 
 * @author Paul Kalauner 4AHITT
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 *
 */
public class Sekretariat {
	private static final Logger LOG = LogManager.getLogger(Sekretariat.class);

	private Lagermitarbeiter lagermitarbeiter;

	private int mitarbeiterId;
	private long threadeeId;

	/**
	 * Die Counter werden initialisiert und der {@link Lagermitarbeiter} wird zugewiesen.
	 * 
	 * @param lm
	 *            Zustaendiger {@link Lagermitarbeiter}
	 * @param anzahlLieferanten
	 *            Die Anzahl der {@link Lieferant}en
	 * @param anzahlMonteure
	 *            Die Anzahl der {@link Montagemitarbeiter}
	 */
	public Sekretariat(Lagermitarbeiter lm, int anzahlLieferanten, int anzahlMonteure) {
		this.lagermitarbeiter = lm;
		mitarbeiterId = 0;
		threadeeId = 0;
	}

	/**
	 * Generiert die Id fuer einen {@link Montagemitarbeiter}, indem zuerst der Counter {@code mitarbeiterId} erhoeht
	 * wird.
	 * 
	 * @return Die Id fuer einen {@link Montagemitarbeiter}
	 */
	public int generiereMitarbeiterId() {
		return ++this.mitarbeiterId;
	}

	/**
	 * Generiert die Id fuer einen {@link Threadee}, indem der Counter {@code mitarbeiterId} erhoeht wird.
	 * 
	 * @return Die Id fuer einen {@link Threadee}
	 */
	public long generiereThreadeeId() {
		if (this.threadeeId == Long.MAX_VALUE) {
			LOG.error("Maximale Anzahl an Threadees erreicht (2^63-1)");
			throw new RuntimeException("Maximale Anzahl an Threadees erreicht (2^63-1)");
		}

		return ++this.threadeeId;
	}

	/**
	 * Lieferung entgegennehmen und Monteur beauftragen, einen Threadee zusammenzubauen.
	 * 
	 * @param teile
	 *            Die gelieferten Teile des Lieferanten
	 */
	public synchronized void empfangeLieferung(Stack<Teil> teile) {
		LOG.debug("Nehme Lieferung entgegen");
		lagermitarbeiter.einlagern(teile);
	}

	
	public void setThreadeeId(long id) {
		this.threadeeId = id;
	}

	
	

}
