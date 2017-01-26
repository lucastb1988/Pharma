package br.com.lucas.pharma.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Caixa extends GenericDomain {
	
	@Column(nullable = false, unique = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;
	
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFechamento;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorAbertura; // valor inicial ao abrir o caixa
		
	/**
	 * @return the dataAbertura
	 */
	public Date getDataAbertura() {
		return dataAbertura;
	}
	
	/**
	 * @param dataAbertura the dataAbertura to set
	 */
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	/**
	 * @return the dataFechamento
	 */
	public Date getDataFechamento() {
		return dataFechamento;
	}
	
	/**
	 * @param dataFechamento the dataFechamento to set
	 */
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	/**
	 * @return the valorAbertura
	 */
	public BigDecimal getValorAbertura() {
		return valorAbertura;
	}
	
	/**
	 * @param valorAbertura the valorAbertura to set
	 */
	public void setValorAbertura(BigDecimal valorAbertura) {
		this.valorAbertura = valorAbertura;
	}	
}