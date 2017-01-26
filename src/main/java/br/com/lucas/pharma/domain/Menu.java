package br.com.lucas.pharma.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
@Entity
public class Menu extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String rotulo;
	
	@Column(length = 50)
	private String caminho;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "codigo") //é ligado ao código do Generic Domain
	@Fetch(FetchMode.SUBSELECT)
	private List<Menu> menus;	

	/**
	 * @return the rotulo
	 */
	public String getRotulo() {
		return rotulo;
	}

	/**
	 * @param rotulo the rotulo to set
	 */
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	/**
	 * @return the menus
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * @return the caminho
	 */
	public String getCaminho() {
		return caminho;
	}

	/**
	 * @param caminho the caminho to set
	 */
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}	
}