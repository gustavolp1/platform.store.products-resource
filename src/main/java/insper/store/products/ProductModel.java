package main.java.insper.store.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "products")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class ProductModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_product")
    private String id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_description")
    private String description;

    @Column(name = "tx_price")
    private String price;

    @Column(name = "tx_quantity")
    private String quantity;

    public ProductModel(Product o) {
        this.id = o.id();
        this.name = o.name();
        this.description = o.description();
        this.price = o.price();
        this.quantity = o.quantity();
    }
    
    public Product to() {
        return Product.builder()
            .id(id)
            .name(name)
            .description(description)
            .price(price)
            .quantity(quantity)
            .build();
    }
}
