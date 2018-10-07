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
		vogn.addVare("Pære");

		assertEquals(2, vogn.storLeik());
		assertEquals("Eple", vogn.hent(0));
		assertEquals("Pære", vogn.hent(1));
	}

	@Test
	public void slettVare() {
		vogn.addVare("Pære");
		vogn.removeVare("Pære");
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
