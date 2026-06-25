package org.ecommerce.productservice.Service;

import org.ecommerce.productservice.DTO.ProductRequestDTO;
import org.ecommerce.productservice.DTO.ProductResponseDTO;
import org.ecommerce.productservice.Model.ProductEntity;
import org.ecommerce.productservice.Repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    //get all products
    public List<ProductResponseDTO> getAllProducts() {
        try {
            List<ProductEntity> products = productRepo.findAll();
            return modelMapper.map(products, new TypeToken<List<ProductResponseDTO>>() {}.getType());
        } catch (Exception e) {
            System.out.println("Error occurred while fetching products: " + e.getMessage());
            return null;
        }
    }

    //get product by id
    public ProductResponseDTO getProductById(Integer productId) {
        try {
            ProductEntity product = productRepo.findById(productId).orElse(null);
            if (product == null) {
                System.out.println("Product not found with id: " + productId);
                return null;
            }
            return modelMapper.map(product, ProductResponseDTO.class);
        } catch (Exception e) {
            System.out.println("Error occurred while fetching product: " + e.getMessage());
            return null;
        }
    }

    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        try {
            ProductEntity product = modelMapper.map(request, ProductEntity.class);
            ProductEntity savedProduct = productRepo.save(product);
            return modelMapper.map(savedProduct, ProductResponseDTO.class);
        } catch (Exception e) {
            System.out.println("Error occurred while creating product: " + e.getMessage());
            return null;
        }
    }

    public ProductResponseDTO updateProductById(Integer productId, ProductRequestDTO request) {
        try {
            ProductEntity existingProduct = productRepo.findById(productId).orElse(null);
            if (existingProduct == null) {
                System.out.println("Product not found with id: " + productId);
                return null;
            }

            existingProduct.setProductName(request.getProductName());
            existingProduct.setProductDescription(request.getProductDescription());
            existingProduct.setProductPrice(request.getProductPrice());

            ProductEntity updatedProduct = productRepo.save(existingProduct);
            return modelMapper.map(updatedProduct, ProductResponseDTO.class);
        } catch (Exception e) {
            System.out.println("Error occurred while updating product: " + e.getMessage());
            return null;
        }
    }

    public String deleteProductById(Integer productId) {
        try {
            if (productRepo.existsById(productId)) {
                productRepo.deleteById(productId);
                return "product deleted successfully";
            }
            return "product not found with id: " + productId;
        } catch (Exception e) {
            System.out.println("Error occurred while deleting product: " + e.getMessage());
            return "product not found with id: " + productId + " due to error: " + e.getMessage();
        }
    }
}
