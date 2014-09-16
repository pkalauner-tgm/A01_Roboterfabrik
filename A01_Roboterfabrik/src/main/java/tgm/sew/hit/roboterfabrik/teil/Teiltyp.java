package tgm.sew.hit.roboterfabrik.teil;

public enum Teiltyp {
	
	AUGE("Auge"),

	RUMPF("Rumpf"),

	ARM("Arm"),

	KETTENANTRIEB("Kettenatrieb");
	
	private String name;
	
	Teiltyp(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
