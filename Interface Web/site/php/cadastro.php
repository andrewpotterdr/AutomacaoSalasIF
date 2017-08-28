<?php
$user = $_POST['username'];
$pass = $_POST['senha'];
$email = $_POST['email'];
$pass2 = $_POST['confirmar'];

if($pass != $pass2)
{
	echo "As Senhas Não Coincidem!";
}
else
{
	$con = new PDO("mysql:host=127.0.0.1;dbname=Salutaris", "root","root");
$sql = "insert dados_cadastro (login,senha,email) values('{$user}','{$pass}','{$email}')";
$con->exec($sql);
	echo "Usuário Cadastrado Com Sucesso!";
	header('Location: ../index.html');
}
?>