package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.HistoricoDAO;
import br.com.lucas.pharma.dao.ProdutoDAO;
import br.com.lucas.pharma.domain.Historico;
import br.com.lucas.pharma.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {
	
	private Produto produto;
	private Boolean exibePainelDados; //se esta variavel for false o painel sumirá (página do site), se for true o painel aparece
	private Historico historico;
	
	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();	
		exibePainelDados = false;
	}
	
	public void buscar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.buscar(produto.getCodigo());
			
			if(resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");				
			} else {
				exibePainelDados = true;
				produto = resultado;				
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
		}
	}
	
	public void salvar() {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);
			
			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);
			
			Messages.addGlobalInfo("Histórico salvo com sucesso");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o historico");
		}
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
	 * @return the exibePainelDados
	 */
	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	/**
	 * @param exibePainelDados the exibePainelDados to set
	 */
	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}

	/**
	 * @return the historico
	 */
	public Historico getHistorico() {
		return historico;
	}

	/**
	 * @param historico the historico to set
	 */
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	

}