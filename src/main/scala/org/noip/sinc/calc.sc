val data = "11 + (exp(2.010635 + sin(PI/2) * 3) + 50) / 2"
val PI = 3.14
val E = 2.71
val number = "([0-9]*){0,1}([\\.][0-9]*){0,1}".r
number.findAllIn(data).toArray

"("
")"
"PI"
"E"

"sin".r
"exp".r
"+"
"-"
"*"