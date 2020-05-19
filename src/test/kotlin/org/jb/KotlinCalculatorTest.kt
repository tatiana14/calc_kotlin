package org.jb

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception

class KotlinCalculatorTest {

    @Test fun simpleTest() {
        assertEquals("simple addition", 2.0, evaluate("1 + 1"), 1e-4)
    }
    @Test fun LowPriorityOperatorsTest() {
        assertEquals("", 9.0, evaluate("6+7-8 + 4"), 1e-4)
    }

    @Test fun HighPriorityOperatorsTest() {
        assertEquals("", 4.0, evaluate("4*3/6*2"), 1e-4)
    }

    @Test fun MixedOperatorsTest() {
        assertEquals("", 22.0, evaluate("2*6+5*2"), 1e-4)
    }

    @Test fun DoubleOperandsTest() {
        assertEquals("", 25.52, evaluate("2*6+5*2+6-4+1.52"), 1e-4)
    }

    @Test fun BracesFirstTest() {
        assertEquals("", 11.00, evaluate("(2+6)+3"), 1e-4)
    }

    @Test fun BracesInTheMiddleTest() {
        assertEquals("", 29.00, evaluate("6*(3+1)+5"), 1e-4)
    }

    @Test fun SeveralBracesOnSameLevelTest() {
        assertEquals("", 18.50, evaluate("3+(7*2)+(6/4)"), 1e-4)
    }

    @Test fun NestedBracesTest() {
        assertEquals("", 193.00, evaluate("(1+8*(2+1)*(2+6))"), 1e-4)
    }

    @Test(expected = IllegalArgumentException::class)
    fun DivisionByZeroThrowsExceptionTests() {
        evaluate("1/0")
    }

}