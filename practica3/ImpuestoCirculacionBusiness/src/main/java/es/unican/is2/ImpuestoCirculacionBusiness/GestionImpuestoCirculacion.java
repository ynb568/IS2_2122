package es.unican.is2.ImpuestoCirculacionBusiness;
import es.unican.is2.ImpuestoCirculacionCommon.*;

/**
 * Clase que implementa la capa de negocio de la aplicacion
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}
	
	public Contribuyente altaContribuyente(Contribuyente c) {
		if (contribuyentes.contribuyentes().contains(c)) {
			return null;
		}
		contribuyentes.creaContribuyente(c);
		return c;
	}

	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		Contribuyente c = contribuyente(dni);
		if (!contribuyentes.contribuyentes().contains(c)) {
			return null;
		}
		if (c.getVehiculos() != null) {
			throw new OperacionNoValida("Contribuyente existe pero tiene vehiculos a su nombre");
		}
		contribuyentes.eliminaContribuyente(dni);
		return c;		
	}
	
	public Contribuyente contribuyente(String dni) {
		Contribuyente c = contribuyentes.contribuyente(dni);
		return c;
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		Contribuyente c = contribuyente(dni);
		if (!contribuyentes.contribuyentes().contains(c)) {
			return null;
		}
		if (c.getVehiculos().contains(v)) {
			throw new OperacionNoValida("El vehiculo ya pertenece al contribuyente");
		}
		vehiculos.creaVehiculo(v);
		c.getVehiculos().add(v);
		contribuyentes.actualizaContribuyente(c);
		return v;
	}

	
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		Vehiculo v = vehiculo(matricula);
		Contribuyente c = contribuyente(dni);
		
		if (!vehiculos.vehiculos().contains(v)) {
			return null;
		}
		if (!contribuyentes.contribuyentes().contains(c)) {
			return null;
		}
		if (!c.getVehiculos().contains(v)) {
			throw new OperacionNoValida("EL vehiculo no pertenece al contribuyente");
		}
		c.getVehiculos().remove(v);
		vehiculos.eliminaVehiculo(matricula);
		contribuyentes.actualizaContribuyente(c);
		return v;
	}

	
	public Vehiculo vehiculo(String matricula) {
		Vehiculo v = vehiculos.vehiculo(matricula);
		return v;
	}	
}

