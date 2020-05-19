package org.jetbrains

/*
Create a simple calculator that given a string of operators (), +, -, *, / and numbers separated by spaces
returns the value of that expression

Example: evaluate("1 + 1") => 2
*/
fun evaluate(str: String): Double {
    val lexList = Parser().parse(str)
    lexList.lexems.forEach { print(it.value) }
    val exp = ExpressionBuilder().buildExpr(lexList)
    val res = exp.calculate()
    print(" = $res\n")
    return res
}
