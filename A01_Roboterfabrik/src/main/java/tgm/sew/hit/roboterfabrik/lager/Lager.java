package tgm.sew.hit.roboterfabrik.lager;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tgm.sew.hit.roboterfabrik.teil.Teil;
import tgm.sew.hit.roboterfabrik.teil.Teiltyp;

/**
 * Lager
 * 
 * repraesentiert den Inhalt der Lagerfiles
 * 
 * @author Paul Kalauner 4AHITT
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 *
 */
public class Lager {
	private static final Logger LOG = LogManager.getLogger(Lager.class);

	private Map<Teiltyp, Integer> anzahlTeile;

	public Lager() {
		initMap();
	}

	/**
	 * Initialisiert die EnumMap
	 */
	private void initMap() {
		LOG.debug("Initialisiere Map");
		this.anzahlTeile = Collections.synchronizedMap(new EnumMap<Teiltyp, Integer>(Teiltyp.class));
		for (Teiltyp cur : Teiltyp.values())
			anzahlTeile.put(cur, 0);
	}

	/**
	 * Fuegt ein Teil hinzu
	 * 
	 * @param teil
	 */
	public void addTeil(Teil teil) {
		int old = anzahlTeile.get(teil.getTyp());
		anzahlTeile.put(teil.getTyp(), ++old);
	}

	/**
	 * Entfernt ein Teil
	 * 
	 * @param teil
	 */
	public void removeTeil(Teiltyp teiltyp) {
		int old = anzahlTeile.get(teiltyp);
		anzahlTeile.put(teiltyp, --old);
	}

	/**
	 * Ueberprueft, ob genuegend Teile eines Teiltyps vorhanden sind
	 * 
	 * @param anzahl
	 *            Die Anzahl der Teile
	 * @param teiltyp
	 *            Angabe des Teiltyps
	 * @return Ob genuegend Teile eines Teiltyps vorhanden sind
	 */
	public boolean teileDa(int anzahl, Teiltyp teiltyp) {
		return (anzahlTeile.get(teiltyp) >= anzahl);
	}

}
