package tgm.sew.hit.roboterfabrik.mitarbeiter;

import java.util.Arrays;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.WatchableWorker;
import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;

/**
 * Montagemitarbeiter
 * 
 * Ein Montagemitarbeiter ist Zustaendig fuer das Zusammenbauen eines {@link Threadee}s.
 * 
 * @author Mathias Ritter
 * @version 1.0
 */
public class Montagemitarbeiter implements WatchableWorker {
	private static final Logger LOG = LogManager.getLogger(Montagemitarbeiter.class);
	private int id;
	private boolean running;
	private Lagermitarbeiter lagermitarbeiter;
	private Sekretariat sekretariat;

	/**
	 * Die Ids und das Sekretariat werden zugewiesen
	 * 
	 * @param id
	 *            die vom {@link Sekretariat} zugewiesene Id
	 * @param lm
	 *            der zustaendige {@link Lagermitarbeiter}
	 * @param sekretariat
	 *            das {@link Sekretariat}
	 */
	public Montagemitarbeiter(int id, Lagermitarbeiter lm, Sekretariat sekretariat) {
		this.id = id;
		this.lagermitarbeiter = lm;
		this.sekretariat = sekretariat;
		this.running = true;
	}

	/**
	 * Zusammenbauen des Threadees, indem die Arrays (Nummern) der {@link Teil}e sortiert werden
	 * 
	 * @param teile
	 *            die benoetigten {@link Teil}e
	 * @param id
	 *            die vom {@link Sekretariat} zugewiesene Id fuer den Threadee
	 * @return den fertigen {@link Threadee}
	 */
	public Threadee zusammenbauen(Stack<Teil> teile, long id) {
		LOG.debug("Zusammenbauen " + teile);
		Threadee roboter = new Threadee(id, this.id);
		for (Teil t : teile) {
			Arrays.sort(t.getNumbers());
			roboter.addTeil(t);
		}
		return roboter;
	}

	@Override
	public void run() {
		// TODO Paul fragen und JavaDoc
		Thread.currentThread().setName("Monteur ID " + this.id);
		while (this.running) {
			synchronized (lagermitarbeiter) {
				if (this.lagermitarbeiter.genugTeile()) {
					lagermitarbeiter.threadeeEinlagern(zusammenbauen(lagermitarbeiter.bereitstellen(), sekretariat.generiereThreadeeId()));
				}
			}
		}
	}

	/**
	 * 
	 * @return die Id des Threadees
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public void stopThread() {
		this.running = false;
	}
}
