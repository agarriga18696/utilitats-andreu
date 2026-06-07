package utilitats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatematiquesTest {

	// --- esParell ---

	@Test
	void zeroEsParell() {
		assertTrue(Matematiques.esParell(0));
	}

	@Test
	void dosEsParell() {
		assertTrue(Matematiques.esParell(2));
	}

	@Test
	void negatiuParellEsParell() {
		assertTrue(Matematiques.esParell(-4));
	}

	@Test
	void senarNoEsParell() {
		assertFalse(Matematiques.esParell(3));
	}

	@Test
	void negatiuSenarNoEsParell() {
		assertFalse(Matematiques.esParell(-3));
	}

	// --- esMultiple ---

	@Test
	void deuEsMultipleDeCinc() {
		assertTrue(Matematiques.esMultiple(10, 5));
	}

	@Test
	void deuNoEsMultipleDeTres() {
		assertFalse(Matematiques.esMultiple(10, 3));
	}

	@Test
	void zeroEsMultipleDeQualsevolNumero() {
		assertTrue(Matematiques.esMultiple(0, 5));
	}

	@Test
	void cincEsMultipleDeUn() {
		assertTrue(Matematiques.esMultiple(5, 1));
	}

	// --- esPrimer ---

	@Test
	void zeroNoEsPrimer() {
		assertFalse(Matematiques.esPrimer(0));
	}

	@Test
	void uNoEsPrimer() {
		assertFalse(Matematiques.esPrimer(1));
	}

	@Test
	void dosEsPrimer() {
		assertTrue(Matematiques.esPrimer(2));
	}

	@Test
	void tresEsPrimer() {
		assertTrue(Matematiques.esPrimer(3));
	}

	@Test
	void quatreNoEsPrimer() {
		assertFalse(Matematiques.esPrimer(4));
	}

	@Test
	void cincEsPrimer() {
		assertTrue(Matematiques.esPrimer(5));
	}

	@Test
	void nouNoEsPrimer() {
		assertFalse(Matematiques.esPrimer(9));
	}

	@Test
	void dissetEsPrimer() {
		assertTrue(Matematiques.esPrimer(17));
	}

	@Test
	void vintICincNoEsPrimer() {
		assertFalse(Matematiques.esPrimer(25));
	}

	@Test
	void norantaSetEsPrimer() {
		assertTrue(Matematiques.esPrimer(97));
	}

	// --- aBinari ---

	@Test
	void binariDeZero() {
		assertEquals("0", Matematiques.aBinari(0));
	}

	@Test
	void binariDeUn() {
		assertEquals("1", Matematiques.aBinari(1));
	}

	@Test
	void binariDeDosCentsCinquantaCinc() {
		assertEquals("11111111", Matematiques.aBinari(255));
	}

	@Test
	void binariDeDeu() {
		assertEquals("1010", Matematiques.aBinari(10));
	}

	// --- aHexadecimal ---

	@Test
	void hexadecimalDeZero() {
		assertEquals("0", Matematiques.aHexadecimal(0));
	}

	@Test
	void hexadecimalDeDosCentsCinquantaCinc() {
		assertEquals("ff", Matematiques.aHexadecimal(255));
	}

	@Test
	void hexadecimalDeSetze() {
		assertEquals("10", Matematiques.aHexadecimal(16));
	}

	// --- binariADecimal ---

	@Test
	void binariADecimalZero() {
		assertEquals(0, Matematiques.binariADecimal("0"));
	}

	@Test
	void binariADecimalUn() {
		assertEquals(1, Matematiques.binariADecimal("1"));
	}

	@Test
	void binariADecimalMilSis() {
		assertEquals(16, Matematiques.binariADecimal("10000"));
	}

	@Test
	void binariADecimalVuitBits() {
		assertEquals(255, Matematiques.binariADecimal("11111111"));
	}

}
