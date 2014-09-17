package tgm.sew.hit.roboterfabrik.teil;

/**
 * Teiltyp
 * 
 * Stellt die verschiedenen Arten eines Teils dar.
 * 
 * @author Mathias Ritter
 * @version 1.0
 */
public enum Teiltyp {
	
	AUGE("Auge"),

	RUMPF("Rumpf"),

	ARM("Arm"),

	KETTENANTRIEB("Kettenantrieb");
	
	private String name;
	
	Teiltyp(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
