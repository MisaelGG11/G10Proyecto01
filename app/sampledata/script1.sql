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

CREATE TABLE `escuela2` (
  `id_escuela` int(11) NOT NULL,
  `acronimo` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fecha_modificado` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `escuela2`
--

INSERT INTO `escuela2` (`id_escuela`, `acronimo`, `nombre`, `fecha_modificado`) VALUES
(5, 'EIME', 'Escuela de ingenieria mecanica', '2019-01-01 06:00:00'),
(6, 'EIQI', 'Escuela de ingenieria quimica', '2019-01-01 06:00:00');

--
-- Indices de la tabla `escuela2`
--
ALTER TABLE `escuela2`
  ADD PRIMARY KEY (`id_escuela`);


-- SOLO DB WEB

-- Volcado de datos para la tabla `escuela2` SOLO PARA LA DB WEB
--

  INSERT INTO `escuela2` (`id_escuela`, `acronimo`, `nombre`, `fecha_modificado`) VALUES
  (7, 'EIAL', 'Escuela de ingenieria en alimentos', '2019-01-01 06:00:00'),
  (8, 'EISW', 'Escuela de ingenieria de software', '2019-01-01 06:00:00');

