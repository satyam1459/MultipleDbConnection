package com.example.MultipleDb.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private final com.example.MultipleDb.db1.repo.ProductRepo productRepoDb1; // Autowire the ProductRepo for db1

    private final com.example.MultipleDb.db2.repo.ProductRepo productRepoDb2; // Autowire the ProductRepo for db2

    @PostMapping("product/db1")
    public String saveProductInDb1(@RequestBody com.example.MultipleDb.db1.entities.Product product) {
        productRepoDb1.save(product); // Save the product in db1
        return "Product saved in db1";
    }

    @PostMapping("product/db2")
    public String saveProductInDb2(@RequestBody com.example.MultipleDb.db2.entities.Product product) {
        productRepoDb2.save(product); // Save the product in db2
        return "Product saved in db2";
    }
}
