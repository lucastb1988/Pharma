package br.com.lucas.pharma.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.lucas.pharma.dao.CidadeDAO;
import br.com.lucas.pharma.domain.Cidade;

@Path("cidade")
public class CidadeService {

	// http://127.0.0.1:8080/Pharma/rest/cidade // url que será utilizada para receber o GET
	@GET
	@Path("{estadoCodigo}")
	public String buscarPorEstado(@PathParam("estadoCodigo") Long estadoCodigo) {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.buscarPorEstado(estadoCodigo);

		Gson gson = new Gson();
		String json = gson.toJson(cidades); // Pega o List fabricantes em formato objeto e converte no formato texto (GSon) e o retorna.

		return json;

		// Para gson o retorno é sempre String
	}		
}
