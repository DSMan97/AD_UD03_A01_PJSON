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
	// M�todo para mostrar la tabla videojuegos
	public void leerVideojuegos() {
		
		FindIterable<Document> busquedaV = collectionVideojuegos.find();
		MongoCursor<Document> lectura = busquedaV.iterator();
		System.out.println("Tabla videojuegos:" + "\n");

		do {
			System.out.println(lectura.next());
		} while (lectura.hasNext());

	}

	// M�todo para mostrar la tabla personajes
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

	// M�todo para insertar un personaje
	public void insertarPersonaje() {
		Scanner sc = new Scanner(System.in);
		// Esta linea es solo para comprobar que el switch funciona
		System.out.println("ID:");
		int id = sc.nextInt();
		Document documento = new Document();
		documento.put("ID", id);
		documento.put("Nombre_Personaje", sc.nextLine());
		documento.put("ID_Juego", sc.nextLine());
		collectionPersonajes.insertOne(documento);
		System.out.println("Datos introducidos correctamente");
	}

	// M�todo para borrar un videojuego
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

	// M�todo para borrar un personaje
	public void borrarPersonaje() {
		Scanner sc = new Scanner(System.in);
		// Esta linea es solo para comprobar que el switch funciona
		
	
	}

	// M�todo para actualizar un videojuego
	public void actualizarVideojuego() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ID:");
		int id = sc.nextInt();
		Document documento = new Document();
		documento.put("ID", id);
		//db.videojuegos.update({Nombre: 'hOLA'},{$set: {Nombre: 'Mario'}})
		// collectionVideojuegos.updateOne(arg0, arg1);
		// Esta linea es solo para comprobar que el switch funciona
		System.out.println("En esta opci�n actualizaremos un videojuego");
	}

	// M�todo para actualizar un personaje
	public void actualizarPersonaje() {
		// Esta linea es solo para comprobar que el switch funciona
		System.out.println("En esta opci�n actualizaremos un personaje");
	}
}
