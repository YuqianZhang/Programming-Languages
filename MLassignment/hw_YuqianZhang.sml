Control.Print.printDepth :=100;
Control.Print.printLength :=100;

(*Q1*)
fun helper [] ret = ret
|   helper (x::xs) ret = helper xs (x::ret)

fun reverse L = helper L []

(*Q2*)
fun new_reverse L = 
  let fun f [] ret = ret
  |   f  (x::xs) ret = f xs (x::ret)
  in f L []
  end
	
(*Q3*)
fun reduce_depth [] =[]
|   reduce_depth  (x::xs)= x @ (reduce_depth xs)

(*Q4*)
infix elt
fun x elt [] = false
|   x elt (y::ys) = if x-y=0 then true else x elt ys

infix ++
fun  [] ++ L = L
| (x::xs) ++ L = if (x elt L )then xs ++ L else x::(xs ++ L)

infix **
fun [] ** L = []
| (x::xs) ** L = if (x elt L) then x::(xs ** L) else xs ** L

infix --
fun [] -- L = []
| (x::xs) -- L = if (x elt L) then xs -- L else x::(xs -- L)

(*Q5*)
datatype tree = leaf of int | node of int * tree * tree

(*Q6*)
datatype 'a ptree = pleaf of 'a | pnode of 'a * 'a ptree * 'a ptree 

(*Q7*)
fun interior (pleaf x) = []
|   interior (pnode (x,left,right))= interior left @ [x] @ interior right

(*Q8*)
fun mapTree f (pleaf x) = pleaf (f x)
|   mapTree f (pnode (x,left,right))= pnode (f x, mapTree f left, mapTree f right)

(*Q9*)
fun lexLess (op <) [] [] = false 
|   lexLess (op <) [] (y::ys)= true
|   lexLess (op <) (x::xs) [] = false
|   lexLess (op <) (x::xs) (y::ys) = (x < y) orelse (not (y < x) andalso (lexLess (op <) xs ys))

(*10*)
fun ptreeLess (op <) (pleaf x) (pnode y) = true
|   ptreeLess (op <) (pnode x) (pleaf y) = false
|   ptreeLess (op <) (pleaf x) (pleaf y) = (x<y)
|   ptreeLess (op <) (pnode (x,l1,r1)) (pnode (y,l2,r2)) = 
(ptreeLess (op<) l1 l2) orelse (not (ptreeLess (op<) l2 l1) andalso ((x<y) orelse not (y<x) andalso (ptreeLess (op<) r1 r2))) 
														


