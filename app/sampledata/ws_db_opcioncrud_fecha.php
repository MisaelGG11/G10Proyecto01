<?php
$year=$_REQUEST['year'];
$month=$_REQUEST['month'];
$day=$_REQUEST['day'];

$idopcion=$_REQUEST['id_opcion'];
$desopcion=$_REQUEST['des_opcion'];

$servidor="localhost";
$usuario="root";
$baseDatos="grupo10";
$password="";

$conexion=mysql_connect($servidor,$usuario,$password) or die ("Problemas en la conexion");

mysql_select_db($baseDatos,$conexion) or die("Problemas en la seleccion de la base de datos");

$registros=mysql_query(
    "Select * from OPCION_CRUD2 where fecha_modificado > '".$year."-".$month."-".$day."'",$conexion) or die("Problemas en el select:".mysql_error());

$filas=array();

while ($reg=mysql_fetch_assoc($registros)) {
    $filas[]=$reg;
}

echo json_encode($filas);

mysql_close($conexion);
?>