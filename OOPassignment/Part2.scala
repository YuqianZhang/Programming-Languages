class A (x:Int) extends Ordered[A]{
	val value = x
	def compare(that:A) = this.x-that.value
	override def toString()="A<"++x.toString()++">"
}

class B (x:Int, y:Int) extends A(x+y){
	val value2 = x+y
	def compare(that:B) = this.value2-that.value2
	override def toString() = "B<" ++ x.toString() ++","++y.toString()++ ">"

}

class C[+T](fun: Int => T){
	def apply(x:Int) = fun(x)
}

abstract class Tree[T<:Ordered[T]]{
	def insert(x:T):Tree[T]
}

case class Leaf[T<:Ordered[T]] (label:T)extends Tree[T]{
	val leafvalue  = label
	override def insert(x:T):Tree[T] = {
		if (x.compare(leafvalue)<0){
			return Node(leafvalue,Leaf(x),Empty())
		}
		else{
			return Node(leafvalue,Empty(),Leaf(x))
		}
	}
	override def toString() =leafvalue.toString
}

case class Node[T<:Ordered[T]] (label:T, left:Tree[T],right:Tree[T]) extends Tree[T]{
	val nlabel=label
	override def insert(x:T):Tree[T] = {
		if (x.compare(nlabel)<0||x.compare(nlabel)==0){
			return Node(nlabel,left.insert(x),right)			
		}
		else {
			return Node(nlabel,left,right.insert(x))
		}
	}
	override def toString() ="("++left.toString++","++label.toString++","++right.toString++")"
}

case class Empty[T<:Ordered[T]]()extends Tree[T]{
	override def insert(x:T):Tree[T] = new Leaf(x)
	override def toString() =""
}

object Part2{
	def applyTo10(c:C[A]) = c.apply(10)

	def found[T<:Ordered[T]] (y:T,tr:Tree[T]):Boolean = tr match {
		case Empty()=>return false
		case Node(label,left,right)=>if(y.compare(label)==0) return true 
									else if(y.compare(label)<0) return found(y,left)
									else return found(y,right)
		case Leaf(label)=>if (y.compare(label)==0) return true else return false
	}

	def test() {		
	    val c1 = new C((x:Int) => new A(x))
	    println(c1.apply(3))

	    val c2 = new C((x:Int) => new B(x+1, x+2))
	    println(c2.apply(3))

	    println(applyTo10(c1))
	    println(applyTo10(c2))  //relies on covariant subtyping

	    var t1: Tree[A] = Empty()

	    t1 = t1.insert(new A(4))
	    t1 = t1.insert(new A(3))
	    t1 = t1.insert(new B(4,1))
	    t1 = t1.insert(new A(2))
	    println(t1)

	    val a3 = new A(3)
	    val a5 = new A(5)
	    val b21 = new B(2,1)
	    val b42 = new B(4,2)

	    if (found(a3, t1))
	       println(a3 + "is found in " + t1)
	    else
	       println(a3 + "is not found in " + t1)

	    if (found(a5, t1))
	       println(a5 + "is found in " + t1)
	    else
	       println(a5 + "is not found in " + t1)

	    if (found(b21, t1))
	       println(b21 + "is found in " + t1)
	    else
	       println(b21 + "is not found in " + t1)

	    if (found(b42, t1))
	       println(b42 + "is found in " + t1)
	    else
	       println(b42 + "is not found in " + t1)

	  }
	  def main(args: Array[String])  = {test()}
}