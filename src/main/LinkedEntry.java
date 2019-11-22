package my.util;
import java.util.*;

public class LinkedEntry<K,V> implements Map.Entry<K,V> {
	private K key;
	private V value;
	private LinkedEntry<K,V> next;
	private LinkedEntry<K,V> prev;
	public LinkedEntry (K key, V value,
			LinkedEntry<K,V> next,LinkedEntry<K,V> previous) {
		this.key = key;
		this.value = value;
		this.next=next;
		this.prev=previous;
	}
	public K getKey() { return key; }
	
	public V getValue() { return value; }
	
	public V setValue(V v) {
		V result = value;
		value = v;
		return result;
	}
	public LinkedEntry<K,V> getNext(){
		return next;
	}
	public LinkedEntry<K,V> getPrevious(){
		return prev;
	}
	public void setNext(LinkedEntry<K,V> next) {
		this.next=next;
	}
	public void setPrevious(LinkedEntry<K,V> previous) {
		this.prev=previous;
	}
	public boolean hasNext() { return !(next==null);}
	public boolean hasPrevious() { return !(prev==null);}
	public int hashCode() {
		return (key==null ? 0 : key.hashCode()) ^
				(value==null ? 0 : value.hashCode());
	}
	public boolean equals(LinkedEntry<K,V> o) {
		if(!(o instanceof LinkedEntry)) return false;
		LinkedEntry<K,V> me = o;
		return
				(key == null ?
						me.getKey() == null : key.equals(me.getKey())) &&
				(value == null ?
						me.getValue()== null : value.equals(me.getValue()));
	}
	public String toString() { return key + "=" + value; }
} 


