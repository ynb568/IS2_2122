package es.unican.is2.ImpuestoCirculacionCommon;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Furgoneta
extends Turismo implements Serializable
{

	private boolean comercial;

	public Furgoneta(String string, LocalDate localDate, double i, boolean comercial) throws DatoInvalido{
		super(string, localDate, i);
		this.comercial = comercial;
	}

	/**
	 * Retorna el valor del atributo comercial
	 * @return true si la furgoneta es de uso comercial
	 *         false si no es de uso comercial
	 */
	public boolean getComercial() {
		return comercial;
	}

	@Override
	public double precioImpuesto() {
		double discount = 0.2;
		double impuesto = 0;
		if (this.getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25)) || this.getFechaMatriculacion().isEqual(LocalDate.now().minusYears(25))) {
			return 0;
		}
		if (this.getPotencia() < 8) {
			impuesto = 25.24;
		}
		else if (this.getPotencia() <= 11.99) {
			impuesto = 68.16;
		}
		else if (this.getPotencia() <= 15.99) {
			impuesto = 143.88;
		}
		else if (this.getPotencia() <= 19.99) {
			impuesto = 179.22;;
		}
		else if (this.getPotencia() > 19.99){
			impuesto = 224;
		}
		if (this.getComercial()) {
			impuesto -= impuesto*discount;
		}
		return impuesto;
	}
}
