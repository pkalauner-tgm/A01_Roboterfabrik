package tgm.sew.hit.roboterfabrik;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.mitarbeiter.Montagemitarbeiter;
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
	private List<Montagemitarbeiter> montagemitarbeiter;

	private Lagermitarbeiter lagermitarbeiter;

	public Sekretariat(Lagermitarbeiter lm, int anzahlMonteure) {
		this.lagermitarbeiter = lm;
		this.montagemitarbeiter = new ArrayList<Montagemitarbeiter>();
		this.monteureEinstellen(anzahlMonteure);
	}

	private void monteureEinstellen(int anzahlMonteure) {
		ExecutorService esMonteure = Executors.newFixedThreadPool(anzahlMonteure);
		for (int i = 0; i < anzahlMonteure; i++) {
			Montagemitarbeiter mm = new Montagemitarbeiter(generiereMitarbeiterId());
			this.montagemitarbeiter.add(mm);
			esMonteure.execute(mm);
		}
	}

	private int generiereMitarbeiterId() {
		// TODO: no duplicates
		return (int) (Math.random() * 1000) + 1;
	}

	private int generiereThreadeeId() {
		// TODO: no duplicates
		return (int) (Math.random() * 1000) + 1;
	}

	public void befehleMonteur() {
		this.montagemitarbeiter.get((int) (Math.random() * this.montagemitarbeiter.size()));
	}

	public void threadeeHinzufuegen() {

	}

	/**
	 * Lieferung entgegennehmen und Monteur beauftragen, einen Threadee zusammenzubauen
	 * @param teile Die gelieferten Teile des Lieferanten
	 */
	public synchronized void empfangeLieferung(Stack<Teil> teile) {
		lagermitarbeiter.einlagern(teile);
		if (lagermitarbeiter.genugTeile()) befehleMonteur();
	}

}
