package br.com.lucas.pharma.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.lucas.pharma.dao.EstadoDAO;
import br.com.lucas.pharma.domain.Estado;

@Path("estado")
public class EstadoService {

	// http://127.0.0.1:8080/Pharma/rest/estado // url que será utilizada para receber o GET
	@GET
	public String listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estados = estadoDAO.listar("nome");

		Gson gson = new Gson();
		String json = gson.toJson(estados); // Pega o List fabricantes em formato objeto e converte no formato texto (GSon) e o retorna.

		return json;

		// Para gson o retorno é sempre String
	}		
}