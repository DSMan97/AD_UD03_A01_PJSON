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

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import Controlador.Controlador;
import Interface.Intercambio;
import Modelo.Personajes;
import Modelo.Videojuego;

public class AccesoMongo extends Conexion implements Intercambio {
	MongoCollection<Document> collectionVideojuegos = db.getCollection("videojuegos");
	MongoCollection<Document> collectionPersonajes = db.getCollection("personajes");
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> ListaPersonajes = new HashMap<Integer, Personajes>();

	







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
	 * documento.put("ID", id); //
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
			String idtxt = Integer.toString(entry.getKey());
			documento.put("ID", idtxt);
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
			String idtxt = Integer.toString(entry.getKey());
			documento.put("ID", idtxt);
			documento.put("Nombre_Personaje", entry.getValue().getNombre_Personaje());
			String idjuegotxt = Integer.toString(entry.getValue().getjuego());
			documento.put("ID_Juego", idjuegotxt );
		}

		collectionPersonajes.insertOne(documento);

		System.out.println("Datos personajes introducidos correctamente");
		mControlador.Cargar_Inicio();

		return ListaPersonajes;
	}

	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		Controlador mControlador = new Controlador();
		FindIterable<Document> busquedaV = collectionVideojuegos.find();
		MongoCursor<Document> lectura = busquedaV.iterator();
		System.out.println("Tabla videojuegos:" + "\n");

		while (lectura.hasNext()) {
			System.out.println(lectura.next());
		}

		mControlador.Cargar_Inicio();
		return ListaVideojuegos;

	}

	@Override
	public HashMap<Integer, Personajes> EscribirTodosPer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Personajes> LeerTodosPer() {
		Controlador mControlador = new Controlador();
		FindIterable<Document> busquedaP = collectionPersonajes.find();
		MongoCursor<Document> lecturaP = busquedaP.iterator();
		System.out.println("Tabla personajes:" + "\n");

		while (lecturaP.hasNext()) {
			System.out.println(lecturaP.next());
		}

		mControlador.Cargar_Inicio();
		return ListaPersonajes;
	}

	@Override
	public HashMap<Integer, Videojuego> EscribirTodos() {
		// TODO Auto-generated method stub
		return null;

	}


	@Override
	public HashMap<Integer, Videojuego> BorrarVideojuego() {
		Controlador mControlador = new Controlador();
		mControlador.PedirDatosMongoBD(ListaVideojuegos);
		Document documento = new Document();
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
			String idtxt = Integer.toString(entry.getKey());
			documento.put("ID", idtxt);
			documento.put("Nombre", entry.getValue().getNombre());
			documento.put("Fecha_Lanzamiento", entry.getValue().getFecha_Lanzamiento());
			documento.put("Desarrollador", entry.getValue().getDesarrollador());
			documento.put("Plataforma", entry.getValue().getPlataforma());
		}
		
		System.out.println(documento.toString());
		collectionVideojuegos.deleteOne(documento);
		System.out.println("Datos videojuegos borrados correctamente");
		mControlador.Cargar_Inicio();
		
		return ListaVideojuegos;
	}


	@Override
	public HashMap<Integer, Personajes> BorrarPersonaje() {
		Controlador mControlador = new Controlador();
		mControlador.PedirDatosMongoPER(ListaPersonajes);
		Document documento = new Document();
		for (Entry<Integer, Personajes> entry : ListaPersonajes.entrySet()) {
			String idtxt = Integer.toString(entry.getKey());
			documento.put("ID", idtxt);
			documento.put("Nombre_Personaje", entry.getValue().getNombre_Personaje());
			String idjuegotxt = Integer.toString(entry.getValue().getjuego());
			documento.put("ID_Juego", idjuegotxt );
		}
		
		System.out.println(documento.toString());
		collectionPersonajes.deleteOne(documento);
		System.out.println("Datos Personajes borrados correctamente");
		mControlador.Cargar_Inicio();
		
		return ListaPersonajes;
	}

	public void actualizarVideojuego() {
		Controlador mControlador = new Controlador();
		mControlador.PedirDatosUpdateMongoBD(ListaVideojuegos);
		Document documento = new Document();
		Document id = new Document();
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
			String idtxt = Integer.toString(entry.getKey());
			id.put("ID", idtxt);
			documento.put("Nombre", entry.getValue().getNombre());
			documento.put("Fecha_Lanzamiento", entry.getValue().getFecha_Lanzamiento());
			documento.put("Desarrollador", entry.getValue().getDesarrollador());
			documento.put("Plataforma", entry.getValue().getPlataforma());
		}
		Document set = new Document("$set", documento);
		System.out.println(documento.toString());
		 collectionVideojuegos.updateMany(id, set);
		 mControlador.Cargar_Inicio();
	}

	public void actualizarPersonaje() {
		Controlador mControlador = new Controlador();
		mControlador.PedirDatosMongoUpdatePER(ListaPersonajes);
		Document documento = new Document();
		Document id = new Document();
		for (Entry<Integer, Personajes> entry : ListaPersonajes.entrySet()) {
			String idtxt = Integer.toString(entry.getKey());
			id.put("ID", idtxt);
			documento.put("Nombre_Personaje", entry.getValue().getNombre_Personaje());
			String idjuegotxt = Integer.toString(entry.getValue().getjuego());
			documento.put("ID_Juego", idjuegotxt );
		}
		Document set = new Document("$set", documento);
		System.out.println(documento.toString());
		collectionPersonajes.updateMany(id, set);
		
		
	}
	
}