package no.hvl.dat108;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * @author herbo & sondr
 */
public class ServletTests {
	HandleVogn vogn = new HandleVogn();

	@Test
	public void testAddVare() {
		vogn.addVare("Eple");
		vogn.addVare("P�re");

		assertEquals(2, vogn.storLeik());
		assertEquals("Eple", vogn.hent(0));
		assertEquals("P�re", vogn.hent(1));
	}

	@Test
	public void slettVare() {
		vogn.addVare("P�re");
		vogn.removeVare("P�re");
		assertTrue(vogn.storLeik() == 0);
	}

	@Test
	public void slettEinAvToVarer() {
		vogn.addVare("Kanin");
		vogn.addVare("Bein");

		vogn.removeVare("Bein");
		assertEquals(1, vogn.storLeik());
		for (Vare v : vogn.getVarer()) {
			assertFalse("Bein".equals(v.getNamn()));
		}
	}
}
