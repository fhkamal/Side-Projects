;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Calculator) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #t)))

(define (calculator lst)
  (cond[(= (length lst) 1) (first lst)]
       [else (calculator (operations lst))]))

(define (operations lst)
  (cond[(not (false? (list-check lst)))
        (operations (list-operations lst))]
       [else (add-subtract-check (multiply-divide-check (exponent-check lst)))]))

(define (list-check lst)
  (cond[(empty? (rest lst)) false]
       [(list? (first lst)) true]
       [(list? (third lst)) true]
       [else (list-check (rest (rest lst)))]))

(define (list-operations lst)
  (cond[(empty? lst) empty]
       [(list? (first lst)) (append (operations (first lst)) (list-operations (rest lst)))]
       [else (cons (first lst) (cons (second lst)
                                     (list-operations (rest (rest lst)))))]))

(define (exponent-check lst)
  (cond[(empty? lst) empty]
       [(= 1 (length lst)) lst]
       [(equal? (second lst) '^) (exponent-check (cons (expt (first lst) (third lst))
                                                       (rest (rest (rest lst)))))]
       [else (cons (first lst)
                   (cons (second lst)
                         (exponent-check (rest (rest lst)))))]))

(define (multiply-divide-check lst)
  (cond[(empty? lst) empty]
       [(= 1 (length lst)) lst]
       [(or (equal? (second lst) *) (symbol=? (second lst) '*))
        (multiply-divide-check (cons (* (first lst) (third lst))
                                     (rest (rest (rest lst)))))]
       [(or (equal? (second lst) /) (symbol=? (second lst) '/))
        (multiply-divide-check (cons (/ (first lst) (third lst))
                                     (rest (rest (rest lst)))))]
       [else (cons (first lst)
                   (cons (second lst)
                         (multiply-divide-check (rest (rest lst)))
                         ))]))

(define (add-subtract-check lst)
  (cond[(empty? lst) empty]
       [(= 1 (length lst)) lst]
       [(or (equal? (second lst) +) (symbol=? (second lst) '+))
        (add-subtract-check (cons (+ (first lst) (third lst))
                                  (rest (rest (rest lst)))))]
       [(or (equal? (second lst) -) (symbol=? (second lst) '-))
        (add-subtract-check (cons (- (first lst) (third lst))
                                  (rest (rest (rest lst)))))]
       [else (cons (first lst)
                   (cons (second lst)
                         (add-subtract-check (rest (rest lst))
                                             )))]))





