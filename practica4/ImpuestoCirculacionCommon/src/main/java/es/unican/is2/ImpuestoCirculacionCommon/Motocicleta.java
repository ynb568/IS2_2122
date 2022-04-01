package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;
	
	public Motocicleta(String string, LocalDate localDate, int i) {
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
		if (LocalDate.now().getYear() - this.getFechaMatriculacion().getYear() > 25) {
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
