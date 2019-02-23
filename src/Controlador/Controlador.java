package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import org.bson.Document;

import BD_Manager.BD_Manager;
import Modelo.Personajes;
import Modelo.Videojuego;
import MongoManager.AccesoMongo;
import Vistas.*;
import JSONManager.*;
import FileManager.FileManager;
import HibernateManager.HibernateManager;

public class Controlador {

	// Llamadas a BD_Manager
	public void ImprimirDatos() {
		BD_Manager mBD = new BD_Manager();
		mBD.LeerTodos();
	}

	public void ImprimirVideojuegos() {
		BD_Manager mBD = new BD_Manager();
		mBD.LeerTodosAux();
	}

	public void ImprimirDatosPer() {
		BD_Manager mBD = new BD_Manager();
		mBD.LeerTodosPer();
	}

	public void InsertarBBDD() {
		BD_Manager mBD = new BD_Manager();
		mBD.Annadir();
	}

	public void InsertarBBDDPer() {
		BD_Manager mBD = new BD_Manager();
		mBD.AnnadirPer();
	}

	public void TXT2BBDD() {
		BD_Manager mBD = new BD_Manager();
		mBD.EscribirTodos();
	}

	// Llamadas a File_Manager
	public void Leer_Fichero() {
		FileManager mFM = new FileManager();
		mFM.LeerTodos();
	}

	public void Leer_FicheroPer() {
		FileManager mFM = new FileManager();
		mFM.LeerTodosPer();
	}

	public void Escribir_Fichero() {
		FileManager mFM = new FileManager();
		mFM.EscribirTodos();
	}

	public void Escribir_FicheroPer() {
		FileManager mFM = new FileManager();
		mFM.EscribirTodosPer();
	}

	public void BBDD2TXT() {
		FileManager mFM = new FileManager();
		mFM.Annadir();
	}

	public void BBDD2TXTPer() {
		FileManager mFM = new FileManager();
		mFM.EscribirTodosPer();
	}

	// Llamadas a Hibernate_Manager

	public void BBDD2TXTHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.EscribirTodos();
	}

	public void BBDD2TXTPerHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.EscribirTodosPer();
	}

	public void ImprimirDatosHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.LeerTodos();
	}

	public void ImprimirVideojuegosHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.LeerTodosAux();
	}

	public void ImprimirDatosPerHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.LeerTodosPer();
	}

	public void ImprimirPersonajesHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.LeerTodosPerAux();
	}

	public void InsertarHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.Annadir();
	}

	public void InsertarPerHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.AnnadirPer();
	}

	public void EliminarHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.EliminarHB();
	}

	public void EliminarPerHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.EliminarPerHB();
	}

	public void TXT2BBDDHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.Fichero2HB();
	}

	public void Fichero2HBPer() {
		HibernateManager mHM = new HibernateManager();
		mHM.Fichero2HBPer();
	}

	// Llamadas a la Vista
	public void PedirDatosBD(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Inicio mVista = new Inicio();
		// TODO Auto-generated method stub
		mVista.PedirDatosDB(ListaVideojuegos);
	}

	public void PedirDatosPerDB(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.PedirDatoPerDB(listaPersonajes);
	}

	public void PedirDatosF(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Inicio mVista = new Inicio();
		mVista.PedirDatosF(ListaVideojuegos);
	}

	public void PedirDatosPerF(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.PedirDatoPerF(listaPersonajes);
	}

	public void PedirDatosHB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Inicio mVista = new Inicio();
		mVista.PedirDatosHB(ListaVideojuegos);
	}

	public void MostrarDatosPer(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.sacarPantallaPer(listaPersonajes);
	}

	public void MostrarDatos(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Inicio mVista = new Inicio();
		mVista.sacarPantalla(ListaVideojuegos);
	}

	public void PedirDatosPerHB(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.PedirDatosPerHB(listaPersonajes);
	}

	public void EliminarDatosHB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Inicio mVista = new Inicio();
		mVista.EliminarDatosHB(ListaVideojuegos);
	}

	public void EliminarDatosPerHB(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.EliminarDatosPerHB(listaPersonajes);
	}

	public void leerPersonajes_JSON() {

		AccesoJSONRemoto mJSON = new AccesoJSONRemoto();
		mJSON.lee();

		
	}

	public void MostrarPersonajesJSON(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.sacarPantallaPer(listaPersonajes);
	}

	// Llamadas a mongodb

	// LLamada al m�todo leerVideojuegos(accesoTablas) para mostrar los videojuegos
	public void imprimirVideojuegos() {
		AccesoMongo mTablas = new AccesoMongo();
		//mTablas.leerVideojuegos();
		mTablas.LeerTodos();

	}

	// LLamada al m�todo leerPersinajes(accesoTablas) para mostrar los personajes
	public void imprimirPersonajes() {
		AccesoMongo mTablas = new AccesoMongo();
		mTablas.LeerTodosPer();

	}

	// LLamada al m�todo insertarVideojuego(accesoTablas) para insertar un
	// videojuegos
	
	public void PedirDatosMongoBD(HashMap<Integer, Videojuego> listaVideojuegos) {
		Inicio mVista = new Inicio();
		mVista.PedirDatosMongoDB(listaVideojuegos);
		
	}
	
	public void PedirDatosMongoPER(HashMap<Integer, Personajes> listaPersonajes) {
		Inicio mVista = new Inicio();
		mVista.PedirDatosMongoPER(listaPersonajes);
		
	}
	
	public void anadirVideojuegoMongo() {
		AccesoMongo mTablas = new AccesoMongo();
		mTablas.Annadir();
	}

	// LLamada al m�todo insertarPersonaje(accesoTablas) para insertar un personaje
	public void anadirPersonajeMongo() {
		AccesoMongo mTablas = new AccesoMongo();
		mTablas.insertarPersonaje();
	}

	// LLamada al m�todo borrarVideojuegp(accesoTablas) para borrar un videojuego
	public void eliminarVideojuegoMongo() {
		AccesoMongo mTablas = new AccesoMongo();
		mTablas.borrarVideojuego();
	}

	// LLamada al m�todo borrarPersonaje(accesoTablas) para borrar un personaje
	public void eliminarPersonajeMongo() {
		AccesoMongo mTablas = new AccesoMongo();
		mTablas.borrarPersonaje();
	}

	// Llamada al m�todo actualizarVideojuego(accesoTablas) para actualizar un videojuego
	public void modificarVideojuegoMongo() {
		AccesoMongo mTablas = new AccesoMongo();
		//mTablas.actualizarVideojuego();
	}

	// Llamada al m�todo actualizarPersonaje(accesoTablas) para actualizar un personaje
	public void modificarPersonajeMongo() {
		AccesoMongo mTablas = new AccesoMongo();
		//mTablas.actualizarPersonaje();
	}

	public void Cargar_Inicio() {
		try {
			Inicio mVista = new Inicio();
			mVista.CargarMenuPrincipal();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}



}
