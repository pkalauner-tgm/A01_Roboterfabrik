package tgm.sew.hit.roboterfabrik.mitarbeiter;

import java.util.Arrays;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.Simulation;
import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.lager.Lagermitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;

public class Montagemitarbeiter implements Runnable {
	private static final Logger LOG = LogManager.getLogger(Montagemitarbeiter.class);
	private int id;
	private Lagermitarbeiter lagermitarbeiter;
	private Sekretariat sekretariat;

	public Montagemitarbeiter(int id, Lagermitarbeiter lm, Sekretariat sekretariat) {
		this.id = id;
		this.lagermitarbeiter = lm;
		this.sekretariat = sekretariat;
	}

	public Threadee zusammenbauen(Stack<Teil> teile, long id) {
		LOG.debug("Zusammenbauen " + teile);
		Threadee roboter = new Threadee(id, this.id);
		for (Teil t : teile) {
			Arrays.sort(t.getNumbers());
			roboter.addTeil(t);
		}
		return roboter;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("Monteur ID " + this.id);
		while (Simulation.running) {
			synchronized (lagermitarbeiter) {
				if (this.lagermitarbeiter.genugTeile()) {
					lagermitarbeiter.threadeeEinlagern(zusammenbauen(lagermitarbeiter.bereitstellen(), sekretariat.generiereThreadeeId()));
				}
			}
		}
	}

}
