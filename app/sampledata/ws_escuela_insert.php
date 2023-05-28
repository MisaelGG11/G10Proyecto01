<?php
$id_escuela=$_REQUEST['id_escuela'];
$acronimo=$_REQUEST['acronimo'];
$nombre=$_REQUEST['nombre'];
$fecha_modificado=$_REQUEST['fecha_modificado'];

$servidor="localhost";
$usuario="root";
$baseDatos="grupo10";
$password="";

$respuesta=array('resultado'=>0);
json_encode($respuesta);

$conexion=mysql_connect($servidor,$usuario,$password) or die ("Problemas en la conexion");

mysql_select_db($baseDatos,$conexion) or die("Problemas en la seleccion de la base de datos");

$query = "INSERT INTO escuela2 VALUES('".$id_escuela."','".$acronimo."','".$nombre."',".$fecha_modificado.");";

$resultado = mysql_query($query) or die(mysql_error());

//Si la respuesta es correcta enviamos 1 y sino enviamos 0
if(mysql_affected_rows() == 1)
    $respuesta=array('resultado'=>1);

echo json_encode($respuesta);

mysql_close($conexion);
?>
