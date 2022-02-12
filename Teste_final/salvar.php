<?php
	
	include('conexao.php');

	//Capiturando os dados do NodeMCU
	$vaga1 = $_GET['vaga1'];
	$vaga2 = $_GET['vaga2'];
	$vaga3 = $_GET['vaga3'];
	$id    = $_GET['id'];
	


	//montando a query
	$sql = "UPDATE tbvagas SET vaga1=:vaga1, vaga2=:vaga2, vaga3=:vaga3 WHERE id=:id";

	$stmt = $PDO->prepare($sql);

	$stmt->bindValue(':vaga1', $vaga1);
	$stmt->bindValue(':vaga2', $vaga2);
	$stmt->bindValue(':vaga3', $vaga3);
	$stmt->bindValue(':id', $id);


	if($stmt->execute()){
		echo "Salvo_com_sucesso";

	}
	else{
		echo "Erro_ao_salvar";
	}
?>