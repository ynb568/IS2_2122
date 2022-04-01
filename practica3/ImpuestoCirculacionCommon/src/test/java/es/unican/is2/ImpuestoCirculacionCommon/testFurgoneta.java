package es.unican.is2.ImpuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class testFurgoneta {

private Furgoneta furgo;
	
	@Test
	public void testConstructor() throws DatoInvalido {
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 0, false);
		assertTrue(furgo.getMatricula() != null);
	}
	
	@Test
	public void testPrecioImpouesto() throws DatoInvalido {
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 0, true);
		assertTrue(furgo.precioImpuesto() == 20.192);
				
		furgo = new Furgoneta("1111-AAA", LocalDate.now().minusYears(15), 6, false);
		assertTrue(furgo.precioImpuesto() == 25.24);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 7.99, false);
		assertTrue(furgo.precioImpuesto() == 25.24);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 8, false);
		assertTrue(furgo.precioImpuesto() == 68.16);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now().minusYears(30), 11, false);
		assertTrue(furgo.precioImpuesto() == 0);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 11.99, false);
		assertTrue(furgo.precioImpuesto() == 68.16);
				
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 12, false);
		assertTrue(furgo.precioImpuesto() == 143.88);
				
		furgo = new Furgoneta("1111-AAA", LocalDate.now().minusYears(25), 14, false);
		assertTrue(furgo.precioImpuesto() == 0);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 15.99, false);
		assertTrue(furgo.precioImpuesto() == 143.88);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 16, false);
		assertTrue(furgo.precioImpuesto() == 179.22);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now().minusYears(24).minusMonths(11).minusDays(30), 18, false);
		assertTrue(furgo.precioImpuesto() == 0);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 19.99, false);
		assertTrue(furgo.precioImpuesto() == 179.22);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 20, false);
		assertTrue(furgo.precioImpuesto() == 224);
		
		furgo = new Furgoneta("1111-AAA", LocalDate.now(), 23, false);
		assertTrue(furgo.precioImpuesto() == 224);
		
		try {
			furgo = new Furgoneta("1111-AAA", LocalDate.now(), -6, false);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			furgo = new Furgoneta("1111-AAA", LocalDate.now(), -0.01, false);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			furgo = new Furgoneta("1111-AAA", LocalDate.now().plusYears(3), 6, false);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			furgo = new Furgoneta("1111-AAA", LocalDate.now().plusDays(1), 12, false);
		} catch (DatoInvalido e) {
			fail();
		}
	}

}
