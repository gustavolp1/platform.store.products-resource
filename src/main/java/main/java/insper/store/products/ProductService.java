package main.java.insper.store.products;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import insper.store.partner.PartnerController;
import lombok.NonNull;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PartnerController partnerController;

    public Product create(Product in) {
        return productRepository.save(new ProductModel(in)).to();
    }

    public Product read(@NonNull String id) {
        return productRepository.findById(id).map(ProductModel::to).orElseThrow(() -> new IllegalArgumentException("Product not found"));
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

    public boolean isValidUser(String identifier){
        try {
            partnerController.findUser(identifier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
