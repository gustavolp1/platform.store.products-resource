package main.java.insper.store.products;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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

    public Product update(Product in) {
        return productRepository.save(new ProductModel(in)).to();
    }

    public void delete(@NonNull String id) {
        productRepository.deleteById(id);
    }
}
