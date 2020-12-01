// --== CS400 File Header Information ==--
// Name: Mack Hooyman
// Email: mhooyman@wisc.edu
// Team: EG
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{
	private LinkedList<KeyValPair> map[];
	private int size;
	
	//Constructor with specified capacity
	public HashTableMap(int capacity) {
		map = new LinkedList[capacity];
	}
	//Default constructor
	public HashTableMap() {
		map = new LinkedList[10];
	}

	//Clear method clears all keys and values from map
	@Override
	public void clear() {
		for(int i=0; i<this.map.length; i++) {
			if(this.map[i] != null)this.map[i].clear();
		}
		this.size = 0;
	}

	//Put method adds a key/value pair to the map. If a collision occurs the new value will be added to a linked list at the key's index
	@Override
	public boolean put(KeyType key, ValueType value) {
		
		KeyValPair data = new KeyValPair(key, value);
		
		if(this.containsKey(key))return false;
		
		if(!this.checkSize()) {
			this.expand();
		}
		
		if(this.map[(Math.abs(key.hashCode())) % map.length] == null) {
			map[(Math.abs(key.hashCode())) % map.length] = new LinkedList<KeyValPair>();
		}
		map[(Math.abs(key.hashCode())) % map.length].add(data);
		this.size++;
		
		return true;
	}

	//containsKey returns whether or not a key is stored in the map
	@Override
	public boolean containsKey(KeyType key) {
		if(this.map[(Math.abs(key.hashCode())) % map.length] == null)return false;
		
		for(int i=0; i<this.map[(Math.abs(key.hashCode())) % map.length].size(); i++) {
			if(this.map[(Math.abs(key.hashCode())) % map.length].get(i).getKey().equals(key))return true;
		}
		return false;
	}

	//remove removes a specified key/value pair from the map and returns the value
	@Override
	public ValueType remove(KeyType key) {
		if(this.map[(Math.abs(key.hashCode())) % map.length] != null) {
			for(int i=0; i<this.map[(Math.abs(key.hashCode())) % map.length].size(); i++) {
				if(this.map[(Math.abs(key.hashCode())) % map.length].get(i).getKey().equals(key)) {
					ValueType val = (ValueType) this.map[(Math.abs(key.hashCode())) % map.length].get(i).getValue();
					this.map[(Math.abs(key.hashCode())) % map.length].remove(i);
					return val;
				}
			}
		}
		return null;
	}

	//get returns a value based off of a given key
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		if(!containsKey(key))throw new NoSuchElementException();
		
		for(int i=0; i<this.map[(Math.abs(key.hashCode())) % map.length].size(); i++) {
			if(this.map[(Math.abs(key.hashCode())) % map.length].get(i).getKey().equals(key)) {
				return (ValueType) this.map[(Math.abs(key.hashCode())) % map.length].get(i).getValue();
			}
		}
		
		return null;
	}

	//size returns the size variable that is incremented every time a new element is added
	@Override
	public int size() {
		return this.size;
	}
	
	//checkSize returns whether or not the map is at 80% capacity
	public boolean checkSize() {
		double dSize = size;
		double dLength = map.length;
		
		return dSize/dLength < .8;
	}
	
	//expand is called when the capacity is at 80%. This method doubles the capacity of the map
	public void expand() {
		LinkedList<KeyValPair>[] temp = new LinkedList[map.length];
		for(int i=0; i<temp.length; i++) {
			temp[i] = map[i];
		}
		
		map = new LinkedList[temp.length * 2];
		for(int i=0; i<temp.length; i++) {
			map[i] = temp[i];
		}
	}

}
