package tgm.sew.hit.roboterfabrik.lager;

import java.util.EnumMap;
import java.util.Stack;

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
 *
 */
public class Lager {
	private static final Logger LOG = LogManager.getLogger(Lager.class);
	
	private EnumMap<Teiltyp,Stack<Teil>> teile;

	public Lager() {
		initStacks();
	}
	
	/**
	 * Initialisiert die Stacks in der EnumMap
	 */
	private void initStacks() {
		LOG.debug("Initialisiere Stacks");
		this.teile = new EnumMap<Teiltyp,Stack<Teil>>(Teiltyp.class);
		for (Teiltyp cur : Teiltyp.values())
			teile.put(cur, new Stack<Teil>());
	}
	
	/**
	 * Fuegt ein Teil hinzu
	 * @param teil
	 */
	public void addTeil(Teil teil) {
		teile.get(teil.getTyp()).add(teil);
	}

	/**
	 * Entfernt ein Teil
	 * @param teil
	 */
	public Teil removeTeil(Teiltyp teiltyp) {
		return teile.get(teiltyp).pop();
	}
	
	/**
	 * Ueberprueft, ob genuegend Teile eines Teiltyps vorhanden sind
	 * 
	 * @param anzahl Die Anzahl der Teile
	 * @param teiltyp Angabe des Teiltyps
	 * @return Ob genuegend Teile eines Teiltyps vorhanden sind
	 */
	public boolean teileDa(int anzahl, Teiltyp teiltyp) {
		return (teile.get(teiltyp).size() >= anzahl);
	}

}
