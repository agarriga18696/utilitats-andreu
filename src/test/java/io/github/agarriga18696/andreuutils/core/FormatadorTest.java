package io.github.agarriga18696.andreuutils.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormatadorTest {

	// --- decimal ---

	@Test
	void decimalAmbDosDecimals() {
		assertEquals("3,14", Formatador.decimal(3.14159, 2));
	}

	@Test
	void decimalAmbCincDecimals() {
		assertEquals("0,10000", Formatador.decimal(0.1, 5));
	}

	@Test
	void decimalSenseDecimals() {
		assertEquals("2", Formatador.decimal(2.0, 0));
	}

	// --- majuscules ---

	@Test
	void majusculesTextNormal() {
		assertEquals("HOLA", Formatador.majuscules("Hola"));
	}

	@Test
	void majusculesNullRetornaNull() {
		assertNull(Formatador.majuscules(null));
	}

	@Test
	void majusculesBuit() {
		assertEquals("", Formatador.majuscules(""));
	}

	// --- minuscules ---

	@Test
	void minusculesTextNormal() {
		assertEquals("hola", Formatador.minuscules("Hola"));
	}

	@Test
	void minusculesNullRetornaNull() {
		assertNull(Formatador.minuscules(null));
	}

	@Test
	void minusculesBuit() {
		assertEquals("", Formatador.minuscules(""));
	}

	// --- capitalitzar ---

	@Test
	void capitalitzarUnicaParaula() {
		assertEquals("Hola", Formatador.capitalitzar("hola"));
	}

	@Test
	void capitalitzarJaCapitalitzada() {
		assertEquals("Hola", Formatador.capitalitzar("Hola"));
	}

	@Test
	void capitalitzarMajusculesBarrejades() {
		assertEquals("Hola món", Formatador.capitalitzar("hOLA mÓN"));
	}

	@Test
	void capitalitzarNullRetornaNull() {
		assertNull(Formatador.capitalitzar(null));
	}

	@Test
	void capitalitzarBuit() {
		assertEquals("", Formatador.capitalitzar(""));
	}

	// --- capitalitzarParaules ---

	@Test
	void capitalitzarParaulesNormals() {
		assertEquals("Hola Món", Formatador.capitalitzarParaules("hola món"));
	}

	@Test
	void capitalitzarParaulesNullRetornaNull() {
		assertNull(Formatador.capitalitzarParaules(null));
	}

	@Test
	void capitalitzarParaulesBuit() {
		assertEquals("", Formatador.capitalitzarParaules(""));
	}

}
