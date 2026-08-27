package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversionUtilsTest {

    // --- parseInteger ---

    @Test
    void parsesValidInteger() {
        assertEquals(123, ConversionUtils.parseInteger("123"));
    }

    @Test
    void parsesIntegerWithWhitespace() {
        assertEquals(-5, ConversionUtils.parseInteger("  -5  "));
    }

    @Test
    void invalidIntegerReturnsNull() {
        assertNull(ConversionUtils.parseInteger("abc"));
    }

    @Test
    void emptyIntegerReturnsNull() {
        assertNull(ConversionUtils.parseInteger(""));
    }

    @Test
    void blankIntegerReturnsNull() {
        assertNull(ConversionUtils.parseInteger("   "));
    }

    @Test
    void nullIntegerReturnsNull() {
        assertNull(ConversionUtils.parseInteger(null));
    }

    // --- parseDouble ---

    @Test
    void parsesDoubleWithComma() {
        Double result = ConversionUtils.parseDouble("3,14");

        assertNotNull(result);
        assertEquals(3.14, result.doubleValue(), 1e-10);
    }

    @Test
    void parsesDoubleWithPeriod() {
        Double result = ConversionUtils.parseDouble("3.14");

        assertNotNull(result);
        assertEquals(3.14, result.doubleValue(), 1e-10);
    }

    @Test
    void invalidDoubleReturnsNull() {
        assertNull(ConversionUtils.parseDouble("abc"));
    }

    @Test
    void nullDoubleReturnsNull() {
        assertNull(ConversionUtils.parseDouble(null));
    }

    @Test
    void emptyDoubleReturnsNull() {
        assertNull(ConversionUtils.parseDouble(""));
    }

    @Test
    void parsesDoubleWithWhitespaceAndComma() {
        Double result = ConversionUtils.parseDouble("  -2,5  ");

        assertNotNull(result);
        assertEquals(-2.5, result.doubleValue(), 1e-10);
    }

    // --- toString ---

    @Test
    void convertsTextToString() {
        assertEquals("hola", ConversionUtils.toString("hola"));
    }

    @Test
    void convertsNumberToString() {
        assertEquals("42", ConversionUtils.toString(42));
    }

    @Test
    void nullValueReturnsNullString() {
        assertNull(ConversionUtils.toString(null));
    }

    // --- toYesNo ---

    @Test
    void trueConvertsToYes() {
        assertEquals("Yes", ConversionUtils.toYesNo(true));
    }

    @Test
    void falseConvertsToNo() {
        assertEquals("No", ConversionUtils.toYesNo(false));
    }

}