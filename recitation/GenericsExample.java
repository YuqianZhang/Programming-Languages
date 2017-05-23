//Example of generic classes and methods in java
//Paul Fisher

import java.util.*;

//Generic class which takes a type arguement that must itself implement comparable. This class also implements Comparable
class ComparableList<E extends Comparable<E>> extends ArrayList<E> implements Comparable<ComparableList<E>> {

	//Inherits Constructor from ArrayList
	//Also inherits all of the methods like add(), get(), size(), etc.
	public ComparableList() {
		super();
	}

	//compare lists by comparing their first elements
	public int compareTo(ComparableList<E> slist) {
		//an empty list is less than a non-empty list
		if (size() > 0) {
			if (slist.size() > 0) {
				return (get(0).compareTo(slist.get(0)));
			}
			else {
				return 1;
			}
		}
		else {
			if (slist.size() > 0) {
				return -1;
			}
			//two empty lists are equal
			return 0;
		}

	}

}

//Class Heirarchy A -> B -> C
class A implements Comparable<A>{
	Integer a;

	public A(Integer i) {
		a = i;
	}

	public Integer geta() {
		return a;
	}

	public int compareTo(A other) {
		return a.compareTo(other.geta());
	}

}

class B extends A{
	Integer b;

	public B(Integer i, Integer j) {
		super(i);
		b = j;
	}

	public Integer getb() {
		return b;
	}

	public int compareTo(B other) {
		int comp = super.compareTo(other);
		//If a is equal, compare b.
		if (comp == 0) {
			return b.compareTo(other.getb());
		}
		else {
			return comp;
		}
	}
}

class C extends B{
	Integer c;

	public C(Integer i, Integer j, Integer k) {
		//inherited constructor sets a and b.
		super(i,j);
		c = k;
	}

	public Integer getc() {
		return c;
	}

	//A's B's and C's can all be compared. Will use the most most specific shared comparison method
	public int compareTo(C other) {
		int comp = super.compareTo(other);
		//If a and b are equal, compare c.
		if (comp == 0) {
			return c.compareTo(other.getc());
		}
		else {
			return comp;
		}
	}
}






public class GenericsExample {

	static void printFirst(List<Object> list) {
		System.out.println(list.get(0).toString());
	}

	static Integer getB(List<B> list){
		B b = list.get(0);
		return b.getb();
	}

	static void addB(List<B> list) {
		list.add(new B(0,0));
	}

	static void printFirstwild(List<?> list) {
		System.out.println(list.get(0).toString());
	}

	static Integer getBwild(List<? extends B> list){
		B b = list.get(0);
		return b.getb();
	}


	static void addBwild(List<? super B> list) {
		list.add(new B(0,0));
	}


	static <T> void addtoList(List<T> ts, T item) {
		ts.add(item);
	}

	static <T extends B> void addtoBList(List<B> bs, T item) {
		bs.add(item);
	}

	static <T extends A, V extends T> void addIfLeast(List<T> list, V item) {
		for (T t : list) {
			if (item.compareTo(t) >= 0) {
				return;
			}
		}
		list.add(item);
	}



	public static void main(String[] args) {
		A a = new A(1);
		B b = new B(1,2);
		C c = new C(1,2,3);
		A a2 = new A(2);
		B b2 = new B(1,3);
		C c2 = new C(1,2,4);
		ComparableList<A> as = new ComparableList<A>();
		as.add(a);
		ComparableList<A> as2 = new ComparableList<A>();
		System.out.println(as.compareTo(as2));

		ArrayList<B> bs = new ArrayList<B>();
		ArrayList<A> as3  = new ArrayList<A>();
		ArrayList<C> cs = new ArrayList<C>();
		cs.add(new C(1,2,3));

		addB(bs);
		//addB(as); Type Error
		addBwild(bs);
		addBwild(as2); //But method with wildcard works
		System.out.println(getB(bs));
		//System.out.println(getB(cs)); //Type Error
		System.out.println(getBwild(bs));
		System.out.println(getBwild(cs)); //But method with wild card works




	}

}