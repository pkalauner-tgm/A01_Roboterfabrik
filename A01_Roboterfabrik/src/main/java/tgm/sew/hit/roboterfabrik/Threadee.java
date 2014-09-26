package tgm.sew.hit.roboterfabrik;

import tgm.sew.hit.roboterfabrik.teil.Teil;

/**
 * Threadee
 * 
 * Stellt einen Threadee (Roboter) dar.
 * 
 * @author Mathias Ritter 4AHITT
 * @version 1.0
 */
public class Threadee {

	private long id;

	private int mitarbeiterId;

	private Teil augeLinks;

	private Teil augeRechts;

	private Teil rumpf;

	private Teil armLinks;

	private Teil armRechts;

	private Teil kettenantrieb;

	/**
	 * Die {@code id} und die {@code mitarbeiterId} wird einem neuen Threadee zugewiesen
	 * 
	 * @param id
	 *            {@code id} eines Threadees
	 * @param mitarbeiterId
	 *            {@code mitarbeiterId} des Mitarbeiters, der den Threadee zusammenbaut
	 */
	public Threadee(long id, int mitarbeiterId) {
		this.id = id;
		this.mitarbeiterId = mitarbeiterId;
	}

	/**
	 * Mit dieser Methode werden die Teile im Roboter verbaut. Da sich das linke und rechte Auge bzw. der linke und
	 * rechte Arm nicht unterscheiden, wird zuerst jeweils das linke Teil und dann erst das rechte Teil verbaut.
	 * 
	 * @param teil
	 *            Das Teil, das verbaut werden soll
	 */
	public void addTeil(Teil teil) {
		switch (teil.getTyp()) {
		case AUGE:
			if (this.augeLinks == null)
				this.augeLinks = teil;
			else
				this.augeRechts = teil;
			break;
		case RUMPF:
			this.rumpf = teil;
			break;
		case ARM:
			if (this.armLinks == null)
				this.armLinks = teil;
			else
				this.armRechts = teil;
			break;
		case KETTENANTRIEB:
			this.kettenantrieb = teil;
		}
	}

	/**
	 * Setzt alle Infos ueber einen Threadee kommagetrennt zu einem String zusammen: die {@code id} des Threadees, die
	 * {@code Mitarbeiterid} des Mitarbeiters, der den Threadee zusammengebaut hat, die {@link Teil}e des Threadees
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Alle Teile zum StringBuilder hinzufuegen
		sb.append("Threadee-ID" + this.id);
		sb.append("," + "Mitarbeiter-ID" + this.mitarbeiterId);
		sb.append("," + this.augeLinks);
		sb.append("," + this.augeRechts);
		sb.append("," + this.rumpf);
		sb.append("," + this.armLinks);
		sb.append("," + this.armRechts);
		sb.append("," + this.kettenantrieb);

		return sb.toString();
	}

	/**
	 * 
	 * @return Die {@code id} des Threadees
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * @return Die {@code mitarbeiterId} des Mitarbeiters, der den Threadee zusammengebaut hat
	 */
	public int getMitarbeiterId() {
		return this.mitarbeiterId;
	}

	public Teil getAugeLinks() {
		return augeLinks;
	}

	public Teil getAugeRechts() {
		return augeRechts;
	}

	public Teil getRumpf() {
		return rumpf;
	}

	public Teil getArmLinks() {
		return armLinks;
	}

	public Teil getArmRechts() {
		return armRechts;
	}

	public Teil getKettenantrieb() {
		return kettenantrieb;
	}
	
	

}
