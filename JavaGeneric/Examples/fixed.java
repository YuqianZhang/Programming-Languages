import java.util.*;

// This is the modern version of the old.java code. It uses Java
// generics, to enforce static type checking where the old version
// required run-time checks.

public class fixed {

    public static void main(String[] args) {
	List<Integer> myIntList = new LinkedList<Integer>();
	myIntList.add(new Integer(6)); 
	//myIntList.add(new Object());
	myIntList.add(new Integer(7)); 
	Iterator<Integer> i = myIntList.iterator();
	Integer x = i.next(); //Don't need cast here
	System.out.println(x);
	x =  i.next(); //Don't need cast here
	System.out.println(x);
    }
}
