package es.unican.is2.gestionTienda.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.gestionTienda.Tienda;
import es.unican.is2.gestionTienda.Vendedor;
import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gesti�n de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) { 		// WMC + 1
		// opciones del menu
		final int NUEVA_VENTA = 0, VENDEDOR_DEL_MES = 1, VENDEDORES = 2;

		// variables auxiliares
		String dni;
		Lectura lect;

		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;

		// crea la tienda
		Tienda tienda = new Tienda("C:\\Temp\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("A�adir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) { 		// WMC + 1 CCog +1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { 	//CCog +2
			case NUEVA_VENTA: 		// WMC + 1
				lect = new Lectura("Datos Venta");
				lect.creaEntrada("Id Vendedor", "");
				lect.creaEntrada("Importe", "");
				lect.esperaYCierra();
				dni = lect.leeString("Id Vendedor");
				double importe = lect.leeDouble("Importe");
				try {
					if (!tienda.anhadeVenta(dni, importe)) { 		// WMC + 1 CCog +3
						mensaje("ERROR", "El vendedor no existe");
					}
				} catch (IOException e) { 		// WMC + 1 CCog +1
					mensaje("ERROR", "No se pudo guardar el cambio");
				}
				break;

			case VENDEDOR_DEL_MES: 		// WMC + 1

				vendedores = tienda.vendedores();
				resultado = new LinkedList<Vendedor>();
				double maxVentas = 0.0;
				for (Vendedor v : vendedores) { 		// WMC + 1 CCog +2
					if (v.getTotalVentas() > maxVentas) { 		// WMC + 1 CCog +3
						maxVentas = v.getTotalVentas();
						resultado.clear();
						resultado.add(v);
					} else if (v.getTotalVentas() == maxVentas) { 		// WMC + 1 CCog +3
						resultado.add(v);
					}
				}

				msj = "";
				for (Vendedor vn : resultado) { 		// WMC + 1 CCog +2
					msj += vn.getNombre() + "\n";
				}
				mensaje("VENDEDORES DEL MES", msj);
				break;


			case VENDEDORES: 		// WMC + 1

				vendedores = tienda.vendedores();
				System.out.println(vendedores.size());
				Collections.sort(vendedores, new ComparadorVendedorVentas());			
				msj = "";
				for (Vendedor vn : vendedores) { 		// WMC + 1 CCog +2
					msj += vn.getNombre() + " " + vn.getTotalVentas() + "\n";
				}
				mensaje("VENDEDORES", msj);
				break;
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { 		// WMC + 1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

	public static class ComparadorVendedorVentas implements Comparator<Vendedor>  { 		

		public int compare(Vendedor o1, Vendedor o2) { 		// WMC + 1
			if (o1.getTotalVentas()>o2.getTotalVentas()) 		// WMC + 1 CCog +1
				return -1;
			else if (o1.getTotalVentas()<o2.getTotalVentas()) 		// WMC + 1 CCog +1
				return 1;
			return 0;
		}

	}


}
