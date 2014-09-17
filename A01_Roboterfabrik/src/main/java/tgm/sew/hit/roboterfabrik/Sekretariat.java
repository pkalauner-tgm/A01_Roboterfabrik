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

	public Sekretariat(Lagermitarbeiter lm, int anzahlLieferanten, int anzahlMonteure) {
		this.lagermitarbeiter = lm;
	}

	
	public int generiereMitarbeiterId() {
		// TODO: no duplicates
		return (int) (Math.random() * 1000) + 1;
	}

	public int generiereThreadeeId() {
		// TODO: no duplicates
		return (int) (Math.random() * 1000) + 1;
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
