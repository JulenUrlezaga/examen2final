package base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestPrincipal {


	@Test
	void testSolicitarPermiso1() {
		assertFalse(Principal.solicitarPermiso(10));
	}
	@Test
	void testSolicitarPermiso2() {
		assertTrue(Principal.solicitarPermiso(80));
	}

}
