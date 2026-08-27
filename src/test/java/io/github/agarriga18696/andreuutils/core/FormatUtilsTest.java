package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormatUtilsTest {

    // --- formatDecimal ---

    @Test
    void formatsWithTwoDecimalPlaces() {
        assertEquals("3,14", FormatUtils.formatDecimal(3.14159, 2));
    }

    @Test
    void formatsWithFiveDecimalPlaces() {
        assertEquals("0,10000", FormatUtils.formatDecimal(0.1, 5));
    }

    @Test
    void formatsWithoutDecimalPlaces() {
        assertEquals("2", FormatUtils.formatDecimal(2.0, 0));
    }

    // --- toUpperCase ---

    @Test
    void convertsTextToUpperCase() {
        assertEquals("HOLA", FormatUtils.toUpperCase("Hola"));
    }

    @Test
    void upperCaseReturnsNullForNullInput() {
        assertNull(FormatUtils.toUpperCase(null));
    }

    @Test
    void upperCasePreservesEmptyString() {
        assertEquals("", FormatUtils.toUpperCase(""));
    }

    // --- toLowerCase ---

    @Test
    void convertsTextToLowerCase() {
        assertEquals("hola", FormatUtils.toLowerCase("Hola"));
    }

    @Test
    void lowerCaseReturnsNullForNullInput() {
        assertNull(FormatUtils.toLowerCase(null));
    }

    @Test
    void lowerCasePreservesEmptyString() {
        assertEquals("", FormatUtils.toLowerCase(""));
    }

    // --- capitalize ---

    @Test
    void capitalizesSingleWord() {
        assertEquals("Hola", FormatUtils.capitalize("hola"));
    }

    @Test
    void preservesAlreadyCapitalizedWord() {
        assertEquals("Hola", FormatUtils.capitalize("Hola"));
    }

    @Test
    void normalizesMixedCaseText() {
        assertEquals("Hola món", FormatUtils.capitalize("hOLA mÓN"));
    }

    @Test
    void capitalizeReturnsNullForNullInput() {
        assertNull(FormatUtils.capitalize(null));
    }

    @Test
    void capitalizePreservesEmptyString() {
        assertEquals("", FormatUtils.capitalize(""));
    }

    // --- capitalizeWords ---

    @Test
    void capitalizesEveryWord() {
        assertEquals("Hola Món", FormatUtils.capitalizeWords("hola món"));
    }

    @Test
    void capitalizeWordsReturnsNullForNullInput() {
        assertNull(FormatUtils.capitalizeWords(null));
    }

    @Test
    void capitalizeWordsPreservesEmptyString() {
        assertEquals("", FormatUtils.capitalizeWords(""));
    }

}