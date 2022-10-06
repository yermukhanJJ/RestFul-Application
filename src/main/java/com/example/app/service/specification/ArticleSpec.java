package com.example.app.service.specification;

import com.example.app.entity.Article;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpec {
    public static Specification<Article> idEqualProductId(Long id) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("product_id"), id);
    }
}
