package es.unican.is2.ImpuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class testMotocicleta {

	private Motocicleta moto;
	
	@Test
	public void testConstructor() throws DatoInvalido {
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 0);
		assertTrue(moto.getMatricula() != null);
	}
	
	@Test
	public void testPrecioImpouesto() throws DatoInvalido {
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 0);
		assertTrue(moto.precioImpuesto() == 8.84);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now().minusYears(15), 49);
		assertTrue(moto.precioImpuesto() == 8.84);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 125);
		assertTrue(moto.precioImpuesto() == 8.84);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 126);
		assertTrue(moto.precioImpuesto() == 15.14);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now().minusYears(30), 150);
		assertTrue(moto.precioImpuesto() == 0);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 250);
		assertTrue(moto.precioImpuesto() == 15.14);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 251);
		assertTrue(moto.precioImpuesto() == 30.30);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now().minusYears(25), 400);
		assertTrue(moto.precioImpuesto() == 0);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 500);
		assertTrue(moto.precioImpuesto() == 30.30);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 501);
		assertTrue(moto.precioImpuesto() == 60.58);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now().minusYears(24).minusMonths(11).minusDays(30), 750);
		assertTrue(moto.precioImpuesto() == 0);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 1000);
		assertTrue(moto.precioImpuesto() == 60.58);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 1001);
		assertTrue(moto.precioImpuesto() == 121.16);
		
		moto = new Motocicleta("1111-AAA", LocalDate.now(), 1250);
		assertTrue(moto.precioImpuesto() == 121.16);
		
		try {
			moto = new Motocicleta("1111-AAA", LocalDate.now(), -6);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			moto = new Motocicleta("1111-AAA", LocalDate.now().plusYears(5), -1);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			moto = new Motocicleta("1111-AAA", LocalDate.now().plusYears(3), 49);
		} catch (DatoInvalido e) {
			fail();
		}
		
		try {
			moto = new Motocicleta("1111-AAA", LocalDate.now().plusDays(1), 150);
		} catch (DatoInvalido e) {
			fail();
		}
	}

}
