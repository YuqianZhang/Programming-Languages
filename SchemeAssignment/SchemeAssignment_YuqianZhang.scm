;;;Base case: L contains one element, return the first element of L, (car L)
;;;Assumption: find-min works on (cdr L), returning the smallest element
;;;             of (cdr L).
;;;Step: let x be the result of calling (find-min(cdr L)). If (car L) is
;;;       less than x, return (car L). Otherwise, return x.
(define (find-min L)
   (cond ((NULL? (cdr L)) (car L))
         ((< (car L) (find-min (cdr L))) (car L))
         (else (find-min (cdr L)))))

;;;Base case: L has one element, return a list containing (car L) and '()
;;;Assumption: find-min-rest works on (cdr L),returning the list of the smallest element
;;;            and the rest of list
;;;Step:    let x be the result of calling (find-min-rest (cdr L)). If (car L) is
;;;         less than (car x), (cons (car x) (cadr x)) and return the list of (car L)
;;;         and (cons (car x) (cadr x). Otherwise, (cons (car l)(cadr x)) and
;;;         return the list of (car x) and (cons (car l)(cadr x))

;Version without using let
;(define (find-min-rest L)
;  (cond ((NULL? (cdr L)) (list (car L)'()))
;       ((< (car L) (car (find-min-rest (cdr L))))
;       (list (car L) (cons (car (find-min-rest(cdr L))) (cadr (find-min-rest(cdr L))))))
;     (else
;     (list (car (find-min-rest(cdr L))) (cons (car L) (cadr (find-min-rest (cdr L))))))))
(define (find-min-rest L)
  (cond ((NULL? (cdr L)) (list (car L)'()))
        ((< (car L) (car (find-min-rest (cdr L))))
         (let ((x (find-min-rest(cdr L))))
         (list (car L) (cons (car x) (cadr x)))))
        (else
         (let ((x (find-min-rest(cdr L))))
         (list (car x) (cons (car L) (cadr x)))))))

;;;Base case: L has one element, return L
;;;Assumption: sort works on cadr (find-min-rest L), returning the sorted.
;;;Step: let x be the result of (find-min-rest L), then(car x) is the smallest element
;;;      in the list. (cons(car x) (sort (cadr x)))   
(define (sort L)
  (cond ((NULL? (cdr L))  L)
        (else(cons (car(find-min-rest L)) (sort(cadr(find-min-rest L)))))))

;;;Base case: L is empty, return the sum value 0
;;;Assumption: sum-list wroks on non-empty list and return the sum of it 
;;;Step: If (car L) is non-empty list, return the sum of sum-list(car L) and
;;;      sum-list(cdr L); else return the sum of (car L) and sum-list(cdr L)
(define (sum-list L)
  (cond ((NULL? L) 0)
        ((PAIR? (car L)) (+(sum-list(car L)) (sum-list(cdr L))))
        (else(+ (car L) (sum-list (cdr L))))))

;;;Base case: L1 or L2 is empty list, return empty list 
;;;Assumption: map2 works on (cdr L1) (cdr L2) and return the list of results by function f
;;;Step: (cons (f (car L1)(car L2))(map2 f (cdr L1) (cdr L2)))    
(define (map2 f L1 L2)
   (cond ((NULL? L1) '())
         (else (cons (f (car L1) (car L2)) (map2 f (cdr L1) (cdr L2))))))

;;;Base case: If n equals m, return (list m)
;;;Assumption: nums-from works on n+1 and m, and returns a list from (+ n 1) to m
;;;Step: (cons n (nums-from (+ n 1) m))
(define (nums-from n m)
  (cond ((= n m) (list m))
         (else (cons n (nums-from (+ n 1) m)))))

;;;Base case: If L is empty, returns empty list
;;;Assumption: remove-mults works on n (cdr L), and returns the list without multiples of n
;;;Step: Let x be the result of (modulo (car L) n).
;;;      If x is less than 0,(cons (car L) (remove-mults n (cdr L)))
;;;      Otherwise,(remove-mults n (cdr L))
(define (remove-mults n L)
  (cond ((NULL? L) '())
        ((< 0 (modulo (car L) n)) (cons (car L) (remove-mults n (cdr L))))
        (else (remove-mults n (cdr L)))))
  
; call remove-mults
;;;Base case: If L is empty list, returns empty list
;;;Assumption: sieve works on (cdr L) and returns the list of elements are not
;;;            multiples of each other.
;;;Step: (cons (car L) (remove-mults (car L) (sieve(cdr L))))
(define (sieve L)
  (cond ((NULL? L) '())
        (else (cons (car L) (remove-mults (car L) (sieve(cdr L)))))))

; (prime n)
(define (prime n)
  (sieve (nums-from 2 n)))

;;;Base case: If n=1, return the function list of (lambda(x)(+ x 1))
;;;Assumption: gen-fn-list works on (- n 1), and returns the function list of
;;;            ((lambda(x)(+ x 1)) (lambda(x)(+ x 2)) ...(lambda(x)(+ x (- n 1)))
;;;Step: Append (gen-fn-list (- n 1)) (list (lambda (x) (+ x n))) 
(define (gen-fn-list n)
  (cond ((= 1 n) (list(lambda (x)(+ x 1))))
        (else (append (gen-fn-list (- n 1)) (list (lambda (x) (+ x n))) ))
        ))

