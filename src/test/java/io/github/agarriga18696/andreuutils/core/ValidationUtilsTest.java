package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationUtilsTest {

    // --- isInteger ---

    @Test
    void acceptsValidInteger() {
        assertTrue(ValidationUtils.isInteger("123"));
    }

    @Test
    void acceptsIntegerWithWhitespace() {
        assertTrue(ValidationUtils.isInteger("  123  "));
    }

    @Test
    void acceptsNegativeInteger() {
        assertTrue(ValidationUtils.isInteger("-5"));
    }

    @Test
    void rejectsInvalidInteger() {
        assertFalse(ValidationUtils.isInteger("abc"));
    }

    @Test
    void rejectsNullInteger() {
        assertFalse(ValidationUtils.isInteger(null));
    }

    @Test
    void rejectsEmptyInteger() {
        assertFalse(ValidationUtils.isInteger(""));
    }

    @Test
    void rejectsBlankInteger() {
        assertFalse(ValidationUtils.isInteger("   "));
    }

    // --- isDecimal ---

    @Test
    void acceptsDecimalWithPeriod() {
        assertTrue(ValidationUtils.isDecimal("3.14"));
    }

    @Test
    void acceptsDecimalWithComma() {
        assertTrue(ValidationUtils.isDecimal("3,14"));
    }

    @Test
    void acceptsDecimalWithWhitespaceAndComma() {
        assertTrue(ValidationUtils.isDecimal("  -2,5  "));
    }

    @Test
    void rejectsInvalidDecimal() {
        assertFalse(ValidationUtils.isDecimal("abc"));
    }

    @Test
    void rejectsNullDecimal() {
        assertFalse(ValidationUtils.isDecimal(null));
    }

    @Test
    void rejectsEmptyDecimal() {
        assertFalse(ValidationUtils.isDecimal(""));
    }

    // --- isPositive (int) ---

    @Test
    void positiveIntegerIsPositive() {
        assertTrue(ValidationUtils.isPositive(1));
    }

    @Test
    void zeroIntegerIsNotPositive() {
        assertFalse(ValidationUtils.isPositive(0));
    }

    @Test
    void negativeIntegerIsNotPositive() {
        assertFalse(ValidationUtils.isPositive(-5));
    }

    // --- isPositive (double) ---

    @Test
    void positiveDecimalIsPositive() {
        assertTrue(ValidationUtils.isPositive(0.1));
    }

    @Test
    void zeroDecimalIsNotPositive() {
        assertFalse(ValidationUtils.isPositive(0.0));
    }

    @Test
    void negativeDecimalIsNotPositive() {
        assertFalse(ValidationUtils.isPositive(-0.5));
    }

    // --- isInRange (int) ---

    @Test
    void integerWithinRangeIsAccepted() {
        assertTrue(ValidationUtils.isInRange(5, 1, 10));
    }

    @Test
    void integerOutsideRangeIsRejected() {
        assertFalse(ValidationUtils.isInRange(0, 1, 10));
    }

    @Test
    void integerAtLowerBoundIsAccepted() {
        assertTrue(ValidationUtils.isInRange(1, 1, 10));
    }

    @Test
    void integerAtUpperBoundIsAccepted() {
        assertTrue(ValidationUtils.isInRange(10, 1, 10));
    }

    // --- isInRange (double) ---

    @Test
    void decimalWithinRangeIsAccepted() {
        assertTrue(ValidationUtils.isInRange(5.5, 1.0, 10.0));
    }

    @Test
    void decimalOutsideRangeIsRejected() {
        assertFalse(ValidationUtils.isInRange(0.9, 1.0, 10.0));
    }

    // --- isEmail ---

    @Test
    void acceptsSimpleEmail() {
        assertTrue(ValidationUtils.isEmail("a@b.cat"));
    }

    @Test
    void acceptsTwoCharacterTld() {
        assertTrue(ValidationUtils.isEmail("a@b.co"));
    }

    @Test
    void acceptsStandardEmail() {
        assertTrue(ValidationUtils.isEmail("user@example.com"));
    }

    @Test
    void acceptsEmailWithPeriodAndPlusSign() {
        assertTrue(ValidationUtils.isEmail("user.name+tag@example.co.uk"));
    }

    @Test
    void rejectsEmailWithoutValidDomain() {
        assertFalse(ValidationUtils.isEmail("no@domain"));
    }

    @Test
    void rejectsEmailStartingWithAtSign() {
        assertFalse(ValidationUtils.isEmail("@.cat"));
    }

    @Test
    void rejectsNullEmail() {
        assertFalse(ValidationUtils.isEmail(null));
    }

    @Test
    void rejectsEmptyEmail() {
        assertFalse(ValidationUtils.isEmail(""));
    }

    // --- isSpanishPhoneNumber ---

    @Test
    void acceptsPhoneNumberStartingWithSix() {
        assertTrue(ValidationUtils.isSpanishPhoneNumber("612345678"));
    }

    @Test
    void acceptsPhoneNumberStartingWithSeven() {
        assertTrue(ValidationUtils.isSpanishPhoneNumber("712345678"));
    }

    @Test
    void acceptsPhoneNumberStartingWithNine() {
        assertTrue(ValidationUtils.isSpanishPhoneNumber("912345678"));
    }

    @Test
    void rejectsPhoneNumberStartingWithFive() {
        assertFalse(ValidationUtils.isSpanishPhoneNumber("512345678"));
    }

    @Test
    void rejectsPhoneNumberThatIsTooShort() {
        assertFalse(ValidationUtils.isSpanishPhoneNumber("61234567"));
    }

    @Test
    void rejectsNullPhoneNumber() {
        assertFalse(ValidationUtils.isSpanishPhoneNumber(null));
    }

    @Test
    void rejectsEmptyPhoneNumber() {
        assertFalse(ValidationUtils.isSpanishPhoneNumber(""));
    }

    // --- isSpanishDni ---

    @Test
    void acceptsValidSpanishDni() {
        // 12345678 % 23 = 14 -> letter 'Z'
        assertTrue(ValidationUtils.isSpanishDni("12345678Z"));
    }

    @Test
    void rejectsSpanishDniWithIncorrectLetter() {
        assertFalse(ValidationUtils.isSpanishDni("12345678A"));
    }

    @Test
    void acceptsSpanishDniWithLowercaseLetter() {
        assertTrue(ValidationUtils.isSpanishDni("12345678z"));
    }

    @Test
    void acceptsSpanishDniWithWhitespace() {
        assertTrue(ValidationUtils.isSpanishDni("  12345678Z  "));
    }

    @Test
    void rejectsSpanishDniWithoutLetter() {
        assertFalse(ValidationUtils.isSpanishDni("12345678"));
    }

    @Test
    void rejectsNullSpanishDni() {
        assertFalse(ValidationUtils.isSpanishDni(null));
    }

    @Test
    void rejectsEmptySpanishDni() {
        assertFalse(ValidationUtils.isSpanishDni(""));
    }

}