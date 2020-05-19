package org.jb

class Expression(var left: IExpression, var op: Operator, var right: IExpression): IExpression {
    override fun calculate(): Double {
        var res :Double? = null
        when (op.value) {
            "+" -> res = left.calculate() + right.calculate()
            "-" -> res = left.calculate() - right.calculate()
            "*" -> res = left.calculate() * right.calculate()
            "/" -> {
                val rightValue =right.calculate()
                if(rightValue == 0.0)
                    throw IllegalArgumentException("Can't divide by 0")
                res = left.calculate() / right.calculate()
            }
        }
        return res!!

    }
}