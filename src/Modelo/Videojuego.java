package Modelo;

public class Videojuego {
	
	private int ID;
	private String Nombre;
	private String Fecha_Lanzamiento;
	private String Desarrollador;
	private String Plataforma;
	
	// constructor de la tabla de videojuegos
	public Videojuego() {
		
	}
	
	public Videojuego( String Nombre, String Fecha, String Desarrollador, String Plataforma) {
		this.Nombre = Nombre;
		this.Fecha_Lanzamiento = Fecha;
		this.Desarrollador=Desarrollador;
		this.Plataforma=Plataforma;
		
		
	}
	//getters y setters de los atributos de la tabla videojuegos
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getFecha_Lanzamiento() {
		return Fecha_Lanzamiento;
	}

	public void setFecha_Lanzamiento(String Fecha_Lanzamiento) {
		this.Fecha_Lanzamiento = Fecha_Lanzamiento;
	}

	public String getDesarrollador() {
		return Desarrollador;
	}

	public void setDesarrollador(String Desarrollador) {
		this.Desarrollador = Desarrollador;
	}

	public String getPlataforma() {
		return Plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.Plataforma = plataforma;
	}
	
	

}
