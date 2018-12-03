package Modelo;



public class Personajes {
	
	private int ID_per;
	private String Nombre_Personaje;
	private int juego;
	private Videojuego mjuego;
	//Constructor de la tabla personajes
	public Personajes(){
		
	}
	
	



	public Videojuego getMjuego() {
		return mjuego;
	}


	public void setMjuego(Videojuego mjuego) {
		this.mjuego = mjuego;
	}


	public Personajes( String nombre_Personaje, int id_juegotxt) {
		
		this.Nombre_Personaje = nombre_Personaje;
		this.juego = id_juegotxt;
	}
	
	
	//getters y setters de los atributos
	public int getID_per() {
		return ID_per;
	}
	public void setID_per(int ID_per) {
		this.ID_per = ID_per;
	}
	public String getNombre_Personaje() {
		return Nombre_Personaje;
	}
	public void setNombre_Personaje(String nombre_Personaje) {
		this.Nombre_Personaje = nombre_Personaje;
	}
	public int getjuego(){
		return juego;
		
	}
	public void setjuego(int juego) {
		this.juego = juego;
	}


	public String toString(){
		String aux ="";
		
		aux += "------------------------------------------";
		aux += "\n	ID: " + this.ID_per;
		aux += "\n	Nombre: " + this.Nombre_Personaje;
		aux += "\n	Videojuego: " + this.juego;
		aux += "\n------------------------------------------";
		
		return aux;
	}

	
}
