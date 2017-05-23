(*ML practice functions - Paul Fisher)
(* Function to insert an element into a list at index i
 Type: insert = fn : 'a -> int -> 'a list -> 'a list *)

fun insert el _ [] = el::[]
 |  insert el 0 list = el::list
 |  insert el i (x::xs) = x::(insert el (i - 1) xs)



(*1*)
(*What is the type?*)
fun example f (op <) x (y,z) =
    if x < y then f y else z + 1




(*2*)
(* write a function with type: fn : ('a -> real) -> ('b -> 'a) -> 'b list -> real *)

fun two f g [] = 3.3
|	two f g (x::xs) = f (g x)


(*3*)
(* write a function with type: fn : ('a -> int -> 'b) -> int -> (a' -> b') *)



(*4*)
fun ex4 a b [] = a
|	ex4 a b (x::xs) = 
		let val v = (ex4 (b x) b xs)
		in if a < v then v else a
		end

































(*1*)
  (*Type: example = fn : ('a -> int) -> ('b * 'a -> bool) -> 'b -> 'a * int -> int *)

(*2*)
fun test f g [] = 0.0
|	test f g (x::xs) = f (g x) + test f g xs


(*3*)
fun test2 f x = fn a => f a (x * 2);

(*4*)
(*  val ex4 = fn : int -> ('a -> int) -> 'a list -> int  *)