package br.com.lucas.pharma.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lucas.pharma.domain.Caixa;
import br.com.lucas.pharma.util.HibernateUtil;

/**
 * @author Lucas.
 * 
 *         Classe que representa um DAO Caixa que extende da classe Generica DAO.
 * 
 */
public class CaixaDAO extends GenericDAO<Caixa> {
	
	/**
	 * Método para buscar na classe Caixa informando uma data de abertura capturando na database do banco de dados.
	 * 
	 * * @param dataAbertura a dataAbertura da Classe Caixa a ser buscada
	 * 
	 * @return um caixa com a data de abertura criada.
	 */
	public Caixa buscar(Date dataAbertura) { //verificar se já existe um caixa aberto com uma dataAbertura
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Caixa.class); //criar um criterio para a classe Caixa
			consulta.add(Restrictions.eq("dataAbertura", dataAbertura)); //gerando um WHERE
			
			Caixa resultado = (Caixa) consulta.uniqueResult(); //retorna somente um resultado
			
			return resultado;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}		
	}
}