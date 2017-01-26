package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.FabricanteDAO;
import br.com.lucas.pharma.dao.ProdutoDAO;
import br.com.lucas.pharma.domain.Fabricante;
import br.com.lucas.pharma.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class Produto2Bean implements Serializable {
	private Produto produto;
	private Long codigoProduto; // trafegar com a informação entre as páginas - a tela de listagem envia o códigoProduto para a tela de edição - trafega com a chave primária entre páginas

	private List<Fabricante> fabricantes;
	private List<Produto> produtos;

	private FabricanteDAO fabricanteDAO; // não é necessário mais dar new em todos os métodos/instanciar objetos com esta variável criada	
	private ProdutoDAO produtoDAO; // não é necessário mais dar new em todos os métodos/instanciar objetos com esta variável criada	

	@PostConstruct // ideal para PostConstruct é dar new nos métodos // popular e recuperar dados do banco não é bom fazer mais no PostConstruct
	public void iniciar() {
		fabricanteDAO = new FabricanteDAO(); // instancia todos os métodos somente com este criado, não é necessário criar varios new/instanciamento
		produtoDAO = new ProdutoDAO(); // instancia todos os métodos somente com este criado, não é necessário criar varios new/instanciamento
	}

	public void listar() {
		try {			
			produtos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao listar os produtos");
		}
	}

	public void carregarEdicao() {
		try {
			produto = produtoDAO.buscar(codigoProduto); // pesquisar o produto passando o codigoProduto
			
			fabricantes = fabricanteDAO.listar();
		}  catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao carregar os dados para edição");
		}
	}

	public void excluir() {

	}

	/**
	 * @return the codigoProduto
	 */
	public Long getCodigoProduto() {
		return codigoProduto;
	}

	/**
	 * @param codigoProduto the codigoProduto to set
	 */
	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the fabricantes
	 */
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	/**
	 * @param fabricantes the fabricantes to set
	 */
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	/**
	 * @return the produtos
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}