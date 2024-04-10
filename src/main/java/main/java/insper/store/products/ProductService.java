package main.java.insper.store.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product in) {
        return productRepository.save(new ProductModel(in)).to();
    }

    public Product read(@NonNull String id) {
        return productRepository.findById(id).map(ProductModel::to).orElse(null);
    }

    public Product update(@NonNull String id, Product in) {
        return productRepository.save(new ProductModel(in)).to();
    }

    public void delete(@NonNull String id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public List<ProductModel> findAll() {
        List<ProductModel> list = new ArrayList<>();
        productRepository.findAll().forEach(list::add);
        return list;
    }
}
