package tgm.sew.hit.roboterfabrik.mitarbeiter;

import java.util.Stack;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

public class Lieferant implements Runnable {

	private Sekretariat sekretariat;

	private Stack<Teil> teile;

	public Lieferant(Sekretariat sekretariat) {

	}

	public Stack<Teil> generiereTeile(Teiltyp teiltyp) {
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
