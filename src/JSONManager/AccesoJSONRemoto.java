package JSONManager;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Modelo.Personajes;
public class AccesoJSONRemoto {

	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_Personaje, SET_Personaje; // Datos de la conexion

	public AccesoJSONRemoto() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/json_repo/";
		GET_Personaje = "leePersonajes.php";
		SET_Personaje = "escribirPersonajes.php";

	}

	public HashMap<Integer, Personajes> lee() {

		HashMap<Integer, Personajes> auxhm = new HashMap<Integer, Personajes>();

		try {

			System.out.println("---------- Leemos datos de JSON --------------------");

			System.out.println("Lanzamos peticion JSON para Personajes");

			String url = SERVER_PATH + GET_Personaje; // Sacadas de configuracion

			System.out.println("La url a la que lanzamos la petición es " +
			 url); // Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			 System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) { 
					JSONArray array = (JSONArray) respuesta.get("personajes");

					if (array.size() > 0) {

						// Declaramos variables
						Personajes nuevoPer;
						String nombre;
						int id_juego;
						int id;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);

							nombre = row.get("nombre").toString();
							id = Integer.parseInt(row.get("id").toString());
							id_juego = Integer.parseInt(row.get("id_juego").toString());

							nuevoPer = new Personajes();

							auxhm.put(id, nuevoPer);
						}

						System.out.println("Acceso JSON Remoto - Leidos datos correctamente y generado hashmap");
						System.out.println();

					} else { // El array de jugadores está vacío
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return auxhm;
	}

	public void anadirPersonajeJSON(Personajes auxJugador) {

		try {
			JSONObject objPersonaje = new JSONObject();
			JSONObject objPeticion = new JSONObject();

			objPersonaje.put("nombre", auxJugador.getNombre_Personaje());
			objPersonaje.put("id_juego", auxJugador.getjuego());
			objPersonaje.put("id", auxJugador.getID_per());

			// Tenemos el jugador como objeto JSON. Lo añadimos a una peticion
			// Lo transformamos a string y llamamos al
			// encargado de peticiones para que lo envie al PHP

			objPeticion.put("peticion", "add");
			objPeticion.put("personajeAnnadir", objPersonaje);
			
			String json = objPeticion.toJSONString();

			System.out.println("Lanzamos peticion JSON para almacenar un personaje");

			String url = SERVER_PATH + SET_Personaje;

			System.out.println("La url a la que lanzamos la petición es " + url);
			System.out.println("El json que enviamos es: ");
			System.out.println(json);
			//System.exit(-1);

			String response = encargadoPeticiones.postRequest(url, json);
			
			System.out.println("El json que recibimos es: ");
			
			System.out.println(response); // Traza para pruebas
			System.exit(-1);
			
			// Parseamos la respuesta y la convertimos en un JSONObject
			

			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algún error de parseo (json
										// incorrecto porque hay algún caracter
										// raro, etc.) la respuesta será null
				System.out.println("El json recibido no es correcto. Finaliza la ejecución");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				
				// Sera "ok" si todo ha ido bien o "error" si hay algún problema
				String estado = (String) respuesta.get("estado"); 
				if (estado.equals("ok")) {

					System.out.println("Almacenado jugador enviado por JSON Remoto");

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algún error

					System.out.println("Acceso JSON REMOTO - Error al almacenar los datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}
		} catch (Exception e) {
			System.out.println(
					"Excepcion desconocida. Traza de error comentada en el método 'annadirJugador' de la clase JSON REMOTO");
			// e.printStackTrace();
			System.out.println("Fin ejecución");
			System.exit(-1);
		}

	}
	
	private HashMap<Integer, Personajes> devuelvePeronajes() {

		HashMap<Integer, Personajes> hmAux = lee();

		return hmAux;

	}
}
