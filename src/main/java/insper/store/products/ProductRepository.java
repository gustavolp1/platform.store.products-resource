package main.java.insper.store.products;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, String> {

    Optional<ProductModel> findById(String id);
    
}
