package org.ecommerce.productservice.Controller;

import org.ecommerce.productservice.DTO.CategoryRequestDTO;
import org.ecommerce.productservice.DTO.CategoryResponseDTO;
import org.ecommerce.productservice.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //get all categories
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categories);
        }
    }

    //get a single category by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(category);
        }
    }

    //create new category
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDTO name) {
        CategoryResponseDTO createdCategory = categoryService.createCategory(name);
        return ResponseEntity.ok(createdCategory);
    }

    //update category by id
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestDTO name) {
        CategoryResponseDTO updateCategory = categoryService.updateCategory(id, name);
        if (updateCategory == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateCategory);
        }
    }

    //delete category by id
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        String isDeleted = categoryService.deleteCategory(id);
        if (isDeleted.contains("not found")) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(isDeleted);
        }
    }
}
