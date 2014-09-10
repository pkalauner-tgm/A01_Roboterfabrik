package tgm.sew.hit.roboterfabrik.lager;

import tgm.sew.hit.roboterfabrik.mitarbeiter.Mitarbeiter;
import tgm.sew.hit.roboterfabrik.teil.Teil;

public class Lagermitarbeiter extends Mitarbeiter {

	public Lagermitarbeiter(int id) {
		super(id);
		
	}

	private Lager lager;

	private Teil[] bereitstellen() {
		return null;
	}

	private void einlagern(Teil[] teile) {

	}

}
