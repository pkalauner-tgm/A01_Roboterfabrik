package tgm.sew.hit.roboterfabrik;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;

/**
 * 
 * Sekretariat
 * 
 * Zustaendig fuer das generieren der IDs
 * 
 * @author Paul Kalauner 4AHITT
 *
 */
public class Sekretariat {
	private static final Logger LOG = LogManager.getLogger(Sekretariat.class);
	

	private Lagermitarbeiter lagermitarbeiter;

	private int mitarbeiterId;
	private long threadeeId;
	
	public Sekretariat(Lagermitarbeiter lm, int anzahlLieferanten, int anzahlMonteure) {
		this.lagermitarbeiter = lm;
		mitarbeiterId = 0;
		threadeeId = 0;
	}

	
	public int generiereMitarbeiterId() {
		return ++this.mitarbeiterId;
	}

	public long generiereThreadeeId() {
		if (this.threadeeId == Long.MAX_VALUE) {
			LOG.error("Maximale Anzahl an Threadees erreicht (2^63)");
			System.exit(1);
		}
			
		return ++this.threadeeId;
	}

	/**
	 * Lieferung entgegennehmen und Monteur beauftragen, einen Threadee zusammenzubauen
	 * @param teile Die gelieferten Teile des Lieferanten
	 */
	public synchronized void empfangeLieferung(Stack<Teil> teile) {
		LOG.debug("Nehme Lieferung entgegen");
		lagermitarbeiter.einlagern(teile);
	}

}
