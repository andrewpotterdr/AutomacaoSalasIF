class Maquinas
{
	render()
	{
		const maquinasUrl = `http://localhost/site/php/maquinas.php`
		const Maquinas = document.querySelector('section')
		fetch(maquinasUrl)
			.then(res => res.json())
			.then(maquinas => {
				let html = ''
				let nome = ''
				let MAC = ''
				let IP = ''
				let i = 0
				for(var maquina of maquinas)
				{
					nome = maquina.Nome
					MAC = maquina.MAC
					IP = maquina.IP
					html += `<div class="row">`
					if((i+1)%3==0)
					{
						html += `<div class="col-md-4"><form action=""><input type="checkbox" name="Tudo" value="Tudo"><h5>${nome}</h5><div class="teste"><img src="imgs/teste${i}.jpg"></div></form><table><thead><th><h3>Informações do Cliente</h3></th></thead><tbody><tr><td>Nome:</td><td>${nome}</td></tr><tr><td>MAC:</td><td>${MAC}</td></tr><tr><td>IP:</td><td>${IP}</td></tr></tbody></table></div></div>`
						html += `<div class="row">`
					}
					else
					{
						html += `<div class="col-md-4"><form action=""><input type="checkbox" name="Tudo" value="Tudo"><h5>${nome}</h5><div class="teste"><img src="imgs/teste${i}.jpg"></div></form><table><thead><th><h3>Informações do Cliente</h3></th></thead><tbody><tr><td>Nome:</td><td>${nome}</td></tr><tr><td>MAC:</td><td>${MAC}</td></tr><tr><td>IP:</td><td>${IP}</td></tr></tbody></table></div>`
					}
					i++
				}
				Maquinas.innerHTML = html
			})
	}
}

let Maq = new Maquinas()
Maq.render()