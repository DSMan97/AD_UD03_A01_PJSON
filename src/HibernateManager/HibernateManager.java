package HibernateManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.persistence.Entity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.omg.CORBA.Request;

import Controlador.Controlador;
import Interface.Intercambio;
import Modelo.Modelo;
import Modelo.Personajes;
import Modelo.Videojuego;
import Vistas.Inicio;

public class HibernateManager implements Intercambio {
	private static String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private static String archivo_personajes = "src/Modelo/personajes.txt";
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	HashMap<Integer, Personajes> listaPersonajes = new HashMap<Integer, Personajes>();

	public SessionFactory sf = new Configuration().configure().buildSessionFactory();
	public Session s = sf.openSession();

	// TODO Auto-generated method stub
	@Override
	public HashMap<Integer, Videojuego> EscribirTodos() {
		// Copiar la tabla videojuegos de BB en el fichero
		Videojuego v = new Videojuego();

		Controlador mControlador = new Controlador();
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getDesarrollador();
		v.getPlataforma();

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_videojuegos, false));

			s.beginTransaction();
			Query q = s.createQuery("Select v from videojuegos v");
			List resultado = q.list();
			Iterator videojuegositerador = resultado.iterator();

			while (videojuegositerador.hasNext()) {
				Videojuego vdo = (Videojuego) videojuegositerador.next();
				int ID = vdo.getID();
				String Nombre = vdo.getNombre();
				String Fecha = vdo.getFecha_Lanzamiento();
				String Plataforma = vdo.getPlataforma();
				String Desarrollador = vdo.getDesarrollador();

				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(ID, mVideojuego);
			}

			s.getTransaction().commit();
			s.close();

			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre() + "\n"
						+ "Fecha de Lanzamiento: " + entry.getValue().getFecha_Lanzamiento() + "\n" + "Desarrollador: "
						+ entry.getValue().getDesarrollador() + "\n" + "Plataforma: " + entry.getValue().getPlataforma()
						+ "\n");
			}

			bw.close();
			mControlador.Cargar_Inicio();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		return ListaVideojuegos;

	}

	public HashMap<Integer, Personajes> EscribirTodosPer() {
		// Copiar la tabla personajes de BBDD en el fichero
		Personajes p = new Personajes();
		Controlador mControlador = new Controlador();
		p.getID_per();
		p.getNombre_Personaje();
		p.getjuego();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_personajes, false));

			s.beginTransaction();
			Query q = s.createQuery("Select p from personajes p");
			List resultado2 = q.list();
			Iterator personajesIterator = resultado2.iterator();
			while (personajesIterator.hasNext()) {
				Personajes pnj = (Personajes) personajesIterator.next();
				int ID = pnj.getID_per();
				String nombre_Personaje = pnj.getNombre_Personaje();
				int juego = pnj.getjuego();
				Videojuego mv = new Videojuego();
				
				mv.getID();
				Personajes mPersonajes = new Personajes(nombre_Personaje, p.getjuego());
				listaPersonajes.put(ID, mPersonajes);

			}
			s.getTransaction().commit();
			s.close();
			for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {
				bw.write("ID: " + entry.getKey() + "\n" + "Nombre: " + entry.getValue().getNombre_Personaje() + "\n"
						+ "id_juego: " + entry.getValue().getjuego() + "\n");
			}
			bw.close();
			mControlador.Cargar_Inicio();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaPersonajes;

	}

	@Override
	public HashMap<Integer, Videojuego> Annadir() {
		Videojuego v = new Videojuego();
		Controlador mControlador = new Controlador();

		s.beginTransaction();
		mControlador.PedirDatosHB(ListaVideojuegos);
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

			v.setID(entry.getKey());
			v.setNombre(entry.getValue().getNombre());
			v.setFecha_Lanzamiento(entry.getValue().getFecha_Lanzamiento());
			v.setPlataforma(entry.getValue().getPlataforma());
			v.setDesarrollador(entry.getValue().getDesarrollador());
		}
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getPlataforma();
		v.getDesarrollador();

		s.save(v);
		s.getTransaction().commit();
		s.close();
		mControlador.Cargar_Inicio();
		return ListaVideojuegos;
	}

	@Override
	public HashMap<Integer, Personajes> AnnadirPer() {
		Personajes p = new Personajes();
		Controlador mControlador = new Controlador();
		s.beginTransaction();
		mControlador.PedirDatosPerHB(listaPersonajes);
		for (Entry<Integer, Personajes> entry: listaPersonajes.entrySet()) {
			p.setID_per(entry.getKey());
			p.setNombre_Personaje(entry.getValue().getNombre_Personaje());
			p.setjuego(entry.getValue().getjuego());
		}
		p.getID_per();
		p.getNombre_Personaje();
		p.getjuego();
		s.save(p);
		s.getTransaction().commit();
		s.close();
		mControlador.Cargar_Inicio();
		return listaPersonajes;
	}

	public HashMap<Integer, Videojuego> LeerTodosAux() {
		Videojuego v = new Videojuego();
		Controlador mControlador = new Controlador();
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getDesarrollador();
		v.getPlataforma();
		try {

			s.beginTransaction();
			Query q = s.createQuery("Select v from videojuegos v");
			List resultado = q.list();
			Iterator videojuegositerador = resultado.iterator();

			while (videojuegositerador.hasNext()) {
				Videojuego vdo = (Videojuego) videojuegositerador.next();
				int ID = vdo.getID();
				String Nombre = vdo.getNombre();
				String Fecha = vdo.getFecha_Lanzamiento();
				String Plataforma = vdo.getPlataforma();
				String Desarrollador = vdo.getDesarrollador();

				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(ID, mVideojuego);
			}

			s.getTransaction().commit();
			s.close();

			mControlador.MostrarDatos(ListaVideojuegos);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;
	}
	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		Videojuego v = new Videojuego();
		Controlador mControlador = new Controlador();
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getDesarrollador();
		v.getPlataforma();
		try {

			s.beginTransaction();
			Query q = s.createQuery("Select v from videojuegos v");
			List resultado = q.list();
			Iterator videojuegositerador = resultado.iterator();

			while (videojuegositerador.hasNext()) {
				Videojuego vdo = (Videojuego) videojuegositerador.next();
				int ID = vdo.getID();
				String Nombre = vdo.getNombre();
				String Fecha = vdo.getFecha_Lanzamiento();
				String Plataforma = vdo.getPlataforma();
				String Desarrollador = vdo.getDesarrollador();

				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(ID, mVideojuego);
			}

			s.getTransaction().commit();
			s.close();

			mControlador.MostrarDatos(ListaVideojuegos);
			mControlador.Cargar_Inicio();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;
	}

	public HashMap<Integer, Personajes> LeerTodosPerAux() {
		Controlador mControlador = new Controlador();
		Personajes p = new Personajes();
		p.getID_per();
		p.getNombre_Personaje();
		p.getjuego();
		try {

			s.beginTransaction();
			Query q = s.createQuery("Select p from personajes p");
			List resultado = q.list();
			Iterator personajesIterator = resultado.iterator();

			while (personajesIterator.hasNext()) {
				Personajes pnj = (Personajes) personajesIterator.next();
				int ID = pnj.getID_per();
				String nombre_Personaje = pnj.getNombre_Personaje();
				int juego = pnj.getjuego();
				Personajes mPersonajes = new Personajes(nombre_Personaje, juego);
				listaPersonajes.put(ID, mPersonajes);
			}

			s.getTransaction().commit();
			s.close();

			mControlador.MostrarDatosPer(listaPersonajes);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPersonajes;
	}
	@Override
	public HashMap<Integer, Personajes> LeerTodosPer() {
		Controlador mControlador = new Controlador();
		Personajes p = new Personajes();
		p.getID_per();
		p.getNombre_Personaje();
		p.getjuego();
		try {

			s.beginTransaction();
			Query q = s.createQuery("Select p from personajes p");
			List resultado = q.list();
			Iterator personajesIterator = resultado.iterator();

			while (personajesIterator.hasNext()) {
				Personajes pnj = (Personajes) personajesIterator.next();
				int ID = pnj.getID_per();
				String nombre_Personaje = pnj.getNombre_Personaje();
				int juego = pnj.getjuego();
				Personajes mPersonajes = new Personajes(nombre_Personaje, juego);
				listaPersonajes.put(ID, mPersonajes);
			}

			s.getTransaction().commit();
			s.close();

			mControlador.MostrarDatosPer(listaPersonajes);
			mControlador.Cargar_Inicio();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPersonajes;
	}
	public HashMap<Integer, Videojuego> EliminarHB(){
		Videojuego v  = new Videojuego();
		Controlador mControlador = new Controlador();
		mControlador.EliminarDatosHB(ListaVideojuegos);
		
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

			v.setNombre(entry.getValue().getNombre());
		
		}
		String name=v.getNombre();
		//escribir llamada con parametro a procedure
		
		Query q1 = s.createSQLQuery("CALL delete_videojuegos(:name1)");
		q1.setParameter("name1", name);
		Query q2 = s.createSQLQuery("CALL delete_videojuegos2(:name)");
		q2.setParameter("name", name);
		s.beginTransaction();
		q1.executeUpdate();
		q2.executeUpdate();
		s.getTransaction().commit();
		s.close();
		
	
		mControlador.Cargar_Inicio();
		return ListaVideojuegos;
		
	}
	public HashMap<Integer, Personajes> EliminarPerHB(){
		Personajes p  = new Personajes();
		Controlador mControlador = new Controlador();
		mControlador.EliminarDatosPerHB(listaPersonajes);
		
		for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {

			p.setNombre_Personaje(entry.getValue().getNombre_Personaje());
		
		}
		String name=p.getNombre_Personaje();
		//escribir llamada con parametro a procedure
		
		Query q1 = s.createSQLQuery("DELETE FROM personajes where Nombre_Personaje = :name1");
		q1.setParameter("name1", name);
		s.beginTransaction();
		q1.executeUpdate();
		s.getTransaction().commit();
		s.close();
		
		
		mControlador.Cargar_Inicio();
		return listaPersonajes;
		
	}	
	public HashMap<Integer, Videojuego> Fichero2HB(){
	Controlador mControlador = new Controlador();
	Videojuego v = new Videojuego();
	try {

		BufferedReader br = new BufferedReader(new FileReader(archivo_videojuegos));

		String linea;
		while ((linea = br.readLine()) != null) {
			String idtxt = linea.substring(4);
			int id = Integer.parseInt(idtxt);
			String nametxt = br.readLine().substring(8);
			String fechatxt = br.readLine().substring(22);
			String desarrolladortxt = br.readLine().substring(15);
			String plataformatxt = br.readLine().substring(12);

			Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);
			ListaVideojuegos.put(id, mVideojuego);
		}
	

		s.beginTransaction();

		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {

			v.setID(entry.getKey());
			v.setNombre(entry.getValue().getNombre());
			v.setFecha_Lanzamiento(entry.getValue().getFecha_Lanzamiento());
			v.setPlataforma(entry.getValue().getPlataforma());
			v.setDesarrollador(entry.getValue().getDesarrollador());
		
			
		
			
		}
		
		
		
		v.getID();
		v.getNombre();
		v.getFecha_Lanzamiento();
		v.getPlataforma();
		v.getDesarrollador();

		s.update(v);
		s.getTransaction().commit();
		br.close();
		s.close();

		mControlador.Fichero2HBPer();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mControlador.Cargar_Inicio();

	return ListaVideojuegos;

}
	public HashMap<Integer, Personajes> Fichero2HBPer() {
		Personajes p = new Personajes();
		Controlador mControlador = new Controlador();

		try {

			BufferedReader brf = new BufferedReader(new FileReader(archivo_personajes));

			String linea2;
			while ((linea2 = brf.readLine()) != null) {
				Videojuego mv = new Videojuego();
				String idtxt = linea2.substring(4);
				int id = Integer.parseInt(idtxt);
				String nombre_Personaje = brf.readLine().substring(8);
				String id_Juegotxt = brf.readLine().substring(10);
				int id_Juego = Integer.parseInt(id_Juegotxt);
				
				Personajes mPersonaje = new Personajes(nombre_Personaje,id_Juego);
				listaPersonajes.put(id, mPersonaje);
			}
			


			for (Entry<Integer, Personajes> entry: listaPersonajes.entrySet()) {
				p.setID_per(entry.getKey());
				p.setNombre_Personaje(entry.getValue().getNombre_Personaje());
				p.setjuego(entry.getValue().getjuego());
				
				
				
			}
			p.getID_per();
			p.getNombre_Personaje();
			p.getjuego();
			s.beginTransaction();
			s.update(p);
			s.getTransaction().commit();
			
			
			
			s.close();

			brf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mControlador.Cargar_Inicio();

		return listaPersonajes;

	}

}
