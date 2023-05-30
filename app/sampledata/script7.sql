-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 30-05-2023 a las 22:13:23
-- Versión del servidor: 10.5.20-MariaDB
-- Versión de PHP: 7.3.32

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
  `fecha_modificado` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `OPCION_CRUD2`
--

INSERT INTO `OPCION_CRUD2` (`id_opcion`, `des_opcion`, `fecha_modificado`) VALUES
('006', 'Consultar y eliminar', '2023-05-30'),
('007', 'Añadir y actualizar', '2023-05-30');

--
-- Indices de la tabla `OPCION_CRUD2`
--
ALTER TABLE `OPCION_CRUD2`
  ADD PRIMARY KEY (`id_opcion`);
COMMIT;





  -- SOLO DB WEB
  -- Volcado de datos para la tabla `ciclo2` SOLO PARA LA DB WEB
  --

INSERT INTO `OPCION_CRUD2` (`id_opcion`, `des_opcion`, `fecha_modificado`) VALUES
('008', 'Consultar y actulizar', '2023-05-30'),
('009', 'añafir y eliminar', '2023-05-30');

