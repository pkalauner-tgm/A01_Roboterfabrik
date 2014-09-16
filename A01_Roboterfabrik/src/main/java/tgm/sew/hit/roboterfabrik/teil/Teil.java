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

	public Teiltyp getTyp() {
		return typ;
	}

	public void setTyp(Teiltyp typ) {
		this.typ = typ;
	}

	public int[] getNumbers() {
		return numbers;
	}
	
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.typ.getName());
		for (int i: numbers)
			sb.append("," + i);
		return sb.toString();
	}
}
