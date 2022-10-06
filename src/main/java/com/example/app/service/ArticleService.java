package com.example.app.service;

import com.example.app.entity.Article;
import com.example.app.entity.Product;
import com.example.app.repository.ArticleRepository;
import com.example.app.repository.ProductRepository;
import com.example.app.service.specification.ArticleSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ProductRepository productRepository;

    public List<Article> getArticles(String word) {
        Product products;
        Specification<Article> filter = Specification.where(null);
        if (word == null) {
            log.info("Getting all articles");
            return articleRepository.findAll();
        } else {
            products = productRepository.findByTitleStartingWith(word);
            filter = filter.and(ArticleSpec.idEqualProductId(products.getId()));
            log.info("Getting articles title starting with " + word);
            return articleRepository.findAll(filter);
        }
    }

    public Article getArticle(Long id) {
        log.info("Getting article with id: " + id);
        return articleRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Article with id: " + id + " not found"));
    }

    public Article addArticle(Article article) {
        Product product = productRepository.findById(article.getProduct_id()).
                orElseThrow(() -> new EntityNotFoundException("Product with id: " + article.getProduct_id() + " not found"));
        article.setDt(LocalDate.now());
        Article newArticle = new Article(product.getId(), article.getContent(), article.getTitle(), product, article.getDt());
        log.info("Added new article");
        return articleRepository.save(newArticle);
    }

    public Article upArticle(Long id, Article article) {
        Product product = productRepository.findById(article.getProduct_id()).
                orElseThrow(() -> new EntityNotFoundException("Product with id: " + article.getProduct_id() + " not found"));
        Article upArticle = articleRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Article with id: " + id + " not found"));
        upArticle.setTitle(article.getTitle());
        upArticle.setContent(article.getContent());
        upArticle.setProduct_id(product.getId());
        upArticle.setProduct(product);
        log.info("Article with id " + id + "updated");
        return articleRepository.save(upArticle);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
        log.info("Article with id " + id + " deleted");
    }
}
