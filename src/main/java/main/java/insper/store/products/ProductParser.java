package main.java.insper.store.products;

import insper.store.products.ProductIn;
import insper.store.products.ProductOut;

public class ProductParser {
    
    public static Product to(ProductIn in) {
        return Product.builder()
            .name(in.name())
            .id_partner(in.id_partner())
            .description(in.description())
            .price(in.price())
            .quantity(in.quantity())
            .build();
    }

    public static ProductOut to(Product product) {
        return ProductOut.builder()
            .name(product.name())
            .id_partner(product.id_partner())
            .description(product.description())
            .price(product.price())
            .quantity(product.quantity())
            .build();
    }

    public static ProductOut to(ProductModel product){
        return ProductOut.builder()
            .name(product.name())
            .id_partner(product.id_partner())
            .description(product.description())
            .price(product.price())
            .quantity(product.quantity())
            .build();
    }
}
