(defun fibonacci (n) 
    (cond
    ((equal n 0) 0)
    ((equal n 1) 1)
    (t (+ (fibonacci (- n 1)) (fibonacci (- n 2))))
    )
)

(fibonacci 10)