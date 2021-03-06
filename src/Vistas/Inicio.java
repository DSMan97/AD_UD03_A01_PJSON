package Vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import BD_Manager.BD_Manager;

import java.util.Map.Entry;

import org.bson.Document;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.MckoiCaseFragment;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mysql.jdbc.util.ResultSetUtil;

import Controlador.Controlador;
import HibernateManager.HibernateManager;
import Interface.Intercambio;
import Main.Main;
import Modelo.Modelo;
import Modelo.Personajes;
import Modelo.Videojuego;
import MongoManager.AccesoMongo;

public class Inicio {
	int id = 0;
	private String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private String archivo_personajes = "src/Modelo/personajes.txt";

	public void CargarMenuPrincipal() throws SQLException, IOException {
		String menu = "�En que modo quieres trabajar?\n" + "1:Normal\n" + "2:Hibernate\n" + "3:JSON\n" + "4:MongoDB";
		System.out.println(menu);
		Scanner opt = new Scanner(System.in);
		System.out.print("Elija una opcion:  ");
		int option = opt.nextInt();
		switch (option) {
		case 1:
			CargarMenu();
			break;
		case 2:
			CargarMenuHibernate();
			break;
		case 3:
			CargarMenuJSON();
			break;
		case 4:
			CargarMenuMongo();
			break;

		default:
			System.err.println("Dato mal introducido");
			CargarMenuPrincipal();
			break;
		}
	}

	public void CargarMenu() throws SQLException, IOException {
		Controlador mControlador = new Controlador();

		// TODO Auto-generated method stub
		System.out.println("1: Crear fichero");
		System.out.println("2: Leer fichero");
		System.out.println("3: Copiar BBDD a fichero");
		System.out.println("4: Copiar fichero a BBDD");
		System.out.println("5: Leer BBDD");
		System.out.println("6: A�adir BBDD");

		Scanner opt = new Scanner(System.in);
		System.out.print("Elija una opcion:  ");
		int eleccion = opt.nextInt();
		switch (eleccion) {
		case 1:
			System.out.println("   1: Crear Fichero Videojuegos");
			System.out.println("   2: Crear Fichero Personajes");
			Scanner opt1 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion1 = opt1.nextInt();
			switch (eleccion1) {
			case 1:

				mControlador.Escribir_Fichero();
				break;
			case 2:
				mControlador.Escribir_FicheroPer();
			default:
				break;
			}

			break;
		case 2:
			System.out.println("   1: Leer Videojuegos");
			System.out.println("   2: Leer Personajes");
			Scanner opt2 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion2 = opt2.nextInt();
			switch (eleccion2) {
			case 1:
				mControlador.Leer_Fichero();
				break;
			case 2:
				mControlador.Leer_FicheroPer();
			default:
				break;
			}

			break;
		case 3:
			System.out.println("  1: Pasar tabla Videojuegos a Fichero");
			System.out.println("  2: Pasar tabla Personajes a Fichero");
			Scanner opt3 = new Scanner(System.in);
			System.out.print("    Elija una opcion:  ");
			int eleccion3 = opt3.nextInt();
			switch (eleccion3) {
			case 1:
				mControlador.BBDD2TXTHB();
				break;
			case 2:
				mControlador.BBDD2TXTPer();
			default:
				break;

			}
			break;

		case 4:
			mControlador.TXT2BBDD();

			break;
		case 5:
			System.out.println("  1: Leer Tabla Videojuegos");
			System.out.println("  2: Leer Tabla Personajes");
			Scanner opt5 = new Scanner(System.in);
			System.out.print("    Elija una opcion:  ");
			int eleccion5 = opt5.nextInt();
			switch (eleccion5) {
			case 1:
				mControlador.ImprimirDatos();
				break;
			case 2:
				mControlador.ImprimirDatosPer();
			default:
				break;
			}

			break;
		case 6:
			System.out.println("   1: Insertar un Videojuego");
			System.out.println("   2: Insertar un Personajes");
			Scanner opt6 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion6 = opt6.nextInt();
			switch (eleccion6) {
			case 1:
				mControlador.InsertarBBDD();
				break;
			case 2:
				mControlador.InsertarBBDDPer();
			default:
				break;
			}

			break;
		default:
			System.out.println("Dato mal introducido");
			break;
		}
	}

	public void sacarPantalla(HashMap<Integer, Videojuego> ListaVideojuegos) {
		for (Entry<Integer, Videojuego> videojuego : ListaVideojuegos.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre());
			System.out.println("Plataforma: " + videojuego.getValue().getPlataforma());
			System.out.println("Fecha de Lanzamiento: " + videojuego.getValue().getFecha_Lanzamiento());
			System.out.println("Desarrollador: " + videojuego.getValue().getDesarrollador() + "\n");
		}
	}

	public void sacarPantallaPer(HashMap<Integer, Personajes> listaPersonajes) {
		for (Entry<Integer, Personajes> videojuego : listaPersonajes.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre_Personaje());
			System.out.println("ID_Juego: " + videojuego.getValue().getjuego() + "\n");
		}
	}

	public void PedirDatosF(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Controlador mControlador = new Controlador();
		try {

			BufferedReader br = new BufferedReader(new FileReader(archivo_videojuegos));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Videojuego: ");
			String idtxt = scanner.nextLine();
			id = Integer.parseInt(idtxt);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (idtxt.equals(linea.substring(4))) {

					System.err.print("Este ID ya existe, por favor introduzca otro\n");

					mControlador.Escribir_Fichero();
				}

			}
			System.out.println("Nombre del Videojuego: ");
			String nametxt = scanner.nextLine();
			System.out.println("Plataforma (DS, GBA, N64): ");
			String plataformatxt = scanner.nextLine();
			System.out.println("Fecha de Lanzamiento: ");
			String fechatxt = scanner.nextLine();
			System.out.println("Desarrollador: ");
			String desarrolladortxt = scanner.nextLine();
			Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);

			ListaVideojuegos.put(id, mVideojuego);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void PedirDatosDB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Controlador mControlador = new Controlador();
		Modelo mModelo = new Modelo();
		PreparedStatement pstm;
		String cargar = "Select * from videojuegos";
		ResultSet rset;

		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			rset = pstm.executeQuery();
			BufferedReader br = new BufferedReader(new FileReader(archivo_videojuegos));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Videojuego: ");
			String idtxt = scanner.nextLine();
			id = Integer.parseInt(idtxt);

			while (rset.next()) {
				if (id == (rset.getInt(1))) {

					System.err.println("Este ID ya existe, por favor introduzca otro\n");

					mControlador.InsertarBBDD();
				}

			}
			System.out.println("Nombre del Videojuego: ");
			String nametxt = scanner.nextLine();
			System.out.println("Plataforma (DS, GBA, N64): ");
			String plataformatxt = scanner.nextLine();
			System.out.println("Fecha de Lanzamiento: ");
			String fechatxt = scanner.nextLine();
			System.out.println("Desarrollador: ");
			String desarrolladortxt = scanner.nextLine();
			Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);

			ListaVideojuegos.put(id, mVideojuego);

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void PedirDatoPerF(HashMap<Integer, Personajes> listaPersonajes) {
		// TODO Auto-generated method stub
		Controlador mControlador = new Controlador();

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo_personajes));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Personaje: ");
			String idtxt = scanner.nextLine();
			int idper = Integer.parseInt(idtxt);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (idtxt.equals(linea.substring(4))) {
					System.err.println("Este ID ya existe, por favor introduzca otro\n");

					mControlador.Escribir_FicheroPer();
				}

			}
			System.out.println("Nombre del Personaje: ");
			String namePtxt = scanner.nextLine();

			System.out.println("ID_Juego: ");
			String id_juegotxt = scanner.nextLine();
			int id_juego = Integer.parseInt(id_juegotxt);

			Personajes mPersonaje = new Personajes(namePtxt, id_juego);

			listaPersonajes.put(idper, mPersonaje);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void PedirDatoPerDB(HashMap<Integer, Personajes> listaPersonajes) {
		// TODO Auto-generated method stub
		BD_Manager mBD = new BD_Manager();
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		PreparedStatement pstm, pstm1;
		String cargar = "Select * from personajes";
		String cargar1 = "Select * from videojuegos";
		ResultSet rsetper, rset;
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			pstm1 = mModelo.conexion.prepareStatement(cargar1);
			rsetper = pstm.executeQuery();
			rset = pstm1.executeQuery();

			if (rset.next()) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("ID_Personaje: ");
				String idtxt = scanner.nextLine();
				int idper = Integer.parseInt(idtxt);

				while (rsetper.next()) {
					if (idper == (rsetper.getInt(1))) {
						System.err.println("Este ID ya existe, por favor introduzca otro\n");
						idper = 0;
						mControlador.InsertarBBDDPer();
						;
					}

				}
				System.out.println("Nombre del Personaje: ");
				String namePtxt = scanner.nextLine();

				mControlador.ImprimirVideojuegos();
				System.out.println("----Elige el juego al que pertenece este Personaje");
				Scanner scanner1 = new Scanner(System.in);
				System.out.println("ID_Juego: ");
				String idj = scanner1.nextLine();
				int id = Integer.parseInt(idj);
				Personajes mPersonaje = new Personajes(namePtxt, id);

				listaPersonajes.put(idper, mPersonaje);

			} else {
				System.out
						.print("�����No hay ning�n juego a�adido por favor a�ade al menos uno!!!!!\n");
				System.out.print("�Quieres a�adirlo ahora?\n");
				System.out.print("   1: SI\n");
				System.out.print("   2: NO\n");
				Scanner opt6 = new Scanner(System.in);
				System.out.print("     Elija una opci�n:  \n");
				int eleccion4 = opt6.nextInt();
				switch (eleccion4) {
				case 1:
					mControlador.InsertarBBDD();
					break;
				case 2:

					break;
				default:
					break;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	/*
	 * -----------------------------------------------------------------------------
	 * -
	 * 
	 * 
	 * 
	 * 
	 * A PARTIR DE AQU� MEN� HIBERNATE
	 * 
	 * 
	 * 
	 * -----------------------------------------------------------------------------
	 */

	public void CargarMenuHibernate() throws SQLException, IOException {

		Controlador mControlador = new Controlador();

		// TODO Auto-generated method stub
		System.out.println("1: Copiar BBDD a fichero");
		System.out.println("2: Copiar fichero a BBDD");
		System.out.println("3: Leer BBDD");
		System.out.println("4: Anadir BBDD");
		System.out.println("5: Eliminar BBDD");

		Scanner opt = new Scanner(System.in);
		System.out.print("Elija una opcion:  ");
		int eleccion = opt.nextInt();
		switch (eleccion) {
		case 1:

			System.out.println("  1: Pasar tabla Videojuegos a Fichero");
			System.out.println("  2: Pasar tabla Personajes a Fichero");
			Scanner opt1 = new Scanner(System.in);
			System.out.print("    Elija una opcion:  ");
			int eleccion1 = opt1.nextInt();
			switch (eleccion1) {
			case 1:
				mControlador.BBDD2TXTHB();
				break;
			case 2:

				mControlador.BBDD2TXTPerHB();
				break;
			default:
				break;

			}
			break;

		case 2:

			mControlador.TXT2BBDDHB();

			break;
		case 3:
			System.out.println("  1: Leer Tabla Videojuegos");
			System.out.println("  2: Leer Tabla Personajes");
			Scanner opt3 = new Scanner(System.in);
			System.out.print("    Elija una opcion:  ");
			int eleccion3 = opt3.nextInt();
			switch (eleccion3) {
			case 1:
				mControlador.ImprimirDatosHB();
				break;
			case 2:

				mControlador.ImprimirDatosPerHB();
			default:
				break;
			}

			break;
		case 4:
			System.out.println("   1: Insertar un Videojuego");
			System.out.println("   2: Insertar un Personajes");
			Scanner opt4 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion4 = opt4.nextInt();
			switch (eleccion4) {
			case 1:

				mControlador.InsertarHB();
				break;
			case 2:

				mControlador.InsertarPerHB();
			default:
				break;
			}

			break;
		case 5:
			System.out.println("   1: Eliminar un Videojuego");
			System.out.println("   2: Eliminar un Personaje");
			Scanner opt5 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion5 = opt5.nextInt();
			switch (eleccion5) {
			case 1:

				mControlador.EliminarHB();
				break;
			case 2:

				mControlador.EliminarPerHB();
			default:
				break;
			}
		default:
			System.out.println("Dato mal introducido");
			break;
		}
	}

	public void sacarPantallaHB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		for (Entry<Integer, Videojuego> videojuego : ListaVideojuegos.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre());
			System.out.println("Plataforma: " + videojuego.getValue().getPlataforma());
			System.out.println("Fecha de Lanzamiento: " + videojuego.getValue().getFecha_Lanzamiento());
			System.out.println("Desarrollador: " + videojuego.getValue().getDesarrollador());
		}
	}

	public void sacarPantallaPerHB(HashMap<Integer, Personajes> listaPersonajes) {
		for (Entry<Integer, Personajes> videojuego : listaPersonajes.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre_Personaje());
			System.out.println("ID_Juego: " + videojuego.getValue().getjuego());
		}
	}

	public void PedirDatosHB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Controlador mControlador = new Controlador();
		HibernateManager mHM = new HibernateManager();
		Query q = mHM.s.createQuery("Select v from videojuegos v");
		List resultado = q.list();
		Iterator videojuegositerador = resultado.iterator();
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID_Videojuego: ");
		String idtxt = scanner.nextLine();
		id = Integer.parseInt(idtxt);
		while (videojuegositerador.hasNext()) {
			Videojuego vdo = (Videojuego) videojuegositerador.next();

			if (id == (vdo.getID())) {

				System.err.println("Este ID ya existe, por favor introduzca otro\n");

				mControlador.InsertarHB();
			}
			vdo.setID(id);

		}
		System.out.println("Nombre del Videojuego: ");
		String nametxt = scanner.nextLine();
		System.out.println("Fecha de Lanzamiento: ");
		String fechatxt = scanner.nextLine();
		System.out.println("Plataforma (DS, GBA, N64): ");
		String plataformatxt = scanner.nextLine();
		System.out.println("Desarrollador: ");
		String desarrolladortxt = scanner.nextLine();
		Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, plataformatxt, desarrolladortxt);
		ListaVideojuegos.put(id, mVideojuego);
	}

	public void PedirDatosPerHB(HashMap<Integer, Personajes> listaPersonajes) {
		Controlador mControlador = new Controlador();
		HibernateManager mHM = new HibernateManager();
		Query qper = mHM.s.createQuery("Select p from personajes p");
		List resultadoper = qper.list();
		Iterator personajesiterador = resultadoper.iterator();
		Query qvdo = mHM.s.createQuery("Select v from videojuegos v");
		List resultadovdo = qvdo.list();
		Iterator videojuegoiterador = resultadovdo.iterator();
		if (videojuegoiterador.hasNext()) {

			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Personaje: ");
			String idtxt = scanner.nextLine();
			int idper = Integer.parseInt(idtxt);

			while (personajesiterador.hasNext()) {
				Personajes per = (Personajes) personajesiterador.next();

				if (idper == (per.getID_per())) {
					System.err.println("Este ID ya existe, por favor introduzca otro\n");
					idper = 0;
					mControlador.InsertarPerHB();

				}

				per.setjuego(idper);
			}
			System.out.println("Nombre del Personaje: ");
			String namePtxt = scanner.nextLine();

			mControlador.ImprimirVideojuegosHB();
			System.out.println("----Elige el juego al que pertenece este Personaje");
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("ID_Juego: ");
			String idj = scanner1.nextLine();
			int id = Integer.parseInt(idj);
			Personajes mPersonaje = new Personajes(namePtxt, id);

			listaPersonajes.put(idper, mPersonaje);

		} else {
			System.out.print("!!!!!!No hay ningun juego a�adido por favor a�ade al menos uno!!!!!\n");
			System.out.print("Quieres a�adirlo ahora?\n");
			System.out.print("   1: SI\n");
			System.out.print("   2: NO\n");
			Scanner opt6 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  \n");
			int eleccion4 = opt6.nextInt();
			switch (eleccion4) {
			case 1:
				mControlador.InsertarHB();
				break;
			case 2:

				break;
			default:
				break;
			}

		}

	}

	public void EliminarDatosHB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Scanner scanner = new Scanner(System.in);
		Controlador mControlador = new Controlador();
		mControlador.ImprimirVideojuegosHB();
		System.out.println("Nombre del Videojuego que quiere borrar (se borraran sus personajes): ");
		String nametxt = scanner.nextLine();
		String Fecha = "";
		int id = 0;
		String Desarrollador = "";
		String Plataforma = "";
		Videojuego mVideojuego = new Videojuego(nametxt, Fecha, Desarrollador, Plataforma);
		ListaVideojuegos.put(id, mVideojuego);

	}

	public void EliminarDatosPerHB(HashMap<Integer, Personajes> listaPersonajes) {
		Controlador mControlador = new Controlador();
		mControlador.ImprimirPersonajesHB();
		System.out.println("Nombre del personaje que desea borrar: ");
		Scanner scanner = new Scanner(System.in);
		String nametxt = scanner.nextLine();

		int id = 0;

		Personajes mPersonaje = new Personajes(nametxt, id);
		listaPersonajes.put(id, mPersonaje);

	}

	public void CargarMenuJSON() {
		Controlador mControlador = new Controlador();

		// TODO Auto-generated method stub
		System.out.println("1: Copiar BBDD a fichero");
		System.out.println("2: Copiar fichero a BBDD");
		System.out.println("3: Leer BBDD");
		System.out.println("4: Anadir BBDD");
		System.out.println("5: Eliminar BBDD");

		Scanner opt = new Scanner(System.in);
		System.out.print("Elija una opci�n:  ");
		int eleccion = opt.nextInt();
		switch (eleccion) {
		case 1:

			System.out.println("  1: Pasar tabla Videojuegos a Fichero");
			System.out.println("  2: Pasar tabla Personajes a Fichero");
			Scanner opt1 = new Scanner(System.in);
			System.out.print("    Elija una opcion:  ");
			int eleccion1 = opt1.nextInt();
			switch (eleccion1) {
			case 1:
				mControlador.BBDD2TXTHB();
				break;
			case 2:

				mControlador.BBDD2TXTPerHB();
				break;
			default:
				break;

			}
			break;

		case 2:

			mControlador.TXT2BBDDHB();

			break;
		case 3:
			System.out.println("  1: Leer Tabla Videojuegos");
			System.out.println("  2: Leer Tabla Personajes");
			Scanner opt3 = new Scanner(System.in);
			System.out.print("    Elija una opcion:  ");
			int eleccion3 = opt3.nextInt();
			switch (eleccion3) {
			case 1:
				mControlador.leerVideojuegos_JSON();
				break;
			case 2:
				mControlador.leerPersonajes_JSON();
			default:
				break;
			}

			break;
		case 4:
			System.out.println("   1: Insertar un Videojuego");
			System.out.println("   2: Insertar un Personajes");
			Scanner opt4 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion4 = opt4.nextInt();
			switch (eleccion4) {
			case 1:

				mControlador.annadirVideojuegos_JSON();
				break;
			case 2:

				mControlador.annadirPersonajes_JSON();
			default:
				break;
			}

			break;
		case 5:
			System.out.println("   1: Eliminar un Videojuego");
			System.out.println("   2: Eliminar un Personaje");
			Scanner opt5 = new Scanner(System.in);
			System.out.print("     Elija una opcion:  ");
			int eleccion5 = opt5.nextInt();
			switch (eleccion5) {
			case 1:

				mControlador.EliminarHB();
				break;
			case 2:

				mControlador.EliminarPerHB();
			default:
				break;
			}
		default:
			System.out.println("Dato mal introducido");
			break;
		}

	}

	private void CargarMenuMongo() {
		Controlador mControlador = new Controlador();
		// En esta linea definimos la posibilidad de elegir las distintas opciones
		Scanner eleccion = new Scanner(System.in);
		System.out.println("Que quieres hacer?" + "\n");
		System.out.println("1: Leer tabla videojuegos");
		System.out.println("2: Leer tabla personaje");
		System.out.println("3: Insertar videojuego");
		System.out.println("4: Insertar personaje");
		System.out.println("5. Borrar videojuego");
		System.out.println("6. Borrar personaje");
		System.out.println("7. Actualizar videojuego");
		System.out.println("8. Actualizar personaje");
		System.out.println("9. BBDD -> TXT");
		System.out.println("10. TXT -> BBDD");
		// Declaraci�n del switch
		int opciones = eleccion.nextInt();
		switch (opciones) {
		case 1:
			mControlador.imprimirVideojuegos();
			break;
		case 2:
			mControlador.imprimirPersonajes();
			break;
		case 3:
			mControlador.anadirVideojuegoMongo();
			break;
		case 4:
			mControlador.anadirPersonajeMongo();
			break;
		case 5:
			mControlador.eliminarVideojuegoMongo();
			break;
		case 6:
			mControlador.eliminarPersonajeMongo();
			break;
		case 7:
			mControlador.modificarVideojuegoMongo();
			break;
		case 8:
			mControlador.modificarPersonajeMongo();
			break;
		case 9:
			/**
			 * Redefinir
			 */
			mControlador.modificarVideojuegoMongo();
			break;
		case 10:
			/**
			 * Redefinir
			 */
			mControlador.modificarPersonajeMongo();
			break;
		default:
			System.out.println("Dato mal introducido");
			break;
		}

	}

	public void PedirDatosMongoDB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		// TODO Coger Datos de la linea 58 de AccesoMongo.java
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();
		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		documento.put("Nombre", nombre);
		System.out.println("Fecha_Lanzamiento:");
		String fecha = sc.nextLine();
		documento.put("Fecha_Lanzamiento", fecha);
		System.out.println("Desarrollador:");
		String desarrollador = sc.nextLine();
		documento.put("Desarrollador", desarrollador);
		System.out.println("Plataforma:");
		String plataforma = sc.nextLine();
		documento.put("Plataforma", plataforma);
		Videojuego mVideojuego = new Videojuego(documento.getString("Nombre"), documento.getString("Fecha_Lanzamiento"),
				documento.getString("Desarrollador"), documento.getString("Plataforma"));
		ListaVideojuegos.put(id, mVideojuego);
	}

	public void PedirDatosJSONPER(HashMap<Integer, Personajes> ListaPersonajes) {
		// TODO Coger Datos de la linea 58 de AccesoMongo.java
		Scanner sc = new Scanner(System.in);
	

		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		

		System.out.println("Nombre del personaje:");
		String nombreP = sc.nextLine();
		

		System.out.println("ID del juego:");
		String id_juegotxt = sc.nextLine();
		int id_juego = Integer.parseInt(id_juegotxt);
	

		Personajes mPersonaje = new Personajes(nombreP,id_juego);
		ListaPersonajes.put(id, mPersonaje);
	}
	
	public void PedirDatosJSON(HashMap<Integer, Videojuego> ListaVideojuegos) {
		// TODO Coger Datos de la linea 58 de AccesoMongo.java
		Scanner sc = new Scanner(System.in);
	
		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
	
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		
		System.out.println("Fecha_Lanzamiento:");
		String fecha = sc.nextLine();
	
		System.out.println("Desarrollador:");
		String desarrollador = sc.nextLine();
	
		System.out.println("Plataforma:");
		String plataforma = sc.nextLine();


		Videojuego mVideojuego = new Videojuego(nombre,fecha,desarrollador,plataforma);
		ListaVideojuegos.put(id, mVideojuego);
	}
	
	
	public void PedirDatosMongoPER(HashMap<Integer, Personajes> ListaPersonajes) {
		// TODO Coger Datos de la linea 58 de AccesoMongo.java
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();

		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);

		System.out.println("Nombre del personaje:");
		String nombreP = sc.nextLine();
		documento.put("Nombre_Personaje", nombreP);

		System.out.println("ID del juego:");
		String id_juegotxt = sc.nextLine();
		int id_juego = Integer.parseInt(id_juegotxt);
		documento.put("ID_Juego", id_juego);

		Personajes mPersonaje = new Personajes(documento.getString("Nombre_Personaje"),
				documento.getInteger("ID_Juego"));
		ListaPersonajes.put(id, mPersonaje);
	}

	public void sacarPantallaPERMongo(HashMap<Integer, Personajes> ListaPersonajes) {
		for (Entry<Integer, Personajes> personajes : ListaPersonajes.entrySet()) {
			System.out.println("ID: " + personajes.getKey().toString());
			System.out.println("Nombre del Personaje: " + personajes.getValue().getNombre_Personaje());
			System.out.println("Id del Juego: " + personajes.getValue().getjuego());
		}
	}

	public void sacarPantallaMongo(HashMap<Integer, Videojuego> ListaVideojuegos) {
		for (Entry<Integer, Videojuego> videojuego : ListaVideojuegos.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre());
			System.out.println("Plataforma: " + videojuego.getValue().getPlataforma());
			System.out.println("Fecha de Lanzamiento: " + videojuego.getValue().getFecha_Lanzamiento());
			System.out.println("Desarrollador: " + videojuego.getValue().getDesarrollador());
		}

	}

	public void PedirDatosUpdateMongoDB(HashMap<Integer, Videojuego> listaVideojuegos) {

		Scanner sc = new Scanner(System.in);
		Document documento = new Document();
		System.out.println("Seleccione el ID del cocumento:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		documento.put("Nombre", nombre);
		System.out.println("Fecha_Lanzamiento:");
		String fecha = sc.nextLine();
		documento.put("Fecha_Lanzamiento", fecha);
		System.out.println("Desarrollador:");
		String desarrollador = sc.nextLine();
		documento.put("Desarrollador", desarrollador);
		System.out.println("Plataforma:");
		String plataforma = sc.nextLine();
		documento.put("Plataforma", plataforma);
		Videojuego mVideojuego = new Videojuego(documento.getString("Nombre"), documento.getString("Fecha_Lanzamiento"),
				documento.getString("Desarrollador"), documento.getString("Plataforma"));
		listaVideojuegos.put(id, mVideojuego);

	}

	public void PedirDatosUpdateMongoDBPer(HashMap<Integer, Personajes> ListaPersonajes) {

		Scanner sc = new Scanner(System.in);
		Document documento = new Document();
		System.out.println("Seleccione el ID del cocumento:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);

		System.out.println("Nombre del personaje:");
		String nombreP = sc.nextLine();
		documento.put("Nombre_Personaje", nombreP);

		System.out.println("ID del juego:");
		String id_juegotxt = sc.nextLine();
		int id_juego = Integer.parseInt(id_juegotxt);
		documento.put("ID_Juego", id_juego);

		Personajes mPersonaje = new Personajes(documento.getString("Nombre_Personaje"),
				documento.getInteger("ID_Juego"));
		ListaPersonajes.put(id, mPersonaje);

	}
}
