package org.ecommerce.productservice.Controller;

import org.ecommerce.productservice.DTO.ProductRequestDTO;
import org.ecommerce.productservice.DTO.ProductResponseDTO;
import org.ecommerce.productservice.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        if (products == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        ProductResponseDTO response = productService.getProductById(productId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO request) {
        ProductResponseDTO response = productService.createProduct(request);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<?> updateProductById(
            @PathVariable Integer productId,
            @RequestBody ProductRequestDTO request) {
        ProductResponseDTO response = productService.updateProductById(productId, request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer productId) {
        String response = productService.deleteProductById(productId);
        if (response.contains("not found")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }
}
