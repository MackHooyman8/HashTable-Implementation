// --== CS400 File Header Information ==--
// Name: Mack Hooyman
// Email: mhooyman@wisc.edu
// Team: EG
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
public class KeyValPair<ValueType, KeyType> {
	private KeyType key;
	private ValueType value;
	
	public KeyValPair(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyType getKey() {
		return this.key;
	}
	
	public ValueType getValue() {
		return this.value;
	}
}
