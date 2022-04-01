package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;
	
	public Motocicleta(String string, LocalDate localDate, int i) throws DatoInvalido {
    	super(string, localDate);
    	this.cilindrada = i;
    }

    /**
     * Retorna la cilindrada de la motocicleta
     * @return cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }
    
  
	@Override
    public double precioImpuesto() {
		if (this.getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25)) || this.getFechaMatriculacion().isEqual(LocalDate.now().minusYears(25))) {
			return 0;
		}
		if (this.getCilindrada() <= 125) {
			return 8.84;
		} else if (this.getCilindrada() <= 250) {
			return 15.14;
		} else if (this.getCilindrada() <= 500) {
			return 30.30;
		} else if (this.getCilindrada() <= 1000) {
			return 60.58;
		} else {
			return 121.16;
		}	
    }
}
