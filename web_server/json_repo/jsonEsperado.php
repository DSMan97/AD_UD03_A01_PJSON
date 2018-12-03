<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrPersonajeEsperado = array();
$arrEquipoEsperado = array();

$arrEsperado["peticion"] = "add";
$arrEsperado["peticion"] = "delete";
$arrEsperado["peticion"] = "update";

$arrPersonajeEsperado["nombre"] = "Lorenzo (Un string)";
$arrPersonajeEsperado["id_juego"] = "2 (Un int)";
$arrPersonajeEsperado["id"] = "2 (Un int)";

$arrEquipoEsperado["equipo"] = "R.Madrid (Un String)";
$arrEquipoEsperado["id"] = "3 (Un int)";

$arrEsperado["personajeAnnadir"] = $arrJugadorEsperado;
$arrEsperado["equipoAnnadir"] = $arrEquipoEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["personajeAnnadir"])){
		
		$auxPersonaje = $recibido["personajeAnnadir"];
		if(isset($auxJugador["nombre"]) && isset($auxJugador["id_juego"]) && isset($auxJugador["id"])){
			$auxCorrecto = true;
		}
		
	}
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["equipoAnnadir"])){
	$auxEquipo = $recibido["equipoAnnadir"];
		if(isset($auxEquipo["id"]) && isset($auxEquipo["equipo"]) ){
			$auxCorrecto = true;
		}
}
	
	return $auxCorrecto;
	
}
