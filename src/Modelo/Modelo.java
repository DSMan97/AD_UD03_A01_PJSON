package Modelo;

import Main.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

import javax.crypto.CipherInputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Modelo {

	// Atributos de la clase
	private String bd;
	private String login;
	private String pass;
	private String url;
	public Connection conexion;
	
	
	// Constructor que crea la conexión y recoge los datos del archivo ini
	public Modelo() {


		getBBDDini();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, login, pass);
			if (conexion != null) {
				

				// stmt.close();
				// conexion.close();
			}
			//System.out.println(" - Conexión con MySQL establecida -");

		} catch (Exception e) {
			System.out.println(" – Error de Conexión con MySQL -");
			e.printStackTrace();
		}

	}

	private void getBBDDini() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
	
		try {
			
		File miConfig = new File("src/Modelo/bbdd_conf.ini");
			if (miConfig.exists()) {
				entrada = new FileInputStream(miConfig);
				// cargamos el archivo de propiedades
				propiedades.load(entrada);

				// obtenemos las propiedades y las imprimimos
				bd = propiedades.getProperty("DataBase");
				login = propiedades.getProperty("usuario");
				pass = propiedades.getProperty("contrasena");
				url = "jdbc:mysql://localhost/" + bd
						+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

			} else
				System.err.println("Fichero no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	

}