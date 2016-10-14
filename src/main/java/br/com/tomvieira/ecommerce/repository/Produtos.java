package br.com.tomvieira.ecommerce.repository;

import br.com.tomvieira.ecommerce.model.Produto;
import br.com.tomvieira.ecommerce.util.jpa.FullTextSearch;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.inject.Model;

import javax.inject.Inject;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

@Model
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @FullTextSearch
    private FullTextEntityManager fullTextEntityManager;

    public List<Produto> fullTextSearch(String text) {
        QueryBuilder builder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Produto.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = builder
                .keyword()
                .onFields("nome", "descricao","fabricante.nome")
                .matching(text)
                .createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Produto.class);
        return jpaQuery.getResultList();

    }

}
