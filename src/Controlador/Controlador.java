package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import BD_Manager.BD_Manager;
import Videojuegos.Personajes;
import Videojuegos.Videojuego;
import Vistas.*;
import Interface.Intercambio;
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
		mBD.Añadir();
	}

	public void InsertarBBDDPer() {
		BD_Manager mBD = new BD_Manager();
		mBD.AñadirPer();
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
		mFM.Añadir();
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
		mHM.Añadir();
	}

	public void InsertarPerHB() {
		HibernateManager mHM = new HibernateManager();
		mHM.AñadirPer();
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

	public void Cargar_Inicio() {
		try {
			Inicio mVista = new Inicio();
			mVista.CargarMenuPrincipal();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
