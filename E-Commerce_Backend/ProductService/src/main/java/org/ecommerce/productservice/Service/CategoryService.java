package org.ecommerce.productservice.Service;

import jdk.jfr.Category;
import org.ecommerce.productservice.DTO.CategoryRequestDTO;
import org.ecommerce.productservice.DTO.CategoryResponseDTO;
import org.ecommerce.productservice.Model.CategoryEntity;
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
        List<CategoryEntity> categorylist = categoryRepo.findAll();
        return modelMapper.map(categorylist, new TypeToken<List<CategoryResponseDTO>>(){}.getType());
    }

    //get category by id
    public CategoryResponseDTO getCategoryById(Integer id){
        CategoryEntity result = categoryRepo.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        return modelMapper.map(result, CategoryResponseDTO.class);
    }

    //caret new category
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO){
        CategoryEntity category = modelMapper.map(categoryRequestDTO, CategoryEntity.class);
        CategoryEntity savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDTO.class);
    }

    //update category
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO categoryRequestDTO){
        CategoryEntity category = categoryRepo.findById(id).orElse(null);
        if(category == null){
            return null;
        }
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        CategoryEntity updatedCategory = categoryRepo.save(category);
        return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
    }

    //delete category
    public String deleteCategory(Integer id){
        CategoryEntity category = categoryRepo.findById(id).orElse(null);
        if(category == null){
            return "Category not found";
        }
        categoryRepo.delete(category);
        return "Category deleted successfully";
    }



}
