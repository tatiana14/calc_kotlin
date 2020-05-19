package org.jetbrains;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaCalculatorTest {
    @Test
    public void simpleTest() {
        assertEquals("simple addition", 2.0, JavaCalculator.evaluate("1 + 1"), 1e-4);
    }
}
