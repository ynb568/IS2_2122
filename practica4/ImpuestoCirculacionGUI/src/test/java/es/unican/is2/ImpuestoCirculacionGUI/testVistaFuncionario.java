package es.unican.is2.ImpuestoCirculacionGUI;

import java.util.*;
import static org.junit.Assert.*;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.ImpuestoCirculacionCommon.*;
import es.unican.is2.ImpuestoCirculacionDAO.ContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionDAO.VehiculosDAO;
import es.unican.is2.ImpuestoCirculacionBusiness.*;

public class testVistaFuncionario {

	private ContribuyentesDAO contribuyentesDAO;
	private VehiculosDAO vehiculosDAO;
	private FrameFixture demo;
	
	
	@Before
	public void setUp() {
		contribuyentesDAO = new ContribuyentesDAO();
		vehiculosDAO = new VehiculosDAO();
		GestionImpuestoCirculacion gestion = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		VistaFuncionario gui = new VistaFuncionario(gestion, gestion, gestion);
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}

	@After
	public void tearBown() {
		demo.cleanUp();
	}

	@Test
	public void testDNIValido() {
		//dni valido
		demo.button("btnBuscar").requireText("Buscar");
		demo.textBox("txtDniContribuyente").enterText("11111111A");
		demo.button("btnBuscar").click();
		
		//nombre valido
		demo.textBox("txtNombreContribuyente").requireText("Pepe López Martínez");
		
		//matriculas validas
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos = contribuyentesDAO.contribuyente("11111111A").getVehiculos();
		for (int i = 0; i < vehiculos.size(); i++) {
			if (vehiculos.get(i).getMatricula() != demo.list().valueAt(i)) {
				fail();
			}
		}
		
		//total valido
		demo.textBox("txtTotalContribuyente").requireText("224.0");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
		this.tearBown();
	}
	
	@Test
	public void testDNINoValido() {
		//dni no valido
		demo.button("btnBuscar").requireText("Buscar");
		demo.textBox("txtDniContribuyente").enterText("1212121212E");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("DNI No Válido");
		
		//total no valido
		demo.textBox("txtTotalContribuyente").requireText("0");
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
		this.tearBown();
	}

	@Test
	public void testSinVehiculos() {
		//dni valido
		demo.button("btnBuscar").requireText("Buscar");
		demo.textBox("txtDniContribuyente").enterText("44444444D");
		demo.button("btnBuscar").click();
		
		//nombre valido
		demo.textBox("txtNombreContribuyente").requireText("Yago Nava Bolado");
		
		//total valido
		demo.textBox("txtTotalContribuyente").requireText("0.0");
		
		//sin matriculas
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos = contribuyentesDAO.contribuyente("44444444D").getVehiculos();
		if (!vehiculos.isEmpty()) {
			fail();
		}
		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {e.printStackTrace();}
		this.tearBown();
	}
	
}