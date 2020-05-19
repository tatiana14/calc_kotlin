package org.jb
class LexemList(var lexems: List<ILexem>) {


     fun isSurroundedWithBraces() :Boolean{
        return lexems.first() is Delimiter
                && (lexems.first() as Delimiter).type == BraceType.OPEN
                && lexems.last() is Delimiter
                && (lexems.last() as Delimiter).type == BraceType.CLOSE
    }

     fun getLexemListWithoutBraces() : LexemList{
        return LexemList(lexems.subList(1, lexems.count() - 1))
    }

     fun getLastLowestNotBracedOperatorIndex(): Int {
        val braces = getBracesRange()
        val lowOp = getLastNotBracedOperatorByPriority( OperatorPriority.LOW ,  braces)
        return if (lowOp >0) lowOp else getLastNotBracedOperatorByPriority( OperatorPriority.HIGH ,  braces)
    }

    private fun getLastNotBracedOperatorByPriority(priority : OperatorPriority,  braces: ArrayList<Int>) : Int{
        return lexems.withIndex().indexOfLast { (ind,it)-> it is Operator && it.priority == priority && ind !in braces}
    }


    private fun getBracesRange(offset:Int = 0)  : ArrayList<Int> {
        val res = ArrayList<Int>()
        if (lexems.any{it is Delimiter}) {
            var openBraceCount = 0
            val startInd = lexems.indexOfFirst { it is Delimiter && it.type == BraceType.OPEN} + offset
            val endInd : Int
            for ((index, lex) in lexems.withIndex()) {
                if (lex is Delimiter ) {
                    if (lex.type == BraceType.OPEN) {
                        openBraceCount++
                        continue
                    }
                    if (lex.type == BraceType.CLOSE) {
                        openBraceCount--
                        if (openBraceCount == 0) {
                            endInd = index + offset
                            res.addAll((startInd..endInd))
                            val bracesInTail = LexemList(lexems.slice(endInd + 1 until lexems.count())).getBracesRange(endInd + 1)
                            res.addAll(bracesInTail)
                            break
                        }
                    }
                }

            }
        }
        return res
    }

    fun isSingleNumber():Boolean {
        return lexems.count()==1 && lexems.first() is Number
    }

    fun splitByIndex(ind:Int) =
            Pair(
                   LexemList(lexems.slice(0 until ind)),
                   LexemList(lexems.slice(ind+1 until lexems.count()))

    )
}