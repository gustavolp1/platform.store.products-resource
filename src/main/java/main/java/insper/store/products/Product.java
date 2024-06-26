package main.java.insper.store.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder
@Getter @Setter @Accessors(fluent = true, chain = true)
@AllArgsConstructor @NoArgsConstructor
public class Product {
    
    private String id;
    private String id_partner;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
