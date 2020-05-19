package org.jetbrains
class Number(override var value: String) : ILexem, IExpression {

    override fun calculate(): Double {
        return value.toDouble()
    }


}