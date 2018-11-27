package Videojuegos;

public class Videojuego {

	
	private String Nombre;
	private String Fecha_Lanzamiento;
	private String Desarrollador;
	private String Plataforma;

	public Videojuego( String Nombre, String Fecha, String Desarrollador, String Plataforma) {
		this.Nombre = Nombre;
		this.Fecha_Lanzamiento = Fecha;
		this.Desarrollador=Desarrollador;
		this.Plataforma=Plataforma;
		
		
	}


	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getFecha_Lanzamiento() {
		return Fecha_Lanzamiento;
	}

	public void setFecha_Lanzamiento(String fecha_Lanzamiento) {
		Fecha_Lanzamiento = fecha_Lanzamiento;
	}

	public String getDesarrollador() {
		return Desarrollador;
	}

	public void setDesarrollador(String desarrollador) {
		Desarrollador = desarrollador;
	}

	public String getPlataforma() {
		return Plataforma;
	}

	public void setPlataforma(String plataforma) {
		Plataforma = plataforma;
	}

}
