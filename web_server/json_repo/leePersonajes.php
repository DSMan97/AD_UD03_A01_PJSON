<?php

require 'bbdd.php'; // Incluimos fichero en la que está la coenxión con la BBDD

/*
 * Se mostrará siempre la información en formato json para que se pueda leer desde un html (via js)
 * o una aplicación móvil o de escritorio realizada en java o en otro lenguajes
 */

$arrMensaje = array();  // Este array es el codificaremos como JSON tanto si hay resultado como si hay error



$query = "SELECT * FROM personajes";

$result = $conn->query ( $query );

if (isset ( $result ) && $result) { // Si pasa por este if, la query está está bien y se obtiene resultado
	
	if ($result->num_rows > 0) { // Aunque la query esté bien puede no obtenerse resultado (tabla vacía). Comprobamos antes de recorrer
		
		$arrPersonaje = array();
		
		while ( $row = $result->fetch_assoc () ) {
			
			// Por cada vuelta del bucle creamos un jugador. Como es un objeto hacemos un array asociativo
			$arrPersonaje = array();
			// Por cada columna de la tabla creamos una propiedad para el objeto
			$arrPersonaje["nombre"] = $row["Nombre_Personaje"];
			$arrPersonaje["id"] = $row["ID_PER"];
			$arrPersonaje["id_juego"] = $row["ID_Juego"];
			// Por último, añadimos el nuevo jugador al array de jugadores
			$arrPersonajes[] = $arrPersonaje;
			
		}
		
		// Añadimos al $arrMensaje el array de jugadores y añadimos un campo para indicar que todo ha ido OK
		$arrMensaje["estado"] = "ok";
		$arrMensaje["personajes"] = $arrPersonajes;
		
		
	} else {
		
		$arrMensaje["estado"] = "ok";
		$arrMensaje["personajes"] = []; // Array vacío si no hay resultados
	}
	
} else {
	
	$arrMensaje["estado"] = "error";
	$arrMensaje["mensaje"] = "SE HA PRODUCIDO UN ERROR AL ACCEDER A LA BASE DE DATOS";
	$arrMensaje["error"] = $conn->error;
	$arrMensaje["query"] = $query;
	
}

$mensajeJSON = json_encode($arrMensaje,JSON_PRETTY_PRINT);

//echo "<pre>";  // Descomentar si se quiere ver resultado "bonito" en navegador. Solo para pruebas 
echo $mensajeJSON;
//echo "</pre>"; // Descomentar si se quiere ver resultado "bonito" en navegador

$conn->close ();

?>