package com.example.MultipleDb.db1.repo;

import com.example.MultipleDb.db1.entities.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Qualifier("db1EntityManagerFactory")
public interface ProductRepo extends JpaRepository<Product, Integer>{
    Product findByName(String name);
}
