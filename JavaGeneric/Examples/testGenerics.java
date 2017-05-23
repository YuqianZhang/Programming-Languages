import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

// This file constains more substantive uses of Java Generics.

class A { 

    int value = 0;

    A(int v) {
	value = v;
    }

    void add1() {
	value++;
    }

    public String toString() {
	return "A:"+value;
    }
}

class B extends A {
    B(int v) {
	super(v);
    }

    public String toString() {
	return "B:"+value;
    }


}


class C extends B {
    C(int v) {
	super(v);
    }

    public String toString() {
	return "C:"+value;
    }

}



class testGenerics {


    //Simple generic method for printing out a Collection<E>, for
    //any type E.

    static <E> void printCollection(Collection<E> c) {
	for (E e : c) {
	    System.out.print(e + " ");
	}
	System.out.println();
    }


    //Generic method for inserting the elements of one Collection<E>
    //into another Collection<E>. Note that both collections have to
    //be exactly the same type.

    static <E> void copyCollection(Collection<E> d, Collection<E> c) {
	for(E e : c)
	    d.add(e);
    }


    //Non-generic method for calling the add1 method on each 
    //value in a Collection<A>

    static void add1Collection(Collection<A> c) {
	for(A e : c)
	    e.add1();
    }


    //Generic method for calling the add1 method on each value
    //in a Collection<E>, for any type E that is a subtype of A (or
    //is A itself.

    static <E extends A> void newadd1Collection(Collection<E> c) {
	for(E e : c)
	    e.add1();
    }


    //Non-generic method that takes a Collection<B> as a parameter
    //and inserts a new B object. Since there is no subtyping on
    //Collections (or any other instances of generic classes), addB 
    //can only be called on arguments of type Collection<B>.

    static void addB(Collection<B> c) {
	B b = new B(100);
	c.add(b);
    }

    //This is a generic method that can be called on a collection of
    //any type that is a supertype of B (or B itself).  This is required
    //in order to insert a B object into the collection.

    static void newaddB(Collection<? super B> c)
    {
	B b = new B(100);
	c.add(b);
    }

    //This inserts the elements from a collection of any type that is a subtype
    //of B (or B itself) into a collection of any type that is a supertype of B
    //(or B itself).  It is a nice illustration of covariance being safe for
    //reading values from a collection and contravariance being safe for inserting
    //values into a collection.

    static void copyBCollection(Collection<? super B> d, Collection<? extends B> c) {
	for(B e : c)
	    d.add(e);
    }


    // Here is a simple function for copying elements from one collection to
    // another, in the least restrictive (but type-safe) way possible.

    static <T> void copyAnyCollection(Collection<T> d, Collection<? extends T> c) {
	for(T t: c)
	    d.add(t);
    }

    // This copying function is equivalent to the one immediately above.

    static <T> void copyAnyCollection2(Collection<? super T> d, Collection<T> c) {
	for(T t: c)
	    d.add(t);
    }



    public static void main(String args[]) {
	ArrayList<Integer> a = new ArrayList<Integer>();
	for(int i = 0; i < 10; i++) 
	    a.add(i);
	printCollection(a);
	Integer x = a.get(2) + a.get(4);
	System.out.println(x);
	ArrayList<Integer> b = new ArrayList<Integer>();
	b.add(-1);
	copyCollection(b, a);
	printCollection(b);
	Integer y = b.get(2) + b.get(4);
	System.out.println(y);

	ArrayList<A> aa = new ArrayList<A>();
	for(int i = 0; i < 10; i++) 
	    aa.add(new A(i));
	printCollection(aa);

	ArrayList<B> bb = new ArrayList<B>();
	for(int i = 0; i < 10; i++) 
	    bb.add(new B(i));
	printCollection(bb);

	add1Collection(aa);
	printCollection(aa);

	//	add1Collection(bb); //ERROR
	newadd1Collection(bb);
	printCollection(bb);

	ArrayList<C> cc = new ArrayList<C>();
	for(int i = 0; i < 10; i++) 
	    cc.add(new C(i));
	printCollection(cc);
	


	newaddB(aa);
	addB(bb);
	//	newaddB(cc); //ERROR

	ArrayList<A> aaa = new ArrayList<A>();
	ArrayList<B> bbb = new ArrayList<B>();
	ArrayList<C> ccc = new ArrayList<C>();
	copyBCollection(aaa,bb);
	//	copyBCollection(bbb,aa);  //ERROR
	copyBCollection(bbb,bb);
	copyBCollection(bbb,cc);

	//	copyBCollection(ccc,bb);   //ERROR
	System.out.print("aaa = ");
	printCollection(aaa);
	System.out.print("bbb = ");
	printCollection(bbb);
	System.out.print("ccc = ");
	printCollection(ccc);

    }
}
