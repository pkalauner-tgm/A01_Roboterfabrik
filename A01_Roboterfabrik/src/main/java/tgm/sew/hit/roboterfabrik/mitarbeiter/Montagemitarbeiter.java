package tgm.sew.hit.roboterfabrik.mitarbeiter;

import java.util.Arrays;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.teil.Teil;

public class Montagemitarbeiter extends Mitarbeiter implements Runnable {
	private static final Logger LOG = LogManager.getLogger(Montagemitarbeiter.class);
	public Montagemitarbeiter(int id) {
		super(id);
		
	}

	public Threadee zusammenbauen(Stack<Teil> teile, int id) {
		LOG.debug("Zusammenbauen: " + teile);
		Threadee roboter = new Threadee(id, this.getId());
		for (Teil t: teile) {
			Arrays.sort(t.getNumbers());
			roboter.addTeil(t);
		}
		return roboter;
	}

	@Override
	public void run() {
		
		
	}

}
