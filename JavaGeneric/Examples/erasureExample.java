
import java.util.*;

// This shows the erasure property of Java generics, 
// namely that the run-time representations of all
// instances of the same generic (e.g. ArrayList<..>)
// is the same. The type parameter information is only
// used at compile time.


public class erasureExample {

    public static void main(String args[]) {

	ArrayList<Integer> li = new ArrayList<Integer>();

	ArrayList<Float> lf = new ArrayList<Float>();
	
	System.out.println(li.getClass().toString());
	System.out.println(lf.getClass().toString());

	if (li.getClass() == lf.getClass()) // evaluates to true
	    System.out.println("Equal");
	else
	    System.out.println("Not Equal");
    }
}



