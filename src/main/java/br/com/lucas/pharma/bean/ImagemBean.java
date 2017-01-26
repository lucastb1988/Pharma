package br.com.lucas.pharma.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped // a imagem será recarregada a cada clique feito para não travar o processo junto ao @ViewScoped
public class ImagemBean {

	@ManagedProperty("#{param.caminho}") // enviando um caminho para a foto no upload do xhtml
	private String caminho;

	private StreamedContent foto;

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
	/**
	 * @return the foto
	 * @throws IOException 
	 */
	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty()) {
			Path path = Paths.get("C:/Users/LUCAS/Desktop/EE/Upload/branco.png"); // passa o caminho da foto branca
			InputStream stream = Files.newInputStream(path); // está capturando os bytes da foto em branco
			foto = new DefaultStreamedContent(stream); // passando a foto branco
		} else {
			Path path = Paths.get(caminho); // passa o caminho da foto verdadeira
			InputStream stream = Files.newInputStream(path); // está capturando os bytes da foto
			foto = new DefaultStreamedContent(stream); // passando a foto
		}
		return foto;
	}
	
	/**
	 * @param foto the foto to set
	 */
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
}