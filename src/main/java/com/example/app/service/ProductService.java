package com.example.app.service;
import com.example.app.entity.Product;
import com.example.app.repository.ProductRepository;
import com.example.app.service.specification.ProductSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    //Выводить по указанными ценами
    public List<Product> getAllProductByPrice(Integer min, Integer max) {
        log.info("Getting all products:min = " + min + "; max = " + max);
        Specification<Product> filter = Specification.where(null);

        if (max != null) {
            filter = filter.and(ProductSpec.priceLessThanOrEq(max));
        }
        if (min != null) {
            filter = filter.and(ProductSpec.priceGreaterThanOrEq(min));
        }

        return productRepository.findAll(filter);
    }

    //Найти продукт по id
    public Product getProduct(Long id) {
        log.info("Getting product with id:" + id);
        return productRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found"));
    }

    //Добавить новый продукт
    public Product addProduct(Product product) {
        log.info("Added new product: " + product);
        return productRepository.save(product);
    }

    //Удалить продукт по айди
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Deleted product with id: " + id);
    }

    //Изменить продукт по айди
    public Product editProduct(Product product, Long id) {
        Product upProduct = productRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product with id:" + id + " not found"));
        upProduct.setTitle(product.getTitle());
        upProduct.setDescription(product.getDescription());
        upProduct.setPrice(product.getPrice());
        log.info("Updated product with id: " + id);

        return productRepository.save(upProduct);
    }
}
