<?php

$id_localidad=$_REQUEST['id_localidad'];
$edificio_localidad=$_REQUEST['edificio_localidad'];
$nombre_localidad=$_REQUEST['nombre_localidad'];
$capacidad_localidad=$_REQUEST['capacidad_localidad'];
$fecha_modificacion_localidad=$_REQUEST['fecha_modificacion_localidad'];

$servidor="localhost";
$usuario="root";
$baseDatos="grupo10";
$password="";

$respuesta=array('resultado'=>0);
json_encode($respuesta);

$conexion=mysql_connect($servidor,$usuario,$password) or die ("Problemas en la conexion");

mysql_select_db($baseDatos,$conexion) or die("Problemas en la seleccion de la base de datos");


$query = "INSERT INTO localidad2 VALUES('".$id_localidad."','".$edificio_localidad."','".$nombre_localidad."',".$capacidad_localidad.",".$fecha_modificacion_localidad.");";

$resultado = mysql_query($query) or die(mysql_error());

//Si la respuesta es correcta enviamos 1 y sino enviamos 0
if(mysql_affected_rows() == 1)
    $respuesta=array('resultado'=>1);

echo json_encode($respuesta);

mysql_close($conexion);
?>
