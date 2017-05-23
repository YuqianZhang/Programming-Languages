
// This is a simple example of a generic class, parameterized by 
// to type parameters, K and V.

class Entry<K, V> {
  private K key;
  private V value;
 
  public Entry(K k,V v) {  
    key = k;
    value = v;   
  }
 
  public K getKey() {
    return key;
  }
 
  public V getValue() {
    return value;
  }
 
  public String toString() { 
    return "(" + key + ", " + value + ")";  
  }
}



public class testEntry {

    // Here's a simple example of a generic method, parameterized
 

    public static <T> Entry<T,T> twice(T value) {
	return new Entry<T,T>(value, value);
    }

    
    public static void main(String args[]) {
	Entry<String, String> e1 = twice("Hello");
	System.out.println("First result is " + e1);
	Entry<Integer,Integer> e2 = twice(7);
	System.out.println("Second result is " + e2);
    }
}

