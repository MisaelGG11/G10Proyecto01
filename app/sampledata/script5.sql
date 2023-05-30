-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Base de datos: `grupo10`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuela2`
--

CREATE TABLE `ciclo2` (
  `id_ciclo` int(11) NOT NULL,
  `ciclo` int(11) NOT NULL,
  `anio` int(11) NOT NULL,
  `fecha_modificado` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciclo2`
--

INSERT INTO `ciclo2` (`id_ciclo`, `ciclo`, `anio`, `fecha_modificado`) VALUES
(6, '1', '1999', '2019-01-01 06:00:00'),
(7, '2', '2001', '2019-01-01 06:00:00');

--
-- Indices de la tabla `escuela2`
--
ALTER TABLE `ciclo2`
  ADD PRIMARY KEY (`id_ciclo`);



  -- SOLO DB WEB
  -- Volcado de datos para la tabla `ciclo2` SOLO PARA LA DB WEB
  --

INSERT INTO `ciclo2` (`id_ciclo`, `ciclo`, `anio`, `fecha_modificado`) VALUES
(6, '1', '1999', '2019-01-01 06:00:00'),
(7, '2', '2001', '2019-01-01 06:00:00');
