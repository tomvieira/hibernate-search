package br.com.tomvieira.ecommerce.controller;

import br.com.tomvieira.ecommerce.model.Produto;
import br.com.tomvieira.ecommerce.repository.Produtos;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Produto> produtosFiltrados;

    private String text;

    @Inject
    private Produtos produtos;

    public void pesquisar() {
        this.produtosFiltrados = this.produtos.fullTextSearch(this.getText());
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
