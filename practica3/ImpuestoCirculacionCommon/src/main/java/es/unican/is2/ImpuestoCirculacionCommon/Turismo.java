package es.unican.is2.ImpuestoCirculacionCommon;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Turismo
    extends Vehiculo implements Serializable
{

	private double potencia;
	
	public Turismo(String string, LocalDate localDate, double i) throws DatoInvalido {
    	super(string, localDate);
    	this.potencia = i;
    	if (this.potencia < 0) {throw new DatoInvalido();}
    	if (localDate.isAfter(LocalDate.now())) {throw new DatoInvalido();}
    	if (string == null) {throw new DatoInvalido();}
    }
	
	/**
	 * Retorna la potencia del turismo
	 * @return potencia en caballos fiscales
	 */
    public double getPotencia() {
        return potencia;
    }
       
    
    /**
     * Retorna el precio del impuesto a pagar
     *  @return precio
     */
	@Override
    public double precioImpuesto() {
		if (this.getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25)) || this.getFechaMatriculacion().isEqual(LocalDate.now().minusYears(25))) {
			return 0;
		}
		if (this.getPotencia() < 8) {
			return 25.24;
		} else if (this.getPotencia() <= 11.99) {
			return 68.16;
		} else if (this.getPotencia() <= 15.99) {
			return 143.88;
		} else if (this.getPotencia() <= 19.99) {
			return 179.22;
		} else {
			return 224;
		}	
    }
    
}
