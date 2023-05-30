-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Base de datos: `id20759238_grupo10`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `OPCION_CRUD2`
--

CREATE TABLE `OPCION_CRUD2` (
  `id_opcion` char(6) NOT NULL,
  `des_opcion` varchar(30) NOT NULL,
  `fecha_modificado` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `OPCION_CRUD2`
--

INSERT INTO `OPCION_CRUD2` (`id_opcion`, `des_opcion`, `fecha_modificado`) VALUES
('006', 'Consultar y eliminar', '2019-01-01 06:00:00'),
('007', 'Agregar y actualizar', '2019-01-01 06:00:00');

--
-- Indices de la tabla `OPCION_CRUD2`
--
ALTER TABLE `OPCION_CRUD2`
  ADD PRIMARY KEY (`id_opcion`);



  -- SOLO DB WEB
  -- Volcado de datos para la tabla `ciclo2` SOLO PARA LA DB WEB
  --

INSERT INTO `OPCION_CRUD2` (`id_opcion`, `des_opcion`, `fecha_modificado`) VALUES
('008', 'Consultar y actualizar', '2019-01-01 06:00:00'),
('009', 'Agregar y eliminar', '2019-01-01 06:00:00');

