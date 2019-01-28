package MongoManager;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Conexion {
	// Conexión con mongo
	MongoClient mongo = new MongoClient("localhost", 27017);
	// MongoDatabase database = mongo.getDatabase("videojuegos");
	protected MongoDatabase db = mongo.getDatabase("videojuegos");
}
