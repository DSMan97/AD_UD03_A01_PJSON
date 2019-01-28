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


	public void insertarVideojuego() {
		Scanner sc = new Scanner(System.in);
		// Esta linea es solo para comprobar que el switch funciona
		Document documento = new Document();
		documento.put("ID", sc.nextLine());
		documento.put("Nombre", sc.nextLine());
		documento.put("Fecha_Lanzamiento", sc.nextLine());
		documento.put("Desarrollador", sc.nextLine());
		documento.put("Plataforma", sc.nextLine());
		collectionVideojuegos.insertOne(documento);
		System.out.println("Datos introducidos correctamente");
	}

	// Método para insertar un personaje
	public void insertarPersonaje() {
		Scanner sc = new Scanner(System.in);
		// Esta linea es solo para comprobar que el switch funciona
		Document documento = new Document();
		System.out.println("ID:");
		String idTxt = sc.nextLine();
		int id = Integer.parseInt(idTxt);
		documento.put("ID", id);
		System.out.println("Nombre_Personaje:");
		String nombre = sc.nextLine();
		documento.put("Nombre_Personaje", nombre);
		//sc.nextLine();
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
		// Esta linea es solo para comprobar que el switch funciona
		Document documento = new Document();
		documento.remove("ID", sc.nextLine());
		documento.remove("Nombre", sc.nextLine());
		documento.remove("Fecha_Lanzamiento", sc.nextLine());
		documento.remove("Desarrollador", sc.nextLine());
		documento.remove("Plataforma", sc.nextLine());
		collectionVideojuegos.deleteOne(documento);
		System.out.println("Datos borrados correctamente");
	}

	// Método para borrar un personaje
	public void borrarPersonaje() {
		Scanner sc = new Scanner(System.in);
		// Esta linea es solo para comprobar que el switch funciona
		
	
	}

	// Método para actualizar un videojuego
	public void actualizarVideojuego() {
		//db.videojuegos.update({Nombre: 'hOLA'},{$set: {Nombre: 'Mario'}})
		// Esta linea es solo para comprobar que el switch funciona
		System.out.println("En esta opción actualizaremos un videojuego");
	}

	// Método para actualizar un personaje
	public void actualizarPersonaje() {
		// Esta linea es solo para comprobar que el switch funciona
		System.out.println("En esta opción actualizaremos un personaje");
	}
}
