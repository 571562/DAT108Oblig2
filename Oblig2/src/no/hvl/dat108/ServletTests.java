package no.hvl.dat108;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class ServletTests {

	HandleVogn forventa;
	HandleVogn actual = new HandleVogn();

	@Test
	public void testAddVare() {
		actual.addVare("Eple");
		actual.addVare("Pære");

		assertEquals(2, actual.storLeik());
		assertEquals("Eple", actual.hent(0));
		assertEquals("Pære", actual.hent(1));
	}

	@Test
	public void slettVare() {
		actual.addVare("Pære");
		actual.removeVare("Pære");
		assertTrue(actual.hent(0) == null);
	}

	@Test
	public void slettEinAvToVarer() {
		actual.addVare("Kanin");
		actual.addVare("Bein");

		actual.removeVare("Bein");
		assertEquals(1, actual.storLeik());
		for (Vare v : actual.getVarer()) {
			assertFalse("Bein".equals(v.getNamn()));
		}
	}
}
