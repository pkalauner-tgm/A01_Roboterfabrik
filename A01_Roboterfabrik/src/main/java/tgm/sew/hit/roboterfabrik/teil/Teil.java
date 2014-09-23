package tgm.sew.hit.roboterfabrik.teil;

/**
 * Stellt ein Teil dar
 * 
 * @author Paul Kalauner 4AHITT
 * @author Mathias Ritter 4AHITT
 *
 */
public class Teil {

	private int[] numbers;

	private Teiltyp typ;

	public Teil(Teiltyp typ, int[] numbers) {
		this.typ = typ;
		this.numbers = numbers;
	}

	/**
	 * 
	 * @return den {@link Teiltyp}
	 */
	public Teiltyp getTyp() {
		return typ;
	}

	/**
	 * 
	 * @param typ
	 *            der {@link Teiltyp}
	 */
	public void setTyp(Teiltyp typ) {
		this.typ = typ;
	}

	/**
	 * 
	 * @return die Nummer eines Teils
	 */
	public int[] getNumbers() {
		return numbers;
	}

	/**
	 * 
	 * @param numbers
	 *            die Nummer eines Teils
	 */
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	/**
	 * Liefert ein Teil als String zurueck: Den {@link Teiltyp} und die Nummern eines Teils kommagetrennt
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.typ.getName());
		for (int i : numbers)
			sb.append("," + i);
		return sb.toString();
	}
}
