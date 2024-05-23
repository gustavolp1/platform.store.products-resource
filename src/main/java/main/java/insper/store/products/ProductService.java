package main.java.insper.store.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import insper.store.partner.PartnerController;
import lombok.NonNull;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PartnerController partnerController;

    @CachePut(value = "partner", key = "#results.id")
    public Product create(Product in) {
        return productRepository.save(new ProductModel(in)).to();
    }

    @Cacheable(value = "partner", key = "#id")
    public Product read(@NonNull String id) {
        return productRepository.findById(id).map(ProductModel::to).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @CachePut(value = "partner", key = "#id")
    public Product update(@NonNull String id, Product in) {
        return productRepository.save(new ProductModel(in)).to();
    }

    @CacheEvict(value = "partner", key = "#id")
    public void delete(@NonNull String id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Cacheable(value = "allpartners", key = "#id")
    public List<ProductModel> findAll() {
        List<ProductModel> list = new ArrayList<>();
        productRepository.findAll().forEach(list::add);
        return list;
    }

    public boolean isValidUser(String identifier){
        try {
            partnerController.findUser(identifier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
