package tgm.sew.hit.roboterfabrik.mitarbeiter;

import tgm.sew.hit.roboterfabrik.Threadee;
import tgm.sew.hit.roboterfabrik.teil.Teil;

public class Montagemitarbeiter extends Mitarbeiter implements Runnable {

	public Montagemitarbeiter(int id) {
		super(id);
		
	}

	public Threadee zusammenbauen(Teil[] teile) {
		return null;
	}

	@Override
	public void run() {
		
		
	}

}
