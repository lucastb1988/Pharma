package br.com.lucas.pharma.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.lucas.pharma.dao.ProdutoDAO;
import br.com.lucas.pharma.domain.Produto;

@Path("produto")
public class ProdutoService {

	// http://127.0.0.1:8080/Pharma/rest/produto
	@GET // método para listar todos os produtos
	public String listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar("descricao");

		Gson gson = new Gson();
		String json = gson.toJson(produtos);

		return json;		
	}

	// http://127.0.0.1:8080/Pharma/rest/produto
	@POST // método para salvar ou editar
	public String salvar(String json) { // Parâmetro a salvar
		Gson gson = new Gson();
		Produto produto = gson.fromJson(json, Produto.class); // irá converter o formato json em um produto

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.merge(produto); // após esse processo do merge, o mesmo já possui um código, 
		// sendo assim já é possivel dar o feedback para o usuario sobre o objeto salvo usando jsonSaida abaixo

		String jsonSaida = gson.toJson(produto); // converte o json em formato texto para dar o feedback ao usuario
		return jsonSaida;		
	}	
}