-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Base de datos: `grupo10`
--

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `LOCALIDAD_2`
--

CREATE TABLE `localidad2` (
  `id_localidad` int(6) NOT NULL,
  `edificio_localidad` varchar(60) NOT NULL,
  `nombre_localidad` varchar(30) NOT NULL,
  `capacidad_localidad` int(3) NOT NULL,
  `fecha_modificacion_localidad` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `LOCALIDAD_2`
--

INSERT INTO `localidad2` (`id_localidad`, `edificio_localidad`, `nombre_localidad`, `capacidad_localidad`,`fecha_modificacion_localidad`) VALUES
(11, 'Edificio B', 'B11',100, '2023-05-01 06:00:00'),
(12, 'Edificio B', 'B12', 100,'2023-05-01 06:00:00'),
(13, 'Labcomp EISI', 'LABCOMP04',20, '2023-05-05 06:00:00');

--
-- Indices de la tabla `LOCALIDAD_2`
--
ALTER TABLE `localidad2`
ADD PRIMARY KEY (`id_localidad`);



-- SOLO DB WEB
-- Volcado de datos para la tabla `Localidad2` SOLO PARA LA DB WEB
--
   INSERT INTO `localidad2` (`id_localidad`, `edificio_localidad`, `nombre_localidad`, `capacidad_localidad`,`fecha_modificacion_localidad`) VALUES
   (14, 'Edificio C', 'C31',100, '2023-05-01 06:00:00'),
   (15, 'Edificio C', 'C33', 100,'2023-05-01 06:00:00'),
   (16, 'Labcomp EISI', 'LABCOMP05',20, '2023-05-05 06:00:00');