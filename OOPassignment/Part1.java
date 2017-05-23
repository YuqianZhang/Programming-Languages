import java.util.*;

interface Addable<T>{
	T plus (T object);
} 

class MyList<T extends Comparable<T>&Addable<T>> extends ArrayList<T> implements Comparable<MyList<T>>,Addable<MyList<T>>{
	int size;
	public MyList (T z){
		size=0;
	}
	public MyList<T> plus(MyList<T> object) {
		MyList<T> n = new MyList<T>(object.get(0));
		for(int i=0;i<this.size;i++){
			n.add(this.get(i));
			n.size++;
		}
		for(int i=0;i<object.size;i++){
			n.add(object.get(i));
			n.size++;
		}
		return n;
	}

	public int compareTo(MyList<T> o) {
		if (this.size<o.size)
			return -1;
		else if (this.size>o.size)
			return 1;
		else
			return 0;
	}
	
	public String toString(){
		String s="[ ";
		for(int i=0;i<size;i++){
			s+=(get(i)+" ");
		}
		s+="]";
		return s;
	}
	
}

class A implements Comparable<A>,Addable<A>{
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
	
	public A plus(A other){
		return new A(this.a+other.geta());
	}
	
	public String toString(){
		return(getClass().getName()+"<"+ a + ">");
	}
}

class B extends A{
	public B(Integer i){
		super(i);
	}
}

public class Part1 {
	
	static<T> void insertIntoMyList(T z,MyList<? super T> L){
		L.add(z);
		L.size++;	
	}
	public static void test() {
		MyList<A> m1 = new MyList<A>(new A(0));
		MyList<A> m2 = new MyList<A>(new A(0));
		for(Integer i = 1; i <= 5; i++) {
		    insertIntoMyList(new A(i),m1);
		    insertIntoMyList(new B(i),m2);
		}

		insertIntoMyList(new A(6),m2);
		System.out.println("m1 = " + m1);
		System.out.println("m2 = " + m2);
		int result = m1.compareTo(m2);
		String s = (result < 0) ? "less than" : (result == 0) ? "equal to" : "grreater than";
		System.out.println("m1 is " + s + " m2");
		System.out.println("m1 + m2 = " + m1.plus(m2));
    }

	public static void main(String[] args) {
		test();
	    }


}
