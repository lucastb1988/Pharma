package br.com.lucas.pharma.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.UsuarioDAO;
import br.com.lucas.pharma.domain.Pessoa;
import br.com.lucas.pharma.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped // finalizado somente quando encerra a sessão ou por timeout
public class AutenticacaoBean implements Serializable {
	
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa()); // criar uma instanciação vazia de pessoa (para que seja reconhecido o cpf dentro da composição pessoa)
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha()); // cria um usuario temporario e captura seu cpf e senha
			
			if(usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}			
			Faces.redirect("./pages/principal.xhtml"); // redireciona para página principal caso o login esteja correto
		} catch(IOException ex) {
			Messages.addGlobalError(ex.getMessage());
			ex.printStackTrace();
		}	
	}
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}		
}