package es.unican.is2.ImpuestoCirculacionCommon;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Furgoneta
    extends Turismo implements Serializable
{
    
    private double potencia;
    private boolean comercial;
    
    public Furgoneta(String string, LocalDate localDate, int i) {
    	super(string, localDate, i);
    }
    
   /**
    * Retorna el valor del atributo comercial
    * @return true si la furgoneta es de uso comercial
    *         false si no es de uso comercial
    */
    public boolean getComercial() {
    	return comercial;
    }
    
    /**
	 * Retorna la potencia de la furgoneta
	 * @return potencia en caballos fiscales
	 */
    public double getPotencia() {
        return potencia;
    }
    
  
	@Override
	public double precioImpuesto() {
		double precio;
		if (LocalDate.now().getYear() - this.getFechaMatriculacion().getYear() > 25) {
			return 0;
		}
    	if (this.getPotencia() < 8 ) {
    		precio = 25.24;
    	} else if (this.getPotencia() <= 11.99) {
    		precio = 68.16;
    	} else if (this.getPotencia() <= 15.99) {
    		precio = 143.88;
    	} else if (this.getPotencia() < 19.99) {
    		precio = 179.22;
    	} else {
    		precio = 224.00;
    	}
    	
    	if (this.getComercial()) {
    		precio = precio * 1.2;
    	}
    	
    	return precio;
    }
}
