package com.example.app.service.specification;
import com.example.app.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {
    public static Specification<Product> priceGreaterThanOrEq(Integer value){
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),value);
        };
    }

    public static Specification<Product> priceLessThanOrEq(Integer value){
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"),value);
        };
    }
}
