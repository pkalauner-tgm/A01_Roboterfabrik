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

	/**
	 * Ein Augen Teil
	 */
	AUGE("Auge"),

	/**
	 * Ein Rumpf Teil
	 */
	RUMPF("Rumpf"),

	/**
	 * Ein Arm Teil
	 */
	ARM("Arm"),

	/**
	 * Ein Kettenantrieb Teil
	 */
	KETTENANTRIEB("Kettenantrieb");

	private String name;

	/**
	 * Initialisiert das Enum mit Angabe des Namens, der im Lagerfile verwendet werden soll
	 * 
	 * @param name
	 *            der Name des Teils
	 */
	Teiltyp(String name) {
		this.name = name;
	}

	/**
	 * Liefert den Namen des Enums
	 * 
	 * @return Name des Enums
	 */
	public String getName() {
		return this.name;
	}
}
