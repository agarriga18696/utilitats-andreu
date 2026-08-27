package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathUtilsTest {

    // --- isEven ---

    @Test
    void zeroIsEven() {
        assertTrue(MathUtils.isEven(0));
    }

    @Test
    void twoIsEven() {
        assertTrue(MathUtils.isEven(2));
    }

    @Test
    void negativeEvenNumberIsEven() {
        assertTrue(MathUtils.isEven(-4));
    }

    @Test
    void oddNumberIsNotEven() {
        assertFalse(MathUtils.isEven(3));
    }

    @Test
    void negativeOddNumberIsNotEven() {
        assertFalse(MathUtils.isEven(-3));
    }

    // --- isMultipleOf ---

    @Test
    void tenIsMultipleOfFive() {
        assertTrue(MathUtils.isMultipleOf(10, 5));
    }

    @Test
    void tenIsNotMultipleOfThree() {
        assertFalse(MathUtils.isMultipleOf(10, 3));
    }

    @Test
    void zeroIsMultipleOfAnyNonZeroNumber() {
        assertTrue(MathUtils.isMultipleOf(0, 5));
    }

    @Test
    void fiveIsMultipleOfOne() {
        assertTrue(MathUtils.isMultipleOf(5, 1));
    }

    // --- isPrime ---

    @Test
    void zeroIsNotPrime() {
        assertFalse(MathUtils.isPrime(0));
    }

    @Test
    void oneIsNotPrime() {
        assertFalse(MathUtils.isPrime(1));
    }

    @Test
    void twoIsPrime() {
        assertTrue(MathUtils.isPrime(2));
    }

    @Test
    void threeIsPrime() {
        assertTrue(MathUtils.isPrime(3));
    }

    @Test
    void fourIsNotPrime() {
        assertFalse(MathUtils.isPrime(4));
    }

    @Test
    void fiveIsPrime() {
        assertTrue(MathUtils.isPrime(5));
    }

    @Test
    void nineIsNotPrime() {
        assertFalse(MathUtils.isPrime(9));
    }

    @Test
    void seventeenIsPrime() {
        assertTrue(MathUtils.isPrime(17));
    }

    @Test
    void twentyFiveIsNotPrime() {
        assertFalse(MathUtils.isPrime(25));
    }

    @Test
    void ninetySevenIsPrime() {
        assertTrue(MathUtils.isPrime(97));
    }

    // --- toBinary ---

    @Test
    void convertsZeroToBinary() {
        assertEquals("0", MathUtils.toBinary(0));
    }

    @Test
    void convertsOneToBinary() {
        assertEquals("1", MathUtils.toBinary(1));
    }

    @Test
    void convertsTwoHundredFiftyFiveToBinary() {
        assertEquals("11111111", MathUtils.toBinary(255));
    }

    @Test
    void convertsTenToBinary() {
        assertEquals("1010", MathUtils.toBinary(10));
    }

    // --- toHexadecimal ---

    @Test
    void convertsZeroToHexadecimal() {
        assertEquals("0", MathUtils.toHexadecimal(0));
    }

    @Test
    void convertsTwoHundredFiftyFiveToHexadecimal() {
        assertEquals("ff", MathUtils.toHexadecimal(255));
    }

    @Test
    void convertsSixteenToHexadecimal() {
        assertEquals("10", MathUtils.toHexadecimal(16));
    }

    // --- binaryToDecimal ---

    @Test
    void convertsBinaryZeroToDecimal() {
        assertEquals(0, MathUtils.binaryToDecimal("0"));
    }

    @Test
    void convertsBinaryOneToDecimal() {
        assertEquals(1, MathUtils.binaryToDecimal("1"));
    }

    @Test
    void convertsBinarySixteenToDecimal() {
        assertEquals(16, MathUtils.binaryToDecimal("10000"));
    }

    @Test
    void convertsEightBitBinaryToDecimal() {
        assertEquals(255, MathUtils.binaryToDecimal("11111111"));
    }

}