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
