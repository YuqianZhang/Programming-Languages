import java.util.*;

// This is the old way of using Collection, List, LinkedList, etc.
// It requires casts that may generate run-time type errors (exceptions)

public class old {

    public static void main(String[] args) {
	List myIntList = new LinkedList();
	myIntList.add(new Integer(6)); 
	myIntList.add(new Object());
	Iterator i = myIntList.iterator();
	Integer x = (Integer) i.next();  //NEED CAST HERE
	System.out.println(x);
	x =  (Integer) i.next(); //NEED CAST HERE
	System.out.println(x);
    }
}


