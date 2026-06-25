package org.ecommerce.productservice.Service;

import org.ecommerce.productservice.DTO.CategoryRequestDTO;
import org.ecommerce.productservice.DTO.CategoryResponseDTO;
import org.ecommerce.productservice.Model.ProductEntity;
import org.ecommerce.productservice.Repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    //get all categories
    public List<CategoryResponseDTO> getAllCategories() {
        List<ProductEntity> categorylist = categoryRepo.findAll();
        return modelMapper.map(categorylist, new TypeToken<List<CategoryResponseDTO>>(){}.getType());
    }

    //get category by id
    public CategoryResponseDTO getCategoryById(Integer id){
        ProductEntity result = categoryRepo.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return modelMapper.map(result, CategoryResponseDTO.class);
    }

    //caret new category
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
        ProductEntity category = modelMapper.map(categoryRequestDTO, ProductEntity.class);
        ProductEntity savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDTO.class);
    }

    //update category
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO categoryRequestDTO){
        ProductEntity category = categoryRepo.findById(id).orElse(null);
        if(category == null){
            return null;
        }
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        ProductEntity updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
    }

    //delete category
    public String deleteCategory(Integer id){
        ProductEntity category = categoryRepo.findById(id).orElse(null);
        if(category == null){
            return "Category not found";
        }
        categoryRepo.delete(category);
        return "Category deleted successfully";
    }



}
