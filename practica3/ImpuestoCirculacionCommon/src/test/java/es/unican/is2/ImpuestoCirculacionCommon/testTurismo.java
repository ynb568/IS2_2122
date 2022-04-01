package es.unican.is2.ImpuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class testTurismo {
	
	private Turismo coche;
	
	@Test
	public void testConstructor() throws DatoInvalido {
		coche = new Turismo("1111-AAA", LocalDate.now(), 0);
		assertTrue(coche.getMatricula() != null);
	}
	
	@Test
	public void testPrecioImpouesto() throws DatoInvalido {
		coche = new Turismo("1111-AAA", LocalDate.now(), 0);
		assertTrue(coche.precioImpuesto() == 25.24);
		
		coche = new Turismo("1111-AAA", LocalDate.now().minusYears(15), 6);
		assertTrue(coche.precioImpuesto() == 25.24);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 7.99);
		assertTrue(coche.precioImpuesto() == 25.24);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 8);
		assertTrue(coche.precioImpuesto() == 68.16);
		
		coche = new Turismo("1111-AAA", LocalDate.now().minusYears(30), 11);
		assertTrue(coche.precioImpuesto() == 0);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 11.99);
		assertTrue(coche.precioImpuesto() == 68.16);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 12);
		assertTrue(coche.precioImpuesto() == 143.88);
		
		coche = new Turismo("1111-AAA", LocalDate.now().minusYears(25), 14);
		assertTrue(coche.precioImpuesto() == 0);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 15.99);
		assertTrue(coche.precioImpuesto() == 143.88);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 16);
		assertTrue(coche.precioImpuesto() == 179.22);
		
		coche = new Turismo("1111-AAA", LocalDate.now().minusYears(24).minusMonths(11).minusDays(30), 18);
		assertTrue(coche.precioImpuesto() == 179.22);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 19.99);
		assertTrue(coche.precioImpuesto() == 179.22);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 20);
		assertTrue(coche.precioImpuesto() == 224);
		
		coche = new Turismo("1111-AAA", LocalDate.now(), 23);
		assertTrue(coche.precioImpuesto() == 224);
		
		try {
			coche = new Turismo("1111-AAA", LocalDate.now(), -6);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			coche = new Turismo("1111-AAA", LocalDate.now(), -0.01);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			coche = new Turismo("1111-AAA", LocalDate.now().plusYears(3), 6);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			coche = new Turismo("1111-AAA", LocalDate.now().plusDays(1), 12);
		} catch (DatoInvalido e) {
			fail();
		}
	}

}
