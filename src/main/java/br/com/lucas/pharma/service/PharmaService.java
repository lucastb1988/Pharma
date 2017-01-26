package br.com.lucas.pharma.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://localhost:8080/Pharma/rest/pharma
//rest foi informado na configuração do Restful (Classe PharmaResourceConfig)
//pharma foi informado abaixo no Path [Repositório de Serviços]
@Path("pharma")
public class PharmaService {

	@GET // (GET é o tipo do método/protocolo) - GET serve para capturar o texto informado e jogar na url / 
	//ao entrar na url http://localhost:8080/Pharma/rest/pharma será exibido o texto abaixo //para verificar a url criada clique em f12
	public String exibir() {
		return "Curso de Java";
	}
}

//Service ou Serviço é um método (é comum devolver respostas / enviar parâmetros depende da situação)
//É comum utilizar protocolos de GET, POST, PUT, DELETE
//Para acesso a URL é comum utilizar o protocolo GET, para acesso aos demais protocolos é obrigatório utilizar addons do navegador ou SoapUI(profissional)
//GET utiliza comunicação navegador com navegador sem o sistema operacional intermediar (comunicação mais rápida)
//POST utiliza comunicação usando serviços do sistema operacional (comunicação mais lenta porém mais segura)