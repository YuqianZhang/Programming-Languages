//Generic Trait. A class that extends this trait must implement + method
trait Addable[T] {
      def + (x: T): T
}

//Use an abstract class to define a type that will instantiated using case classes.
//LinkedList is generic, and its element type must extend Addable
abstract class LinkedList[T <: Addable[T]] {
	//The signature of a method that every subclass will have to have. Return type is required.
	def get(i:Int):Option[T]
}

//Node contains a label and a Node next. Subclass of abstract LinkedList
case class Node[T <: Addable[T]](label:T, next:LinkedList[T]) extends LinkedList[T] {
	//The type Option[T] is a wrapper that allows you to sometimes return a T object
	//and sometimes return None
	def get(i:Int):Option[T] = i match {
		case 0 => Some(label)
		case n => next.get(n-1)
	}
}

//Empty also extends Linked List. Even though it doesn't hold any data, it still needs the full details 
//of the Type variable.
case class Empty[T <: Addable[T]]() extends LinkedList[T] {
	def get(i:Int):Option[T] = None
}

//Genric class defined so it uses contravariant subtyping.
// This means that if C <: A, then X[A] <: X[C]
// Parameter is a function from T to Int.  Because the input type is the variable,
// contravariant subtyping is appropriate
class X[-T](fun: T => Int) {
	def getInt(t:T) = fun(t) 
}


//Class enxtends Addable so we must implement the + method
class A(x:Int) extends Addable[A] {
      val value = x

      def + (other: A) = new A(x + other.value)

      //Use override keyword to override any method or field defined in a parent class
      override def toString() = "A<" ++ x.toString() ++ ">"
}

//B <: A
class B(x: Int) extends A(x) {
      override def toString() = "B<" ++ x.toString() ++ ">"
}

//C <: B
class C(x: Int) extends B(x) {
      override def toString() =	"C<" ++	x.toString() ++	">"
}

//Singleton object which holds functions that don't require an instance of a class
//Also holds the main method.
//The object that holds the main method should have the same name as the file.
object ScalaExample {
	//Function to add up the elements of a LinkedList
	//Requires a LinkedList as input and needs a start value for starting the sum
	//Note the pattern matching. Case classes allow us to do this.
	def sum[T <: Addable[T]](ll: LinkedList[T], start: T):T = ll match {
		 case Empty() => start
		 case Node(label, next) => sum(next,start.+(label)) 
	}

	//Call x's function on a new object of type C. Expected input is type X[C]
	def testX(x:X[C]) = x.getInt(new C(1))

	def test() = {
		//Create and A, a B. and a C 
		//Note the 'new' keyword
		val a1 = new A(1)
		val b2 = new B(2)
		val c3 = new C(3)

		//Create a LinkedList with those three elements. Notice how we don't need the 
		//'new' keyword to create instances of case classes.
		val ll:LinkedList[A] = Node (a1, Node (b2, Node (c3, Empty())))

		println(sum(ll, new A(0)))

		//Two instances of type X[T]. the first is an X[A] the second is an X[C]
		val x1 = new X((a:A) => a.value)
		val x2 = new X((c:C) => c.value)


		println(testX(x1)) //Can use X[A] where X[C] is expected because of contravariant subtyping on X's
		println(testX(x2))
	}

	def main(args: Array[String])  = {test()}
}