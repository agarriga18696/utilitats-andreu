package utilitats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidacionsTest {

	// --- esEnter ---

	@Test
	void enterValid() {
		assertTrue(Validacions.esEnter("123"));
	}

	@Test
	void enterAmbEspais() {
		assertTrue(Validacions.esEnter("  123  "));
	}

	@Test
	void enterNegatiu() {
		assertTrue(Validacions.esEnter("-5"));
	}

	@Test
	void enterInvalid() {
		assertFalse(Validacions.esEnter("abc"));
	}

	@Test
	void enterNull() {
		assertFalse(Validacions.esEnter(null));
	}

	@Test
	void enterBuit() {
		assertFalse(Validacions.esEnter(""));
	}

	@Test
	void enterBlanc() {
		assertFalse(Validacions.esEnter("   "));
	}

	// --- esDecimal ---

	@Test
	void decimalAmbPunt() {
		assertTrue(Validacions.esDecimal("3.14"));
	}

	@Test
	void decimalAmbComa() {
		assertTrue(Validacions.esDecimal("3,14"));
	}

	@Test
	void decimalAmbEspaisIComa() {
		assertTrue(Validacions.esDecimal("  -2,5  "));
	}

	@Test
	void decimalInvalid() {
		assertFalse(Validacions.esDecimal("abc"));
	}

	@Test
	void decimalNull() {
		assertFalse(Validacions.esDecimal(null));
	}

	@Test
	void decimalBuit() {
		assertFalse(Validacions.esDecimal(""));
	}

	// --- esPositiu (int) ---

	@Test
	void enterPositiuEsPositiu() {
		assertTrue(Validacions.esPositiu(1));
	}

	@Test
	void enterZeroNoEsPositiu() {
		assertFalse(Validacions.esPositiu(0));
	}

	@Test
	void enterNegatiuNoEsPositiu() {
		assertFalse(Validacions.esPositiu(-5));
	}

	// --- esPositiu (double) ---

	@Test
	void decimalPositiuEsPositiu() {
		assertTrue(Validacions.esPositiu(0.1));
	}

	@Test
	void decimalZeroNoEsPositiu() {
		assertFalse(Validacions.esPositiu(0.0));
	}

	@Test
	void decimalNegatiuNoEsPositiu() {
		assertFalse(Validacions.esPositiu(-0.5));
	}

	// --- esEnRang (int) ---

	@Test
	void enterDinsRang() {
		assertTrue(Validacions.esEnRang(5, 1, 10));
	}

	@Test
	void enterForaRang() {
		assertFalse(Validacions.esEnRang(0, 1, 10));
	}

	@Test
	void enterAlLimitInferior() {
		assertTrue(Validacions.esEnRang(1, 1, 10));
	}

	@Test
	void enterAlLimitSuperior() {
		assertTrue(Validacions.esEnRang(10, 1, 10));
	}

	// --- esEnRang (double) ---

	@Test
	void decimalDinsRang() {
		assertTrue(Validacions.esEnRang(5.5, 1.0, 10.0));
	}

	@Test
	void decimalForaRang() {
		assertFalse(Validacions.esEnRang(0.9, 1.0, 10.0));
	}

	// --- esEmail ---

	@Test
	void emailSimple() {
		assertTrue(Validacions.esEmail("a@b.cat"));
	}

	@Test
	void emailTldDosCaracters() {
		assertTrue(Validacions.esEmail("a@b.co"));
	}

	@Test
	void emailStandard() {
		assertTrue(Validacions.esEmail("user@example.com"));
	}

	@Test
	void emailAmbPuntIMes() {
		assertTrue(Validacions.esEmail("user.name+tag@example.co.uk"));
	}

	@Test
	void emailSenseDomini() {
		assertFalse(Validacions.esEmail("no@domain"));
	}

	@Test
	void emailArrobaInicial() {
		assertFalse(Validacions.esEmail("@.cat"));
	}

	@Test
	void emailNull() {
		assertFalse(Validacions.esEmail(null));
	}

	@Test
	void emailBuit() {
		assertFalse(Validacions.esEmail(""));
	}

	// --- esTelefon ---

	@Test
	void telefonSisValids() {
		assertTrue(Validacions.esTelefon("612345678"));
	}

	@Test
	void telefonSetValids() {
		assertTrue(Validacions.esTelefon("712345678"));
	}

	@Test
	void telefonNouValids() {
		assertTrue(Validacions.esTelefon("912345678"));
	}

	@Test
	void telefonCincNoValid() {
		assertFalse(Validacions.esTelefon("512345678"));
	}

	@Test
	void telefonMassaCurt() {
		assertFalse(Validacions.esTelefon("61234567"));
	}

	@Test
	void telefonNull() {
		assertFalse(Validacions.esTelefon(null));
	}

	@Test
	void telefonBuit() {
		assertFalse(Validacions.esTelefon(""));
	}

	// --- esDNI ---

	@Test
	void dniValid() {
		// 12345678 % 23 = 14  →  lletra 'Z'
		assertTrue(Validacions.esDNI("12345678Z"));
	}

	@Test
	void dniLletraIncorrecta() {
		assertFalse(Validacions.esDNI("12345678A"));
	}

	@Test
	void dniMinuscules() {
		assertTrue(Validacions.esDNI("12345678z"));
	}

	@Test
	void dniAmbEspais() {
		assertTrue(Validacions.esDNI("  12345678Z  "));
	}

	@Test
	void dniSenseLletra() {
		assertFalse(Validacions.esDNI("12345678"));
	}

	@Test
	void dniNull() {
		assertFalse(Validacions.esDNI(null));
	}

	@Test
	void dniBuit() {
		assertFalse(Validacions.esDNI(""));
	}

}
