package org.jb
class Parser {

    fun parse(str:String): LexemList {
        var res = ArrayList<ILexem>()
        val pattern = "(-|\\+|\\*|/|\\(|\\)|\\d+\\.*\\d*)".toRegex()
        val matchResults = pattern.findAll(str)
        for (match in matchResults) {
            when (match.value) {
                "+", "-" -> res.add(Operator(match.value, OperatorPriority.LOW))
                "*", "/" -> res.add(Operator(match.value, OperatorPriority.HIGH))
                "(" -> res.add(Delimiter(match.value, BraceType.OPEN))
                ")" -> res.add(Delimiter(match.value, BraceType.CLOSE))
                else -> {
                    res.add(Number(match.value))
                }
            }
        }
        return LexemList(res)
    }
}