package org.jetbrains
class ExpressionBuilder {


    fun buildExpr(lexList: LexemList): IExpression{
        if (lexList.isSingleNumber())
            return lexList.lexems[0] as Number
        val opIndex = lexList.getLastLowestNotBracedOperatorIndex()
        if (opIndex>0) {
            val splitted = lexList.splitByIndex(opIndex)
            val leftExpr = buildExpr(splitted.first)
            val rightExpr = buildExpr(splitted.second)
            return Expression(leftExpr, lexList.lexems[opIndex] as Operator, rightExpr)
        }
        if(lexList.isSurroundedWithBraces()) {
            return buildExpr(lexList.getLexemListWithoutBraces())
        }
        throw Exception("Something is wrong with the expression provided. Please check the syntax")
    }

}