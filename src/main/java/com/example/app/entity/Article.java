package com.example.app.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long product_id;

    private String title;

    private String content;

    private LocalDate dt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Product product;

    public Article(Long product_id, String title, String content,Product product,LocalDate dt) {
        this.title = title;
        this.content = content;
        this.product_id = product_id;
        this.product = product;
        this.dt = dt;
    }

}
