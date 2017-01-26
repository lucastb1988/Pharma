package br.com.lucas.pharma.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.lucas.pharma.dao.FabricanteDAO;
import br.com.lucas.pharma.domain.Fabricante;

// Json é uma forma textual para representar os campos porém utiliza menos espaço que o xml(outra forma de representação)
// Json - JavaScript Object Notation - formato para comunicação de dados (utilizado principalmente em android, mobile devido ser mais leve)
// muito mais leve que XML, notação para trafegar objetos, não possui abertura de tag e fechamento, trabalha apenas com pares de valores, arrays e objetos.
// possui menos regras, menos tags e é mais facil de visualizar do que o xml. Json é uma representação do Rest.

// Rest é uma filosofia de construção de servidores de aplicação que utiliza essencialmente protocolo HTTP para expor seus recursos.
// Esses recursos podem ser objetos, arrays, listas, valores simples, esses recursos são representados por URL.
// É necessário verificar quais uris serão publicadas, quais uris farão cada coisa, de que forma as pessoas irão trocar informações com vc, como as pessoas irão interagir com seus recursos, 
// todos esses processos são parte da arquitetura do Rest, devido a isso necessita pensar muito bem em como desenvolver estes processos, 
// para que a pessoa que for acessar simplesmente possa fazer tudo que necessita usando apenas os protocolos GET, POST, PUT, DELETE

// Da mesma forma que é criado um formulário através de um site da web, informando campos obrigatórios e enviando o mesmo. O Rest também faz da mesma forma através de sua API.
// Estratégia de cache HTTP é imprescindivel para que o Rest tenha uma boa performance.
// A idéia do Rest é de um sistema falar com o outro de uma maneira desacoplada.
// Rest é uma arquitetura de longo prazo. É implementada para que seu sistema dure no minimo 5 anos.

// Rest é o conceito
// Restful é a implementação do conceito
@Path("fabricante")
public class FabricanteService {

	//Hibernate não trabalha com Json e sim com objetos (devido a isso é necessário converter todos os objetos a serem utilizados por Gson, 
	//para os métodos abaixo listar/buscar/salvar/editar/excluir foram convertidos os objetos(fabricante) para Gson)

	//Gson = Google gSon (Biblioteca da Google)	
	//Gson consegue gerar objetos a partir de uma String JSON.
	//Gson é uma biblioteca que converte objetos Java em JSON e vice-versa.
	//Serializar(Java para JSON) / deserializar(JSON para JAVA).
	//Gson trabalha com o tipo String, tipos primitivos (int, float, boolean, etc) e arrays. Também suporta classes wrappers como Integer e Long.
	//GSON é uma biblioteca do Google utilizada, entre outras coisas, na conversão de objetos Java em representação JSON.
	
	// http://127.0.0.1:8080/Pharma/rest/fabricante // url que será utilizada para receber o GET
	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.listar("descricao");

		Gson gson = new Gson();
		String json = gson.toJson(fabricantes); // Pega o List fabricantes em formato objeto e converte no formato texto (GSon) e o retorna.

		return json;

		// Para gson o retorno é sempre String
	}		

	// http://127.0.0.1:8080/Pharma/rest/fabricante/(codigo} (ex. código 10) // url que será utilizada para receber o GET

	// Este método buscar foi utilizado a anotação Path/PathParam para distinguir somente a URL (se fossem URLs diferentes não seria necessário incluir estas anotações)
	@GET // utilizado para capturar informações (leitura)
	@Path("{codigo}") // está diferenciando a URL com um outro nivel do Path (utilizando /código do fabricante)	
	public String buscar(@PathParam("codigo") Long codigo) { //amarrando o objeto código ao parâmetro código
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		Gson gson = new Gson();
		String json = gson.toJson(fabricante);

		return json;		
	}

	// http://127.0.0.1:8080/Pharma/rest/fabricante

	// Este método (POST merge) também pode ser utilizado para editar (necessário incluir o id e editar a nova descrição)
	@POST // utilizado para incluir/inserir
	public String salvarOuEditar(String jsonEntrada) {
		Gson gson = new Gson(); // objeto Gson pode converter objeto para String, como String para objeto
		Fabricante fabricante = gson.fromJson(jsonEntrada, Fabricante.class); // converter seu json para objeto do tipo fabricante  na classe Fabricante / 
		// capturando para Gson de Json o parametro e a classe no qual o Json irá converter(fabricante.class)

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante); //editar ou salvar dependendo se já existe um fabricante criado

		String jsonSaida = gson.toJson(fabricante); // dar o feedback para o usuario (Evento 200)		
		return jsonSaida;
	}

	// http://127.0.0.1:8080/Pharma/rest/fabricante
	// Este método (POST salvar) é utilizado somente para salvar
	//	@POST // utilizado para incluir/inserir
	//	public String salvar(String jsonEntrada) {
	//		Gson gson = new Gson(); // objeto Gson pode converter objeto para String, como String para objeto
	//		Fabricante fabricante = gson.fromJson(jsonEntrada, Fabricante.class); // converter seu json para objeto do tipo fabricante na classe Fabricante / 
	//		// capturando para Gson de Json o parametro e a classe no qual o Json irá converter(fabricante.class)
	//
	//		FabricanteDAO fabricanteDAO = new FabricanteDAO();
	//		fabricanteDAO.salvar(fabricante); // salvar um fabricante no bd
	//
	//		String jsonSaida = gson.toJson(fabricante); // dar o feedback para o usuario (Evento 200)		
	//		return jsonSaida;
	//	}

	// http://127.0.0.1:8080/Pharma/rest/fabricante
	// Este método (PUT editar) é utilizado somente para editar (necessário incluir o id e editar a nova descrição)
	@PUT // utilizado para editar
	public String editar(String jsonEntrada) {
		Gson gson = new Gson(); // objeto Gson pode converter objeto para String, como String para objeto
		Fabricante fabricante = gson.fromJson(jsonEntrada, Fabricante.class); // converter seu json para objeto do tipo fabricante na classe Fabricante / 
		// capturando para Gson de Json o parametro e a classe no qual o Json irá converter(fabricante.class)

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.editar(fabricante); // editar um fabricante no bd

		String jsonSaida = gson.toJson(fabricante); // dar o feedback para o usuario (Evento 200)		
		return jsonSaida;
	}	

	// http://127.0.0.1:8080/Pharma/rest/fabricante
	@DELETE // utilizado para deletar
	@Path("{codigo}")
	public String excluir(@PathParam("codigo") Long codigo) {		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		fabricanteDAO.excluir(fabricante); // excluir um fabricante do bd

		Gson gson = new Gson();
		String jsonSaida = gson.toJson(fabricante); // dar o feedback para o usuario (Evento 200)		
		return jsonSaida;
	}
}

//// Gson = Uma biblioteca de serialização/deserialização que pode converter Objetos Java Gson em representação Json.
//// Também pode ser usada para converter uma String JSon em um objeto Java equivalente.
//// GSon pode trabalhar com objetos Java arbitrários incluindo objetos pré-existentes no qual você não possui o código-fonte dele.	
//// É comum utilizar protocolos de GET, POST, PUT, DELETE
//// Para acesso a URL é comum utilizar o protocolo GET, para acesso aos demais protocolos é obrigatório utilizar addons do navegador ou SoapUI(profissional)
//// GET utiliza comunicação navegador com navegador sem o SO intermediar (comunicação mais rápida)
//// POST utiliza comunicação usando serviços do sistema operacional (comunicação mais lenta porém mais segura) - serve para inserir informações
