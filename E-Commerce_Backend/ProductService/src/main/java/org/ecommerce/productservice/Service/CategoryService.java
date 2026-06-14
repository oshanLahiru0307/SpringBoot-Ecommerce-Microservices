package org.ecommerce.productservice.Service;

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

}
