package utilitats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversorTest {

	// --- aEnter ---

	@Test
	void enterTextNumeric() {
		assertEquals(123, Conversor.aEnter("123"));
	}

	@Test
	void enterTextAmbEspais() {
		assertEquals(-5, Conversor.aEnter("  -5  "));
	}

	@Test
	void enterTextInvalidRetornaNull() {
		assertNull(Conversor.aEnter("abc"));
	}

	@Test
	void enterTextBuitRetornaNull() {
		assertNull(Conversor.aEnter(""));
	}

	@Test
	void enterTextBlancRetornaNull() {
		assertNull(Conversor.aEnter("   "));
	}

	@Test
	void enterNullRetornaNull() {
		assertNull(Conversor.aEnter(null));
	}

	// --- aDecimal ---

	@Test
	void decimalAmbComa() {
		assertEquals(3.14, Conversor.aDecimal("3,14"), 1e-10);
	}

	@Test
	void decimalAmbPunt() {
		assertEquals(3.14, Conversor.aDecimal("3.14"), 1e-10);
	}

	@Test
	void decimalInvalidRetornaNull() {
		assertNull(Conversor.aDecimal("abc"));
	}

	@Test
	void decimalNullRetornaNull() {
		assertNull(Conversor.aDecimal(null));
	}

	@Test
	void decimalBuitRetornaNull() {
		assertNull(Conversor.aDecimal(""));
	}

	@Test
	void decimalAmbEspaisIComa() {
		assertEquals(-2.5, Conversor.aDecimal("  -2,5  "), 1e-10);
	}

	// --- aCadena ---

	@Test
	void cadenaText() {
		assertEquals("hola", Conversor.aCadena("hola"));
	}

	@Test
	void cadenaNumero() {
		assertEquals("42", Conversor.aCadena(42));
	}

	@Test
	void cadenaNullRetornaNull() {
		assertNull(Conversor.aCadena(null));
	}

	// --- aSiNo ---

	@Test
	void siNoVertaderEsSi() {
		assertEquals("Sí", Conversor.aSiNo(true));
	}

	@Test
	void siNoFalsEsNo() {
		assertEquals("No", Conversor.aSiNo(false));
	}

}
