<?php
	$maquinas = shell_exec('java -jar ../java/Servidor-Socket.jar l-51138');
	$rows = explode("\n", $maquinas);
	$result = [];
	$machine = 0;
	foreach($rows as $row){
		$column = explode(': ', $row);
		if($column[0] != 'Máquina' && $column[0] != 'Status' && $column[0] != ''){
			$result[$machine] = $result[$machine] ?? [];
			$result[$machine][$column[0]] = $column[1];	
		}
		if($column[0] == 'Status')
				$machine++;
	}
	//var_dump($result);


	echo json_encode($result);
?>
