package tgm.sew.hit.roboterfabrik;

import java.util.Stack;

import tgm.sew.hit.roboterfabrik.teil.Teil;

public class Threadee {

	private int id;
	
	private int mitarbeiterId;

	private Teil augeLinks;

	private Teil augeRechts;

	private Teil rumpf;

	private Teil armLinks;

	private Teil armRechts;

	private Teil kettenantrieb;
	
	public Threadee(int id, int mitarbeiterId) {
		this.id = id;
		this.mitarbeiterId = mitarbeiterId;
	}
	
	/**
	 * Mit dieser Methode werden die Teile im Roboter verbaut.
	 * Da sich das linke und rechte Auge bzw. der linke und rechte Arm nicht unterscheiden,
	 * wird zuerst jeweils das linke Teil und dann erst das rechte Teil verbaut.
	 * 
	 * @param teil Das Teil, das verbaut werden soll
	 */
	public void addTeil(Teil teil) {
		switch(teil.getTyp()) {
		case AUGE:
			if (this.augeLinks == null)
				this.augeLinks = teil;
			else
				this.augeRechts = teil;
		case RUMPF:
			this.rumpf = teil;
		case ARM:
			if (this.armLinks == null)
				this.armLinks = teil;
			else
				this.armRechts = teil;
		case KETTENANTRIEB:
			this.kettenantrieb = teil;
		}	
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getMitarbeiterId() {
		return this.mitarbeiterId;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
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

}
