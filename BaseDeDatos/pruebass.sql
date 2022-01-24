-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2022 at 04:55 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pruebass`
--
CREATE DATABASE IF NOT EXISTS `pruebass` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pruebass`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `buscar-clientes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar-clientes` ()  BEGIN



SELECT *from clientes;

Call ciclo(2);



END$$

DROP PROCEDURE IF EXISTS `buscar_abonos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_abonos` (IN `id_cob` INT, IN `id_pres` INT, IN `criterio` VARCHAR(100), IN `fecha1` DATE, IN `fecha2` DATE, IN `estado` VARCHAR(2))  NO SQL
BEGIN





SELECT 

concat(clientes.nombre,' ',clientes.apellido)as nombre,clientes.id as id_cliente,abonos.* 

from 

prestamos,abonos,clientes 

where 

(abonos.id_prestamo = prestamos.id)

and 

(prestamos.id_cliente = clientes.id)

and 

(abonos.id_cobrador = id_cob)

and 

(abonos.id_prestamo = id_pres  or nombre like CONCAT('%',criterio,'%') or apellido like CONCAT('%',criterio,'%'))

and 

(abonos.fecha >= fecha1 and abonos.fecha <= fecha2)

and

(abonos.estado = estado);



END$$

DROP PROCEDURE IF EXISTS `buscar_abonos_aplicados_al_atraso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_abonos_aplicados_al_atraso` (IN `id_cob` INT, IN `id_pres` INT, IN `no_cuota` INT)  NO SQL
BEGIN





SELECT 

concat(clientes.nombre,' ',clientes.apellido)as nombre,clientes.id as id_cliente,abonos.* 

from 

prestamos,abonos,clientes 

where 

(abonos.id_prestamo = prestamos.id)

and 

(prestamos.id_cliente = clientes.id)

and 

(abonos.id_cobrador = id_cob)

and 

(abonos.id_prestamo = id_pres)

and 

(abonos.numero_cuota = no_cuota)

and

(abonos.estado = 'A');



END$$

DROP PROCEDURE IF EXISTS `buscar_adelantos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_adelantos` (IN `id_cob` INT, IN `fecha` DATE)  NO SQL
BEGIN



SELECT 

Concat(clientes.id,'-',prestamos.id) as codigo,

Concat(clientes.nombre,' ',clientes.apellido) as nombre,

recibos.*,temp.idPres,temp.recibos_en_adelantos

FROM

prestamos,recibos,clientes,

( SELECT recibos.id_prestamo as idPres , GROUP_CONCAT(recibos.numero_cuota) as recibos_en_adelantos

 from

 prestamos,recibos 

 where

 (prestamos.id_cobrador = id_cob)

 and 

 (recibos.id_prestamo = prestamos.id) 

 and

 (recibos.esta_en_adelanto = true)

 GROUP by recibos.id_prestamo) as temp

WHERE

 prestamos.id_cobrador = id_cob

 and

 (recibos.id_prestamo = prestamos.id)

 and 

 (recibos.esta_en_adelanto = true) 

 and

 (prestamos.id_cliente = clientes.id)

 and

 (temp.idPres = prestamos.id)

 and

 (recibos.fecha = fecha);

 





END$$

DROP PROCEDURE IF EXISTS `buscar_atrasos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_atrasos` (IN `id_cob` INT, IN `id_pres` INT, IN `criterio` VARCHAR(100), IN `fecha1` DATE, IN `fecha2` DATE, IN `estado` VARCHAR(2))  NO SQL
BEGIN





SELECT 

concat(clientes.nombre,' ',clientes.apellido)as nombre,clientes.id as id_cliente,atrasos.* 

from 

prestamos,atrasos,clientes 

where 

(atrasos.id_prestamo = prestamos.id)

and 

(prestamos.id_cliente = clientes.id)

and 

(atrasos.id_cobrador = id_cob)

and 

(atrasos.id_prestamo = id_pres  or nombre like CONCAT('%',criterio,'%') or apellido like CONCAT('%',criterio,'%'))

and 

(atrasos.fecha >= fecha1 and atrasos.fecha <= fecha2)

and

(atrasos.estado = estado);



END$$

DROP PROCEDURE IF EXISTS `buscar_atrasos_con_sus_abonos_y_balances`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_atrasos_con_sus_abonos_y_balances` (IN `id_pres` INT, IN `numero_de_cuotas` INT)  NO SQL
BEGIN





SELECT atrasos.numero_cuota,atrasos.monto as atrasos ,ifnull( abonito.total_abonos, 0) as abonos , (atrasos.monto - ifnull( abonito.total_abonos, 0) ) as balance

FROM

atrasos

left JOIN 

(SELECT abonos.numero_cuota as num,SUM(abonos.monto) as total_abonos FROM abonos WHERE abonos.id_prestamo = id_pres AND abonos.numero_cuota <= numero_de_cuotas AND abonos.estado = 'A' GROUP by abonos.numero_cuota) as abonito

on

atrasos.numero_cuota = abonito.num 

where

atrasos.id_prestamo = id_pres 

AND

atrasos.estado = 'A' 

AND

atrasos.numero_cuota<=numero_de_cuotas

ORDER BY atrasos.numero_cuota;





END$$

DROP PROCEDURE IF EXISTS `buscar_atrasos_de_este_prestamo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_atrasos_de_este_prestamo` (IN `id_pres` INT)  NO SQL
BEGIN





SELECT 

concat(clientes.nombre,' ',clientes.apellido)as nombre,clientes.id as id_cliente,atrasos.* 

from 

prestamos,atrasos,clientes 

where 

(atrasos.id_prestamo = prestamos.id)

and 

(prestamos.id_cliente = clientes.id)

and 

(atrasos.id_prestamo = id_pres)

and 

(atrasos.estado = 'A');



END$$

DROP PROCEDURE IF EXISTS `buscar_clientes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_clientes` (IN `criterio` VARCHAR(100), IN `id` INT)  NO SQL
SELECT clientes.*,zona.*

from

clientes,zona

where

(clientes.id_zona = zona.id)

 and

(clientes.id_zona>0)

and

(clientes.nombre like CONCAT(criterio,'%')

or

clientes.apellido like CONCAT(criterio,'%') 

or

clientes.cedula like CONCAT(criterio,'%')

or 

clientes.id = id )$$

DROP PROCEDURE IF EXISTS `buscar_clientes_dependiendo_zona`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_clientes_dependiendo_zona` (IN `criterio` VARCHAR(100), IN `id` INT, IN `id_zona` INT)  NO SQL
SELECT clientes.*,zona.*

from

clientes,zona

where

(clientes.id_zona = zona.id)

 and

(clientes.id_zona = id_zona)

and

(clientes.nombre like CONCAT(criterio,'%')

or

clientes.apellido like CONCAT(criterio,'%') 

or

clientes.cedula like CONCAT(criterio,'%')

or 

clientes.id = id )$$

DROP PROCEDURE IF EXISTS `buscar_cobrador_de_tu_configuracion`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_cobrador_de_tu_configuracion` (IN `criterio` VARCHAR(50), IN `id_cobrador` INT, IN `id_conf` INT)  NO SQL
BEGIN



SELECT

cobrador.*,zona.* 

from

cobrador,zona,usuarios 

where 

(cobrador.id_zona = zona.id ) 

and 

(cobrador.nombre like CONCAT(criterio,'%') or cobrador.id = id_cobrador ) 

and 

(usuarios.id = cobrador.id_usuario) 

and 

(usuarios.id_configuracion_prestamo = id_conf);



END$$

DROP PROCEDURE IF EXISTS `buscar_entregas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_entregas` (IN `id_cobrador` INT, IN `fecha` DATE)  NO SQL
BEGIN

SELECT *from entregas

where

entregas.id_cobrador = id_cobrador

AND

entregas.fecha = fecha;

END$$

DROP PROCEDURE IF EXISTS `buscar_lista_de_cobro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_lista_de_cobro` (IN `id_cob` INT, IN `fecha` DATE)  NO SQL
BEGIN



SELECT

recibos.*,prestamos.id_cliente,Concat(clientes.nombre,' ',clientes.apellido) as nombre

from

recibos,clientes,prestamos

where

(recibos.fecha = fecha and prestamos.id_cobrador = id_cob) 

AND

(recibos.id_prestamo = prestamos.id)

AND

(prestamos.id_cliente = clientes.id)

AND

(recibos.esta_en_adelanto = false)

order by recibos.numero_cuota DESC;



END$$

DROP PROCEDURE IF EXISTS `buscar_prestamos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_prestamos` (IN `criterio` VARCHAR(100), IN `id_cob` INT, IN `id_prestamo` INT, IN `fecha1` DATE, IN `fecha2` DATE, IN `estado` VARCHAR(3))  BEGIN



SELECT prestamos.*,clientes.*,zona.*

FROM

prestamos,clientes,zona

where 

(prestamos.id_cliente = clientes.id)

and 

(clientes.id_zona = zona.id)

and

(prestamos.id_cobrador = id_cob)

and 

(prestamos.id= id_prestamo OR

clientes.nombre like CONCAT(criterio,'%') OR

clientes.apellido like CONCAT(criterio,'%') OR

clientes.cedula like CONCAT(criterio,'%') )

and

(prestamos.estado = estado )

and

(prestamos.fecha_inicial>= fecha1 and prestamos.fecha_inicial <= fecha2 )

order by prestamos.fecha_inicial DESC;



END$$

DROP PROCEDURE IF EXISTS `buscar_recibos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_recibos` (IN `id_prestamo` INT(10))  NO SQL
BEGIN

select *from recibos where recibos.id_prestamo = id_prestamo;

END$$

DROP PROCEDURE IF EXISTS `buscar_recibos_para_imprimir`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_recibos_para_imprimir` (IN `id_cob` INT, IN `fecha` DATE)  NO SQL
BEGIN



SELECT 

clientes.id,

clientes.nombre,

clientes.apellido,

clientes.cedula,

clientes.direccion,

clientes.telefono1,

clientes.telefono2,

clientes.telefono3,

clientes.ocupacion,

clientes.recomendado,

prestamos.id as id_pres,

prestamos.fecha_inicial,

prestamos.fecha_termino,

prestamos.monto,

recibos.id as id_rec,

recibos.numero_cuota as no_cuota,

recibos.cuota,

recibos.balance,

cobrador.id as id_cobr,

cobrador.nombre as nomb_cobrador,

cobrador.telefono as tel_cob,

zona.nombre as nomb_zona,

zona.direccion as dir_zona,

zona.numero_telefonico as tel_zona,

IFNULL((SELECT SUM(atrasos.monto) from atrasos where atrasos.id_prestamo = prestamos.id and atrasos.numero_cuota <= recibos.numero_cuota and atrasos.estado = 'A'),0) as t_atrasos,

IFNULL((SELECT SUM(abonos.monto) from abonos WHERE abonos.id_prestamo = prestamos.id and abonos.numero_cuota <=recibos.numero_cuota and abonos.estado = 'A'),0)as t_abonos,

(SELECT SUM(recibos.cuota)from prestamos,cobrador ,recibos,usuarios, configuracion_prestamos where (usuarios.id_configuracion_prestamo = configuracion_prestamos.id) and (cobrador.id_usuario = usuarios.id) and (prestamos.id_cobrador = id_cob) and (prestamos.id_cobrador = cobrador.id) and (prestamos.estado = 'A') and (recibos.id_prestamo = prestamos.id) and (recibos.esta_en_adelanto = 0) /*con esta linea solo me salen los que terminan y no estan en adelantos, en un futuro si los quiero todo solo la borro*/ and (recibos.fecha = fecha and prestamos.fecha_termino = fecha) ORDER by recibos.numero_cuota) as terminantes

from 

prestamos,cobrador ,clientes,recibos,zona

where

(prestamos.id_cobrador = id_cob)

and

(prestamos.id_cobrador = zona.id)

and

(prestamos.id_cliente = clientes.id)

and

(prestamos.id_cobrador = cobrador.id)

and

(prestamos.estado = 'A')

and 

(recibos.id_prestamo = prestamos.id)

and 

(recibos.esta_en_adelanto = 0)

and

(recibos.fecha = fecha)

ORDER by recibos.numero_cuota;

END$$

DROP PROCEDURE IF EXISTS `buscar_recibos_y_atrasos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_recibos_y_atrasos` (IN `id_pres` INT)  NO SQL
BEGIN

select recibos.*,

CASE



	WHEN ISNULL(atrasos.monto)  THEN  "0.00"

    WHEN atrasos.estado = 'E' THEN "0.00"

    

    ELSE atrasos.monto

    

END



from

recibos

LEFT JOIN atrasos 

on

recibos.numero_cuota = atrasos.numero_cuota and atrasos.id_prestamo = id_pres

where 

recibos.id_prestamo = id_pres

AND

atrasos.estado = 'A'







ORDER by id;

END$$

DROP PROCEDURE IF EXISTS `ciclo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ciclo` (IN `valor` INT)  BEGIN



DECLARE x INT;



DECLARE string_value varchar(200);



set x = 1;

set string_value  = "";



while x <= valor DO



set string_value = concat(string_value , x , " , ");



set x = x + 1;





end WHILE;



SELECT string_value;

END$$

DROP PROCEDURE IF EXISTS `eliminar_abonos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_abonos` (IN `id_pres` INT, IN `no_cuota` INT)  NO SQL
BEGIN



UPDATE abonos set estado = 'E' where estado = 'A' and (id_prestamo = id_pres and numero_cuota = no_cuota);



END$$

DROP PROCEDURE IF EXISTS `eliminar_atrasos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_atrasos` (IN `id_pres` INT, IN `no_cuota` INT)  NO SQL
BEGIN



UPDATE atrasos set estado = 'E' where estado = 'A' and (id_prestamo = id_pres and numero_cuota = no_cuota);



CALL eliminar_todos_abonos_de_este_atraso(id_pres,no_cuota);



END$$

DROP PROCEDURE IF EXISTS `eliminar_prestamos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_prestamos` (IN `id_pres` INT, IN `id_user` INT)  BEGIN

UPDATE prestamos

set estado = 'E',

id_usuario = id_user

WHERE prestamos.id = id_pres;



DELETE from recibos where recibos.id_prestamo = id_pres;

END$$

DROP PROCEDURE IF EXISTS `eliminar_prestamos_bd`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_prestamos_bd` (IN `id_pres` INT)  BEGIN



DELETE from recibos where id_prestamo = id_pres;



DELETE from prestamos WHERE id = id_pres;



END$$

DROP PROCEDURE IF EXISTS `eliminar_todos_abonos_de_este_atraso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_todos_abonos_de_este_atraso` (IN `id_pres` INT, IN `no_cuota` INT)  NO SQL
BEGIN



UPDATE abonos set estado = 'E' where estado = 'A' and (id_prestamo = id_pres and numero_cuota = no_cuota);



END$$

DROP PROCEDURE IF EXISTS `insertar_abonos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_abonos` (IN `id_pres` INT, IN `num_cuota` INT, IN `monto` DOUBLE, IN `fecha` DATE, IN `id_usuario` INT, IN `id_cobrador` INT)  NO SQL
BEGIN

INSERT INTO 

abonos

VALUES

(null,id_pres,num_cuota,fecha,monto,'A',id_usuario,null,id_cobrador);

END$$

DROP PROCEDURE IF EXISTS `insertar_atrasos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_atrasos` (IN `id_pres` INT, IN `num_cuota` INT, IN `monto` DOUBLE, IN `fecha` DATE, IN `id_usuario` INT, IN `id_cobrador` INT)  NO SQL
BEGIN

INSERT INTO 

atrasos

VALUES

(null,id_pres,num_cuota,fecha,monto,'A',id_usuario,null,id_cobrador);

END$$

DROP PROCEDURE IF EXISTS `insertar_prestamos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_prestamos` (IN `f_inicial` DATE, IN `estado` VARCHAR(3) CHARSET ascii, IN `monto` INT, IN `id_cliente` INT, IN `id_cobrador` INT, IN `id_usuario` INT, IN `numero_de_cuotas` INT, IN `porciento` INT, IN `tipo_de_prestamo` INT)  BEGIN



DECLARE id int;





    IF tipo_de_prestamo = 0 THEN

    

    

	insert into

    prestamos
(fecha_inicial,fecha_termino,estado,monto,id_cliente,id_cobrador,id_usuario)
    values

    (f_inicial,DATE_ADD(f_inicial, INTERVAL numero_de_cuotas WEEK),estado,monto,id_cliente,id_cobrador,id_usuario);

    

    ELSEIF tipo_de_prestamo = 1 THEN

    

    

	insert into

    prestamos
(fecha_inicial,fecha_termino,estado,monto,id_cliente,id_cobrador,id_usuario)
    values

    (f_inicial,DATE_ADD(f_inicial, INTERVAL numero_de_cuotas DAY),estado,monto,id_cliente,id_cobrador,id_usuario);

    

    END IF;



set id  = LAST_INSERT_ID();



call insertar_recibos(id,numero_de_cuotas,f_inicial,monto,porciento,tipo_de_prestamo);



END$$

DROP PROCEDURE IF EXISTS `insertar_recibos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_recibos` (IN `id_prestamo` INT, IN `numero_de_cuotas` INT, IN `fecha` DATE, IN `monto` DOUBLE, IN `porciento` INT, IN `tipo_de_prestamo` INT)  NO SQL
BEGIN



DECLARE i int;

DECLARE balance_actual double;

DECLARE cuota_a_pagar double;



set i = 1;

set balance_actual = monto + monto*(porciento/100);

set cuota_a_pagar = balance_actual/numero_de_cuotas;





WHILE i < numero_de_cuotas + 1 DO



    set balance_actual = balance_actual - cuota_a_pagar;

    

    IF tipo_de_prestamo = 0 THEN

    

    INSERT into

    recibos
    (id_prestamo,numero_cuota,fecha,cuota,balance,esta_en_adelanto)

    VALUES

    (id_prestamo , i , DATE_ADD(fecha, INTERVAL i WEEK) , cuota_a_pagar , balance_actual,false);

    

    ELSEIF tipo_de_prestamo = 1 THEN

    

    INSERT into

    recibos 
(id_prestamo,numero_cuota,fecha,cuota,balance,esta_en_adelanto)
    VALUES

    (id_prestamo , i , DATE_ADD(fecha, INTERVAL i DAY) , cuota_a_pagar , balance_actual,false);

	

    END IF;





	set  i = i +1;



END WHILE;



END$$

DROP PROCEDURE IF EXISTS `poner_este_recibo_en_adenlanto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `poner_este_recibo_en_adenlanto` (IN `id_recibo` INT)  NO SQL
BEGIN



UPDATE recibos

set

recibos.esta_en_adelanto = true

WHERE

recibos.id = id_recibo;



END$$

DROP PROCEDURE IF EXISTS `prueba`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prueba` (IN `id` INT)  NO SQL
BEGIN



SELECT id;





END$$

DROP PROCEDURE IF EXISTS `quitar_todos_los_adelantos_de_recibos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `quitar_todos_los_adelantos_de_recibos` (IN `id_prestamo` INT)  NO SQL
BEGIN



UPDATE recibos set recibos.esta_en_adelanto = false where recibos.id_prestamo = id_prestamo;



END$$

DROP PROCEDURE IF EXISTS `validar_usuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `validar_usuario` (IN `usuario` VARCHAR(100), IN `contrasena` VARCHAR(100))  NO SQL
BEGIN



SELECT usuarios.*,configuracion_prestamos.* 

from

usuarios,configuracion_prestamos

where

usuarios.id_configuracion_prestamo = configuracion_prestamos.id

and

usuarios.usuario like binary usuario 

and

usuarios.contrasena like binary contrasena;



END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `abonos`
--

DROP TABLE IF EXISTS `abonos`;
CREATE TABLE `abonos` (
  `id` int(11) NOT NULL,
  `id_prestamo` int(11) NOT NULL,
  `numero_cuota` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  `estado` enum('A','E') NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `ultima_mod` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_cobrador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `atrasos`
--

DROP TABLE IF EXISTS `atrasos`;
CREATE TABLE `atrasos` (
  `id` int(11) NOT NULL,
  `id_prestamo` int(11) NOT NULL,
  `numero_cuota` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  `estado` enum('A','E') NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `ultima_mod` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_cobrador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `cedula` varchar(80) NOT NULL,
  `direccion` varchar(80) NOT NULL,
  `telefono1` varchar(80) DEFAULT NULL,
  `telefono2` varchar(80) DEFAULT NULL,
  `telefono3` varchar(80) DEFAULT NULL,
  `ocupacion` varchar(80) NOT NULL,
  `recomendado` varchar(80) DEFAULT NULL,
  `fecha_ingreso` date NOT NULL,
  `id_zona` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `cedula`, `direccion`, `telefono1`, `telefono2`, `telefono3`, `ocupacion`, `recomendado`, `fecha_ingreso`, `id_zona`, `id_usuario`) VALUES
(8, 'JUAN', 'LOPEZ', '342020202', 'NAGUA, SILFA CASTILLO', '444-444-4444', '343-223-3342', '', 'VENDE EMPANADAS', 'POR JUAN', '2022-01-23', 1, 1),
(9, 'CARLOS', 'ALCANTARA', '933920230', 'NAGUA EL FACTOR', '322-343-3423', '353-455-5322', '393-334-5533', 'MOTOCONCHO', 'POR JUANA', '2022-01-23', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cobrador`
--

DROP TABLE IF EXISTS `cobrador`;
CREATE TABLE `cobrador` (
  `id` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `apellido` varchar(80) DEFAULT NULL,
  `telefono` varchar(80) NOT NULL,
  `id_zona` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cobrador`
--

INSERT INTO `cobrador` (`id`, `nombre`, `apellido`, `telefono`, `id_zona`, `id_usuario`) VALUES
(1, 'Berlis', 'Rodriguez', '888-888-8888', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `configuracion_prestamos`
--

DROP TABLE IF EXISTS `configuracion_prestamos`;
CREATE TABLE `configuracion_prestamos` (
  `id` int(11) NOT NULL,
  `nombre_configuracion` varchar(50) NOT NULL,
  `nombre_compania` varchar(50) NOT NULL,
  `numero_de_cuotas` int(2) NOT NULL,
  `modalidad` varchar(50) NOT NULL,
  `porciento_beneficio` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `configuracion_prestamos`
--

INSERT INTO `configuracion_prestamos` (`id`, `nombre_configuracion`, `nombre_compania`, `numero_de_cuotas`, `modalidad`, `porciento_beneficio`) VALUES
(1, 'Prestamos a 13 semanas.', 'Inversiones BERLIS', 13, 'Semanal', 30);

-- --------------------------------------------------------

--
-- Stand-in structure for view `entregas`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `entregas`;
CREATE TABLE `entregas` (
`id_cobrador` int(11)
,`id_prestamo` int(11)
,`fecha` date
,`Codigo` varchar(23)
,`total_a_pagar` double
,`cuota` double
,`balance` double
,`monto_prestado` double
,`nombre` varchar(161)
);

-- --------------------------------------------------------

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
CREATE TABLE `prestamos` (
  `id` int(11) NOT NULL,
  `fecha_inicial` date NOT NULL,
  `fecha_termino` date NOT NULL,
  `estado` enum('A','T','E') NOT NULL,
  `monto` double NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_cobrador` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `ultima_modificacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prestamos`
--

INSERT INTO `prestamos` (`id`, `fecha_inicial`, `fecha_termino`, `estado`, `monto`, `id_cliente`, `id_cobrador`, `id_usuario`, `ultima_modificacion`) VALUES
(30, '2022-01-24', '2022-04-25', 'A', 10000, 8, 1, 1, '2022-01-24 02:47:58'),
(31, '2022-01-24', '2022-04-25', 'A', 20000, 9, 1, 1, '2022-01-24 02:48:33'),
(32, '2022-01-24', '2022-04-25', 'E', 4000, 8, 1, 1, '2022-01-24 02:52:27');

-- --------------------------------------------------------

--
-- Table structure for table `prueba`
--

DROP TABLE IF EXISTS `prueba`;
CREATE TABLE `prueba` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `ultima_mod` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in structure for view `recibito`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `recibito`;
CREATE TABLE `recibito` (
`id_prestamo` int(11)
,`cuota` double
,`balance` double
,`MAX(numero_cuota)` int(2)
);

-- --------------------------------------------------------

--
-- Table structure for table `recibos`
--

DROP TABLE IF EXISTS `recibos`;
CREATE TABLE `recibos` (
  `id` int(11) NOT NULL,
  `id_prestamo` int(11) NOT NULL,
  `numero_cuota` int(2) NOT NULL,
  `fecha` date NOT NULL,
  `cuota` double NOT NULL,
  `balance` double NOT NULL,
  `esta_en_adelanto` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recibos`
--

INSERT INTO `recibos` (`id`, `id_prestamo`, `numero_cuota`, `fecha`, `cuota`, `balance`, `esta_en_adelanto`) VALUES
(272, 30, 1, '2022-01-31', 1000, 12000, 0),
(273, 30, 2, '2022-02-07', 1000, 11000, 0),
(274, 30, 3, '2022-02-14', 1000, 10000, 0),
(275, 30, 4, '2022-02-21', 1000, 9000, 0),
(276, 30, 5, '2022-02-28', 1000, 8000, 0),
(277, 30, 6, '2022-03-07', 1000, 7000, 0),
(278, 30, 7, '2022-03-14', 1000, 6000, 0),
(279, 30, 8, '2022-03-21', 1000, 5000, 0),
(280, 30, 9, '2022-03-28', 1000, 4000, 0),
(281, 30, 10, '2022-04-04', 1000, 3000, 0),
(282, 30, 11, '2022-04-11', 1000, 2000, 0),
(283, 30, 12, '2022-04-18', 1000, 1000, 0),
(284, 30, 13, '2022-04-25', 1000, 0, 0),
(285, 31, 1, '2022-01-31', 2000, 24000, 0),
(286, 31, 2, '2022-02-07', 2000, 22000, 0),
(287, 31, 3, '2022-02-14', 2000, 20000, 0),
(288, 31, 4, '2022-02-21', 2000, 18000, 0),
(289, 31, 5, '2022-02-28', 2000, 16000, 0),
(290, 31, 6, '2022-03-07', 2000, 14000, 0),
(291, 31, 7, '2022-03-14', 2000, 12000, 0),
(292, 31, 8, '2022-03-21', 2000, 10000, 0),
(293, 31, 9, '2022-03-28', 2000, 8000, 0),
(294, 31, 10, '2022-04-04', 2000, 6000, 0),
(295, 31, 11, '2022-04-11', 2000, 4000, 0),
(296, 31, 12, '2022-04-18', 2000, 2000, 0),
(297, 31, 13, '2022-04-25', 2000, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(80) NOT NULL,
  `contrasena` varchar(80) NOT NULL,
  `id_configuracion_prestamo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `contrasena`, `id_configuracion_prestamo`) VALUES
(1, 'Berlis', '1234', 1),
(2, 'Admin', 'Admin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `zona`
--

DROP TABLE IF EXISTS `zona`;
CREATE TABLE `zona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `direccion` varchar(80) NOT NULL,
  `numero_telefonico` varchar(80) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `zona`
--

INSERT INTO `zona` (`id`, `nombre`, `direccion`, `numero_telefonico`, `id_usuario`) VALUES
(1, 'Nagua', 'Nagua - Centro Ciudad', '555-555-5555', 1);

-- --------------------------------------------------------

--
-- Structure for view `entregas`
--
DROP TABLE IF EXISTS `entregas`;

DROP VIEW IF EXISTS `entregas`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `entregas`  AS SELECT `prestamos`.`id_cobrador` AS `id_cobrador`, `recibito`.`id_prestamo` AS `id_prestamo`, `prestamos`.`fecha_inicial` AS `fecha`, concat(`clientes`.`id`,'-',`prestamos`.`id`) AS `Codigo`, `recibito`.`balance`+ `recibito`.`cuota` AS `total_a_pagar`, `recibito`.`cuota` AS `cuota`, `recibito`.`balance` AS `balance`, `prestamos`.`monto` AS `monto_prestado`, concat(`clientes`.`nombre`,' ',`clientes`.`apellido`) AS `nombre` FROM ((`recibito` join `clientes`) join `prestamos`) WHERE `clientes`.`id` = `prestamos`.`id_cliente` AND `prestamos`.`estado` = 'A' AND `prestamos`.`id` = `recibito`.`id_prestamo` ORDER BY `prestamos`.`fecha_inicial` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `recibito`
--
DROP TABLE IF EXISTS `recibito`;

DROP VIEW IF EXISTS `recibito`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recibito`  AS SELECT `recibos`.`id_prestamo` AS `id_prestamo`, `recibos`.`cuota` AS `cuota`, `recibos`.`balance` AS `balance`, max(`recibos`.`numero_cuota`) AS `MAX(numero_cuota)` FROM `recibos` GROUP BY `recibos`.`id_prestamo` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abonos`
--
ALTER TABLE `abonos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `atrasos`
--
ALTER TABLE `atrasos`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_cobrador` (`id_cobrador`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_zona` (`id_zona`);

--
-- Indexes for table `cobrador`
--
ALTER TABLE `cobrador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `id_zona` (`id_zona`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indexes for table `configuracion_prestamos`
--
ALTER TABLE `configuracion_prestamos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_cobrador` (`id_cobrador`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indexes for table `prueba`
--
ALTER TABLE `prueba`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recibos`
--
ALTER TABLE `recibos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_prestamo` (`id_prestamo`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `id_configuracion_prestamo` (`id_configuracion_prestamo`);

--
-- Indexes for table `zona`
--
ALTER TABLE `zona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abonos`
--
ALTER TABLE `abonos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `atrasos`
--
ALTER TABLE `atrasos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `cobrador`
--
ALTER TABLE `cobrador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `configuracion_prestamos`
--
ALTER TABLE `configuracion_prestamos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `prueba`
--
ALTER TABLE `prueba`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `recibos`
--
ALTER TABLE `recibos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=311;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `zona`
--
ALTER TABLE `zona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `atrasos`
--
ALTER TABLE `atrasos`
  ADD CONSTRAINT `atrasos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `atrasos_ibfk_2` FOREIGN KEY (`id_cobrador`) REFERENCES `cobrador` (`id`);

--
-- Constraints for table `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `clientes_ibfk_3` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id`);

--
-- Constraints for table `cobrador`
--
ALTER TABLE `cobrador`
  ADD CONSTRAINT `cobrador_ibfk_2` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id`),
  ADD CONSTRAINT `cobrador_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

--
-- Constraints for table `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_cobrador`) REFERENCES `cobrador` (`id`),
  ADD CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

--
-- Constraints for table `recibos`
--
ALTER TABLE `recibos`
  ADD CONSTRAINT `recibos_ibfk_1` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamos` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_configuracion_prestamo`) REFERENCES `configuracion_prestamos` (`id`);

--
-- Constraints for table `zona`
--
ALTER TABLE `zona`
  ADD CONSTRAINT `zona_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
