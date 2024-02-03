package com.agil.admin.repository;

import com.agil.admin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,Long> {

}

