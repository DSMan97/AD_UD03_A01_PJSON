package Interface;

import java.util.HashMap;


import Modelo.Personajes;
import Modelo.Videojuego;

public interface Intercambio {


	public HashMap<Integer, Videojuego> EscribirTodos();
	public HashMap<Integer, Videojuego> Annadir();
	public HashMap<Integer, Videojuego>LeerTodos();
	
	public HashMap<Integer, Personajes> EscribirTodosPer();
	public HashMap<Integer, Personajes>LeerTodosPer();
	public HashMap<Integer, Personajes> AnnadirPer();
	
}
