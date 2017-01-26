package br.com.lucas.pharma.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Historico extends GenericDomain {
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(length = 200 ,nullable = false)
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;		
	
	/**
	 * @return the horario
	 */
	
	public Date getHorario() {
		return horario;
	}
	
	/**
	 * @param horario the horario to set
	 */
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	
	/**
	 * @return the observacoes
	 */
	public String getObservacoes() {
		return observacoes;
	}
	
	/**
	 * @param observacoes the observacoes to set
	 */
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
}