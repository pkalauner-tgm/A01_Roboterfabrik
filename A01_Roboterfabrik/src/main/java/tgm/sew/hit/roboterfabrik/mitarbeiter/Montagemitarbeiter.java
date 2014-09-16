package tgm.sew.hit.roboterfabrik.mitarbeiter;

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

	public Threadee zusammenbauen(Stack<Teil> teile) {
		LOG.debug("Zusammenbauen: " + teile);
		// TODO: zusammenbauen
		return null;
	}

	@Override
	public void run() {
		
		
	}

}
