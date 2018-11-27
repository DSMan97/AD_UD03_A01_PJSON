-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-10-2018 a las 15:32:02
-- Versión del servidor: 10.1.29-MariaDB
-- Versión de PHP: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `videojuegos`
--
CREATE DATABASE `videojuegos`;


DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_key` ()  NO SQL
ALTER TABLE personajes
  ADD CONSTRAINT personajes_ibfk_1 FOREIGN KEY (ID_Juego) REFERENCES videojuegos (ID)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_key` ()  NO SQL
ALTER TABLE personajes
  DROP FOREIGN KEY personajes_ibfk_1$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personajes`
--

CREATE TABLE `personajes` (
  `ID` int(11) NOT NULL,
  `Nombre_Personaje` varchar(140) NOT NULL,
  `ID_Juego` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personajes`
--

INSERT INTO `personajes` (`ID`, `Nombre_Personaje`, `ID_Juego`) VALUES
(1, 'Mario', 0),
(2, 'Nathan Drake', 1),
(3, 'Princesa Peach', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegos`
--

CREATE TABLE `videojuegos` (
  `ID` int(11) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Fecha_Lanzamiento` varchar(255) NOT NULL,
  `Desarrollador` varchar(255) NOT NULL,
  `Plataforma` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `videojuegos`
--

INSERT INTO `videojuegos` (`ID`, `Nombre`, `Fecha_Lanzamiento`, `Desarrollador`, `Plataforma`) VALUES
(0, 'Super', 'ayer', 'Nintendo', 'DS'),
(1, 'Uncharted', '20/11/2007', 'Naughty Dog', 'PS3');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD KEY `personajes_ibfk_1` (`ID_Juego`);

--
-- Indices de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`ID`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD CONSTRAINT `personajes_ibfk_1` FOREIGN KEY (`ID_Juego`) REFERENCES `videojuegos` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
