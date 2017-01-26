package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.CidadeDAO;
import br.com.lucas.pharma.dao.EstadoDAO;
import br.com.lucas.pharma.domain.Cidade;
import br.com.lucas.pharma.domain.Estado;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Cidade.
 * 
 */

//Servlets é uma classe java com pedaços de html
//JSP é um monte de html com pedaçõs de java
//Container(Servidor de Aplicações) é quem gerencia componentes(no caso da especificação JavaEE gerencia os Servlets, JSP, JSF) oferece varios serviços de infraestrutura
//Request, View, Session, Application são escopos que definem o ciclo de vida do managed bean, quanto tempo o seu ciclo de vida fica armazenado na página, (Request - quando gera uma response descarta o managed bean)
/*A diferença entre Stateful e Stateless é que uma vai guardar o estado dos objetos(Stateful) e o outro vai reconhecer cada requisição como uma requisição nova(Stateless).
Stateless: 
	não mantém estado conversacional com o cliente
    nenhum registro de todas as interações anteriores são salvos.
    cada interação é tratada com base nas informações disponíveis para a interação
Exemplo: Protocolos de Internet (IP), Protocolo de transmissão Hyper Text.
Vantagens:
    Redução o uso de memória no servidor.
    reduz problemas de sessão expirada
Desvantagens:
    Mais difícil de manter a interação do usuário e criar uma aplicação web sem emenda
    Pode exigir informações extras a serem enviados para e de cliente*/


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	
	private List<Estado> estados;

	/**
	 * Método para listar a entidade Cidade na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar("nome");
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as cidades");
		}
	}

	/**
	 * Método para forçar o recarregamento das entidades Cidade e Estado na tela ao salvar uma cidade.  
	 */
	public void novo() {
		try {
			cidade = new Cidade();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome"); // força o recarregamento dos ex.estados na página passando a variável "nome" para ordenação dos campos no SelectOneMenu/ComboBox nos xhtmls

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova cidade.");
		}
	}

	/**
	 * Método para salvar uma cidade no banco de dados.  
	 */
	public void salvar() {
		try {
			CidadeDAO cidadedao = new CidadeDAO();
			cidadedao.merge(cidade); // salvar(merge) cidade utilizando DAO  	
			
			cidades = cidadedao.listar("nome"); // força o recarregamento das ex.cidades na página 

			novo();	// limpa a tela e força o recarregamento dos ex.estados na página				

			Messages.addGlobalInfo("Cidade salva com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao salvar a cidade");
		}
	}

	/**
	 * Método para excluir uma cidade no banco de dados.  
	 */
	public void excluir(ActionEvent evento) { // Parâmetro para capturar o que foi enviado através da cidadeSelecionada no xhtml 
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada"); // Evento para recuperar o objeto cidadeSelecionada no xhtml 

			CidadeDAO cidadedao = new CidadeDAO();
			cidadedao.excluir(cidade); // excluir cidade utilizando DAO

			cidades = cidadedao.listar("nome"); // força o recarregamento das ex.cidades na página

			Messages.addGlobalInfo("Cidade removida com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao excluir uma cidade.");
		}
	}

	/**
	 * Método para editar uma cidade no banco de dados.  
	 */
	public void editar(ActionEvent evento) {			
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada"); // Evento para recuperar o objeto cidadeSelecionada no xhtml 

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome"); // força o recarregamento dos ex.estados na página / passando a variável "nome" para ordenação dos campos no SelectOneMenu/ComboBox nos xhtmls

			Messages.addGlobalInfo("Cidade editada com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar editar uma cidade.");
		}
	}

	/**
	 * @return the cidades
	 */
	public List<Cidade> getCidades() {
		return cidades;
	}

	/**
	 * @param cidades the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estados
	 */
	public List<Estado> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}		
}