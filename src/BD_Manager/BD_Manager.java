package BD_Manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;

import Modelo.Modelo;
import Modelo.Personajes;
import Modelo.Videojuego;
import Interface.Intercambio;
import Vistas.Inicio;
import Controlador.Controlador;

public class BD_Manager implements Intercambio {
	private String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private String archivo_personajes = "src/Modelo/personajes.txt";
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> ListaPersonajes = new HashMap<Integer, Personajes>();


	@Override
	public HashMap<Integer, Videojuego> EscribirTodos() {
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();

		try {

			BufferedReader br = new BufferedReader(new FileReader(archivo_videojuegos));

			String linea;
			while ((linea = br.readLine()) != null) {
				String idtxt = linea.substring(4);
				int id = Integer.parseInt(idtxt);
				String nametxt = br.readLine().substring(8);
				String fechatxt = br.readLine().substring(22);
				String desarrolladortxt = br.readLine().substring(15);
				String plataformatxt = br.readLine().substring(12);

				Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);
				ListaVideojuegos.put(id, mVideojuego);
			}
			PreparedStatement pstm;

			String delrel = "call eliminar_key()";
			pstm = mModelo.conexion.prepareStatement(delrel);
			int rset = pstm.executeUpdate();
			String deltabla1 = "DELETE FROM `videojuegos`";
			pstm = mModelo.conexion.prepareStatement(deltabla1);
			rset = pstm.executeUpdate();

			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
				String cargar = "INSERT INTO `videojuegos`(`ID`, `Nombre`, `Fecha_Lanzamiento`, `Desarrollador`, `Plataforma`) VALUES ("
						+ entry.getKey() + "," + "'" + entry.getValue().getNombre() + "'" + "," + "'"
						+ entry.getValue().getFecha_Lanzamiento() + "'" + "," + "'"
						+ entry.getValue().getDesarrollador() + "'" + "," + "'" + entry.getValue().getPlataforma() + "'"
						+ ")";

				pstm = mModelo.conexion.prepareStatement(cargar);
				rset = pstm.executeUpdate();
			}

			br.close();

			EscribirTodosPer();

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mControlador.Cargar_Inicio();

		return ListaVideojuegos;

	}

	@Override
	public HashMap<Integer, Personajes> EscribirTodosPer() {
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();

		try {

			BufferedReader brf = new BufferedReader(new FileReader(archivo_personajes));
			Personajes mp = new Personajes();
			String linea2;
			while ((linea2 = brf.readLine()) != null) {
				String idtxt = linea2.substring(4);
				int id = Integer.parseInt(idtxt);
				String nombre_Personaje = brf.readLine().substring(8);
				String id_Juegotxt = brf.readLine().substring(10);
				int id_Juego = Integer.parseInt(id_Juegotxt);
				

				Personajes mPersonaje = new Personajes(nombre_Personaje, id_Juego);
				ListaPersonajes.put(id, mPersonaje);
			}
			PreparedStatement pstm1;

			String deltabla2 = "DELETE FROM `personajes`;";
			pstm1 = mModelo.conexion.prepareStatement(deltabla2);
			int rset1 = pstm1.executeUpdate();

			for (Entry<Integer, Personajes> entry1 : ListaPersonajes.entrySet()) {
				String cargar2 = "INSERT INTO `personajes`(`ID`, `Nombre_Personaje`, `ID_Juego`) VALUES ("
						+ entry1.getKey() + "," + "'" + entry1.getValue().getNombre_Personaje() + "'" + "," + "'"
						+ entry1.getValue().getjuego() + "'" + ")";

				pstm1 = mModelo.conexion.prepareStatement(cargar2);
				rset1 = pstm1.executeUpdate();

			}
			String addrel1 = "call add_key()";
			pstm1 = mModelo.conexion.prepareStatement(addrel1);
			rset1 = pstm1.executeUpdate();

			brf.close();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mControlador.Cargar_Inicio();

		return ListaPersonajes;

	}

	@Override
	public HashMap<Integer, Videojuego> Añadir() {
		Modelo mModelo = new Modelo();
		try {
			Controlador mControlador = new Controlador();
			PreparedStatement pstm;

		mControlador.PedirDatosBD(ListaVideojuegos);

			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
				String cargar = "INSERT INTO `videojuegos`(`ID`, `Nombre`, `Fecha_Lanzamiento`, `Desarrollador`, `Plataforma`) VALUES ("
						+ entry.getKey() + "," + "'" + entry.getValue().getNombre() + "'" + "," + "'"
						+ entry.getValue().getFecha_Lanzamiento() + "'" + "," + "'"
						+ entry.getValue().getDesarrollador() + "'" + "," + "'" + entry.getValue().getPlataforma() + "'"
						+ ")";
				pstm = mModelo.conexion.prepareStatement(cargar);
				int rset = pstm.executeUpdate();

			}
			mControlador.Cargar_Inicio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;

	}

	@Override
	public HashMap<Integer, Personajes> AñadirPer() {
		Modelo mModelo = new Modelo();
		try {
			Controlador mControlador = new Controlador();
			PreparedStatement pstm;

			mControlador.PedirDatosPerDB(ListaPersonajes);

			for (Entry<Integer, Personajes> entry : ListaPersonajes.entrySet()) {
				String cargar = "INSERT INTO `personajes`(`ID`, `Nombre_Personaje`, `ID_Juego`) VALUES ("
						+ entry.getKey() + "," + "'" + entry.getValue().getNombre_Personaje() + "'" + "," + "'"
						+ entry.getValue().getjuego() + "'" + ")";
				pstm = mModelo.conexion.prepareStatement(cargar);
				int rset = pstm.executeUpdate();
			}
			mControlador.Cargar_Inicio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaPersonajes;
	}

	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		String cargar = "Select * from videojuegos";
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("ID");
				String Nombre = rset.getString("Nombre");
				String Fecha = rset.getString("Fecha_Lanzamiento");
				String Desarrollador = rset.getString("Desarrollador");
				String Plataforma = rset.getString("Plataforma");
				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(id, mVideojuego);
			}
			mControlador.MostrarDatos(ListaVideojuegos);
			mControlador.Cargar_Inicio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;
	}

	public HashMap<Integer, Videojuego> LeerTodosAux() {
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		String cargar = "Select * from videojuegos";
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("ID");
				String Nombre = rset.getString("Nombre");
				String Fecha = rset.getString("Fecha_Lanzamiento");
				String Desarrollador = rset.getString("Desarrollador");
				String Plataforma = rset.getString("Plataforma");
				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(id, mVideojuego);
			}
		mControlador.MostrarDatos(ListaVideojuegos);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;
	}

	public HashMap<Integer, Personajes> LeerTodosPer() {
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		String cargar = "Select * from personajes";
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				int id1 = rset.getInt(1);
				String Nombre = rset.getString("Nombre_Personaje");
				int id_juego = rset.getInt("ID_Juego");
				Personajes mPersonaje = new Personajes(Nombre, id_juego);

				ListaPersonajes.put(id1, mPersonaje);
			}
			
			mControlador.MostrarDatosPer(ListaPersonajes);
		
			mControlador.Cargar_Inicio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaPersonajes;
	}

}
