package tgm.sew.hit.roboterfabrik.mitarbeiter;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.Sekretariat;
import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;
import tgm.sew.hit.roboterfabrik.watchdog.WatchableWorker;

/**
 * Lieferant
 * 
 * Liefert Teile
 * 
 * @author Paul Kalauner 4AHITT
 * @version 1.0
 *
 */
public class Lieferant implements WatchableWorker {
	private static final Logger LOG = LogManager.getLogger(Lieferant.class);

	private static final int TEILE_PRO_LIEFERUNG = 10;
	private boolean running;
	private Sekretariat sekretariat;

	public Lieferant(Sekretariat sekretariat) {
		this.sekretariat = sekretariat;
		this.running = true;
	}

	/**
	 * Generiert Teile fuer den angegebenen Teile
	 * 
	 * @param teiltyp Teile dieses Typs werden generiert
	 * @return Stack der die Teile enthaelt
	 */
	public Stack<Teil> generiereTeile(Teiltyp teiltyp) {
		Stack<Teil> stack = new Stack<Teil>();

		for (int i = 0; i < TEILE_PRO_LIEFERUNG; i++) {
			int[] numbers = new int[20];
			for (int j = 0; j < numbers.length; j++)
				numbers[j] = (int) (Math.random() * 1000);
			stack.add(new Teil(teiltyp, numbers));
		}
		return stack;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("Lieferant");
		while (this.running) {
			for (Teiltyp cur : Teiltyp.values()) {
				LOG.debug("Liefere " + cur);
				sekretariat.empfangeLieferung(generiereTeile(cur));
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					LOG.error(e);
				}
			}
		}
	}

	@Override
	public void stopWorker() {
		this.running = false;
	}
}
