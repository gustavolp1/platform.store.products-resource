package main.java.insper.store.products;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import insper.store.products.ProductIn;
import insper.store.products.ProductOut;
import insper.store.products.ProductsController;

@RestController
public class ProductResource implements ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/info")
    public ResponseEntity<Map<String, String>> info() {
        return new ResponseEntity<Map<String, String>>(
            Map.ofEntries(
                Map.entry("microservice.name", ProductApplication.class.getSimpleName()),
                Map.entry("os.arch", System.getProperty("os.arch")),
                Map.entry("os.name", System.getProperty("os.name")),
                Map.entry("os.version", System.getProperty("os.version")),
                Map.entry("file.separator", System.getProperty("file.separator")),
                Map.entry("java.class.path", System.getProperty("java.class.path")),
                Map.entry("java.home", System.getProperty("java.home")),
                Map.entry("java.vendor", System.getProperty("java.vendor")),
                Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
                Map.entry("java.version", System.getProperty("java.version")),
                Map.entry("line.separator", System.getProperty("line.separator")),
                Map.entry("path.separator", System.getProperty("path.separator")),
                Map.entry("user.dir", System.getProperty("user.dir")),
                Map.entry("user.home", System.getProperty("user.home")),
                Map.entry("user.name", System.getProperty("user.name")),
                Map.entry("jar", new java.io.File(
                    ProductApplication.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath()
                    ).toString())
            ), HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<ProductOut> create(ProductIn in) {
        Product product = ProductParser.to(in);
        product = productService.create(product);
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri())
            .body(ProductParser.to(product));
    }

    @Override
    public ResponseEntity<List<ProductOut>> read() {
        return ResponseEntity.ok(productService.findAll().stream().map(ProductParser::to).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ProductOut> read(String id) {
        try {
            Product product = productService.read(id);
            return ResponseEntity.ok(ProductParser.to(product));
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ProductOut> update(String id, ProductIn in) {
        Product product = ProductParser.to(in);
        product = productService.update(id, product);
        return ResponseEntity.ok(ProductParser.to(product));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        try {
            productService.delete(id);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}