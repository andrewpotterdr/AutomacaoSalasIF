<?php
$user = $_POST['login'];
$pass = $_POST['senha'];
$con = new PDO("mysql:host=127.0.0.1;dbname=Salutaris", "root","root");
$sql = 'select * from dados_cadastro';
$res = $con->query($sql);
$res = $res->fetchAll(PDO::FETCH_ASSOC);
$res = json_encode($res);
$array = json_decode($res);

$valido = 0;

foreach($array as $dados)
{
	if($dados->login == $user)
	{
		if($dados->senha == $pass)
		{
			$valido = 1;
		}
	}
}

if($valido == 1)
{
	header('Location: ../painel.html');
}
else
{
	echo "Login e/ou Senha Incorretos!";
}
?>