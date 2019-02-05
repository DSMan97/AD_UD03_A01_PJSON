/*
package MongoManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class AccesoMongo extends Conexion {
	MongoCollection<Document> collectionVideojuegos = db.getCollection("videojuegos");
	MongoCollection<Document> collectionPersonajes = db.getCollection("personajes");

	// Método para mostrar la tabla videojuegos
	public void leerVideojuegos() {
		FindIterable<Document> busquedaV = collectionVideojuegos.find();
		MongoCursor<Document> lectura = busquedaV.iterator();
		System.out.println("Tabla videojuegos:" + "\n");

		do {
			System.out.println(lectura.next());
		} while (lectura.hasNext());

	}

	// Método para mostrar la tabla personajes
	public void leerPersonajes() {
		FindIterable<Document> busquedaP = collectionPersonajes.find();
		MongoCursor<Document> lecturaP = busquedaP.iterator();

		do {
			System.out.println(lecturaP.next());
		} while (lecturaP.hasNext());

	}

	// Método para insertar un videojuego
	public void insertarVideojuego() {
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
		collectionVideojuegos.insertOne(documento);
		System.out.println("Datos introducidos correctamente");
	}

	// Método para insertar un personaje
	public void insertarPersonaje() {
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();
		
		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);
		
		System.out.println("Nombre_Personaje:");
		String nombre = sc.nextLine();
		documento.put("Nombre_Personaje", nombre);
		
		System.out.println("ID_Juego");
		String id_juego = sc.nextLine();
		documento.put("ID_Juego", id_juego);
		
		System.out.println(documento.toString());

		collectionPersonajes.insertOne(documento);
		System.out.println("Datos introducidos correctamente");
	}

	// Método para borrar un videojuego
	public void borrarVideojuego() {
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();
		
		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.remove("ID", id);
		
		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		documento.remove("Nombre", nombre);
		
		System.out.println("Fecha_Lanzamiento:");
		String fecha = sc.nextLine();
		documento.remove("Fecha_Lanzamiento", fecha);
		
		System.out.println("Desarrollador:");
		String desarrollador = sc.nextLine();
		documento.remove("Desarrollador", desarrollador);
		
		System.out.println("Plataforma:");
		String plataforma = sc.nextLine();
		documento.remove("Plataforma", plataforma);
		
		collectionVideojuegos.deleteOne(documento);
		System.out.println("Datos de videojuegos borrados correctamente");
	}

	// Método para borrar un personaje
	public void borrarPersonaje() {
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();
		
		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);
		
		System.out.println("Nombre_Personaje:");
		String nombre = sc.nextLine();
		documento.put("Nombre_Personaje", nombre);
		
		System.out.println("ID_Juego");
		String id_juego = sc.nextLine();
		int idJuego = Integer.parseInt(id_juego);
		documento.put("ID_Juego", idJuego);
		
		System.out.println(documento.toString());
		
		//??? siguiente prueba para hacer 
		
		collectionPersonajes.insertMany((List<? extends Document>) collectionPersonajes.deleteOne(documento));
		
		//collectionPersonajes.deleteOne(documento);
		System.out.println("Datos de personajes borrados correctamente");
	}

	// Método para actualizar un videojuego
	public void actualizarVideojuego() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ID:");
		int id = sc.nextInt();
		Document documento = new Document();
		documento.put("ID", id);
		// db.videojuegos.update({Nombre: 'hOLA'},{$set: {Nombre: 'Mario'}})
		// collectionVideojuegos.updateOne(arg0, arg1);
		System.out.println("En esta opción actualizaremos un videojuego");
	}

	// Método para actualizar un personaje
	public void actualizarPersonaje() {
		// Esta linea es solo para comprobar que el switch funciona
		System.out.println("En esta opción actualizaremos un personaje");
	}
}
*/

package MongoManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import org.bson.Document;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Controlador.Controlador;
import Interface.Intercambio;
import Modelo.Modelo;
import Modelo.Personajes;
import Modelo.Videojuego;
import Vistas.Inicio;

public class AccesoMongo extends Conexion implements Intercambio {
	MongoCollection<Document> collectionVideojuegos = db.getCollection("videojuegos");
	MongoCollection<Document> collectionPersonajes = db.getCollection("personajes");
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> ListaPersonajes = new HashMap<Integer, Personajes>();

	// Mï¿½todo para mostrar la tabla videojuegos
	public FindIterable<Document> leerVideojuegos() {
		FindIterable<Document> busquedaV = collectionVideojuegos.find();
		MongoCursor<Document> lectura = busquedaV.iterator();
		System.out.println("Tabla videojuegos:" + "\n");

		do {
			System.out.println(lectura.next());
		} while (lectura.hasNext());
		return busquedaV;
	}

	// Mï¿½todo para mostrar la tabla personajes
	public FindIterable<Document> leerPersonajes() {
		FindIterable<Document> busquedaP = collectionPersonajes.find();
		MongoCursor<Document> lecturaP = busquedaP.iterator();
		System.out.println("Tabla personajes:" + "\n");
		do {
			System.out.println(lecturaP.next());
		} while (lecturaP.hasNext());
		return busquedaP;
	}

	// Mï¿½todo para insertar un videojuego
	public void insertarVideojuego() {
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();

		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);

		System.out.println("Nombre:");
		String nombre = sc.nextLine();
		documento.put("Nombre", nombre);

		System.out.println("Fecha de lanzamiento:");
		String fecha = sc.nextLine();
		documento.put("Fecha_lanzamiento", fecha);

		System.out.println("Desarrollador:");
		String desarrollador = sc.nextLine();
		documento.put("Desarrollador", desarrollador);

		System.out.println("Plataforma:");
		String plataforma = sc.nextLine();
		documento.put("Plataforma", plataforma);

		System.out.println(documento.toString());
		collectionPersonajes.insertOne(documento);

		System.out.println("Datos videojuegos introducidos correctamente");
		sc.close();
	}

	// Mï¿½todo para insertar un personaje
	public void insertarPersonaje() {
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();

		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);

		System.out.println("Nombre_Personaje:");
		String nombre = sc.nextLine();
		documento.put("Nombre_Personaje", nombre);

		System.out.println("ID_Juego");
		String id_juego = sc.nextLine();
		int id_game = Integer.parseInt(id_juego);
		documento.put("ID_Juego", id_game);

		System.out.println(documento.toString());
		collectionPersonajes.insertOne(documento);

		System.out.println("Datos personaje introducidos correctamente");
		sc.close();
	}

	// Mï¿½todo para borrar un videojuego
	public void borrarVideojuego() {
		Scanner sc = new Scanner(System.in);
		Document documento = new Document();

		documento.remove("ID", sc.nextLine());
		documento.remove("Nombre", sc.nextLine());
		documento.remove("Fecha_Lanzamiento", sc.nextLine());
		documento.remove("Desarrollador", sc.nextLine());
		documento.remove("Plataforma", sc.nextLine());

		collectionVideojuegos.deleteOne(documento);
		System.out.println("Datos videojuegos borrados correctamente");
	}

	// Mï¿½todo para borrar un personaje
	public void borrarPersonaje() {
		Scanner sc = new Scanner(System.in);

		Document documento = new Document();

		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);

		System.out.println("Nombre_Personaje:");
		String nombre = sc.nextLine();
		documento.put("Nombre_Personaje", nombre);

		System.out.println("ID_Juego");
		String id_juego = sc.nextLine();
		int idJuego = Integer.parseInt(id_juego);
		documento.put("ID_Juego", idJuego);

		System.out.println(documento.toString());

		// ??? siguiente prueba para hacer

		collectionPersonajes.insertMany((List<? extends Document>) collectionPersonajes.deleteOne(documento));

		// collectionPersonajes.deleteOne(documento);
		System.out.println("Datos de personajes borrados correctamente");

	}

	/**
	 * // Mï¿½todo para actualizar un videojuego public void HashMap<Integer,
	 * Personajes> actualizarVideojuego() { Inicio mvista = Inicio(); if
	 * (!nombre.equals("")) { updateName(nombre, oldname); } if
	 * (!apellido1.equals("")) { updateApellido1(apellido1, oldname); } if
	 * (!apellido2.equals("")) { updateApellido2(apellido2, oldname); } if (telefono
	 * != 0) { updateTelefono(telefono, oldname); } if (!email.equals("")) {
	 * updateEmail(email, oldname); } }
	 * 
	 * private void updateName(String nombre, String oldname) { // Mï¿½todo para
	 * modificar el nombre
	 * 
	 * PreparedStatement pstm; try { for (Entry<Integer, Contacto> entry :
	 * mListaContactos.entrySet()) { String cargar = "UPDATE`contactos` SET `Nombre`
	 * =" + "'" + entry.getValue().getNombre() + "'" + " Where `Nombre`=" + "'" +
	 * oldname + "'"; pstm = mModelo.conexion.prepareStatement(cargar);
	 * pstm.executeUpdate(); } System.out.println("Has modificado correctamente a "
	 * + nombre); } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * private void updateApellido1(String apellido1, String oldname) { // Mï¿½todo
	 * para modificar el primer apellido PreparedStatement pstm; try { for
	 * (Entry<Integer, Contacto> entry : mListaContactos.entrySet()) { String cargar
	 * = "UPDATE`contactos` SET `Apellido_1` =" + "'" +
	 * entry.getValue().getApellido1() + "'" + " Where `Nombre`=" + "'" + oldname +
	 * "'"; pstm = mModelo.conexion.prepareStatement(cargar); pstm.executeUpdate();
	 * } System.out.println("Has modificado correctamente a " + oldname); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * }
	 * 
	 * private void updateApellido2(String apellido2, String oldname) { // Mï¿½todo
	 * para modificar el segundo apellido PreparedStatement pstm; try { for
	 * (Entry<Integer, Contacto> entry : mListaContactos.entrySet()) { String cargar
	 * = "UPDATE`contactos` SET `Apellido_2` =" + "'" +
	 * entry.getValue().getApellido2() + "'" + " Where `Nombre`=" + "'" + oldname +
	 * "'"; pstm = mModelo.conexion.prepareStatement(cargar); pstm.executeUpdate();
	 * } System.out.println("Has modificado correctamente a " + oldname); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * }
	 * 
	 * private void updateTelefono(int telefono, String oldname) { // Mï¿½todo para
	 * modificar el telefono
	 * 
	 * PreparedStatement pstm; try { for (Entry<Integer, Contacto> entry :
	 * mListaContactos.entrySet()) { String cargar = "UPDATE`contactos` SET
	 * `Telefono` =" + "'" + entry.getValue().getTelefono() + "'" + " Where
	 * `Nombre`=" + "'" + oldname + "'"; pstm =
	 * mModelo.conexion.prepareStatement(cargar); pstm.executeUpdate(); }
	 * System.out.println("Has modificado correctamente a " + oldname); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * }
	 * 
	 * private void updateEmail(String email, String oldname) { // Mï¿½todo para
	 * modificar el email
	 * 
	 * 
	 * PreparedStatement pstm; try { for (Entry<Integer, Contacto> entry :
	 * mListaContactos.entrySet()) { String cargar = "UPDATE`contactos` SET `Email`
	 * =" + "'" + entry.getValue().getEmail() + "'" + " Where `Nombre`=" + "'" +
	 * oldname + "'"; pstm = mModelo.conexion.prepareStatement(cargar);
	 * pstm.executeUpdate(); } System.out.println("Has modificado correctamente a "
	 * + oldname); } catch (SQLException e) { e.printStackTrace(); } }
	 * 
	 * Document documento = new Document();
	 * 
	 * documento.put("ID", id); // db.videojuegos.update({Nombre: 'hOLA'},{$set:
	 * {Nombre: 'Mario'}}) // collectionVideojuegos.updateOne(arg0, arg1);
	 * System.out.println("En esta opciï¿½n actualizaremos un videojuego"); }
	 * 
	 * // Mï¿½todo para actualizar un personaje public void actualizarPersonaje() {
	 * // Esta linea es solo para comprobar que el switch funciona
	 * System.out.println("En esta opciï¿½n actualizaremos un personaje"); }
	 */
	@Override
	public HashMap<Integer, Videojuego> Annadir() {
		Controlador mControlador = new Controlador();
		mControlador.PedirDatosMongoBD(ListaVideojuegos);
		Document documento = new Document();
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
			documento.put("ID", entry.getKey());
			documento.put("Nombre", entry.getValue().getNombre());
			documento.put("Fecha_Lanzamiento", entry.getValue().getFecha_Lanzamiento());
			documento.put("Desarrollador", entry.getValue().getDesarrollador());
			documento.put("Plataforma", entry.getValue().getPlataforma());
		}

		collectionVideojuegos.insertOne(documento);

		System.out.println("Datos videojuegos introducidos correctamente");
		mControlador.Cargar_Inicio();

		return ListaVideojuegos;
	}
	
	@Override
	public HashMap<Integer, Personajes> AnnadirPer() {
		Controlador mControlador = new Controlador();
		mControlador.PedirDatosMongoPER(ListaPersonajes);
		Document documento = new Document();
		for (Entry<Integer, Personajes> entry : ListaPersonajes.entrySet()) {
			documento.put("ID", entry.getKey());
			documento.put("Nombre_Personaje", entry.getValue().getNombre_Personaje());
			documento.put("ID_Juego", entry.getValue().getjuego());
		}
		
		collectionPersonajes.insertOne(documento);
		

		System.out.println("Datos personajes introducidos correctamente");
		mControlador.Cargar_Inicio();
		
		return ListaPersonajes;
	}
	
	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		Controlador mControlador = new Controlador();
		//necesito mostrar datos 
		mControlador.MostrarDatos(ListaVideojuegos);
		
		
		return ListaVideojuegos;
	}

	@Override
	public HashMap<Integer, Personajes> EscribirTodosPer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Personajes> LeerTodosPer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Videojuego> EscribirTodos() {
		// TODO Auto-generated method stub
		return null;

	}
}